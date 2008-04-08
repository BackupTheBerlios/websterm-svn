

/*
 * MaxentRTEContextGenerator.java 1.00 Fri Feb 22 01:02:53 +0000 2008
 *
 */

package org.jaden.rte.maxent;
import opennlp.maxent.ContextGenerator;
import org.jaden.rte.THPair;
import java.util.List;
import java.util.ArrayList;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import edu.stanford.nlp.trees.Tree;
import java.util.Map;


/**
 * TODO: Class description
 *
 * @version 1.00 
 * @author 
 */
public class MaxentRTEContextGenerator implements ContextGenerator {
    private static Map<String, Integer> tokenIndexes = null;

    static {
        tokenIndexes = TokenIndexer.loadIndex("data/token_indexes");
    }

    public MaxentRTEContextGenerator() {
    }

    public String[] getContext(Object o) {
        THPair pair = (THPair)o;
        List<String> hypothesis = pair.getHypothesis();
        List<String> text = pair.getText();
        
        int wordOverlap = 0;
        for(String t : hypothesis) {
            if(text.contains(t))
                wordOverlap++;
        }

        double percentage = wordOverlap / (double)hypothesis.size();
        double distance = getDistance(text, hypothesis);
        List<String> context = new ArrayList<String>();
        context.add("word_overlap=" + percentage);
        context.add("distance=" + distance);

        return context.toArray(new String[0]);
    }

    public static double getDistance(List<String> text, List<String> hypothesis) {
        int size = tokenIndexes.size();
        int[] vector = new int[size];
        for(String token : text) {
            if(tokenIndexes.containsKey(token))
                vector[tokenIndexes.get(token)] ++;
        }

        int[] vector1 = new int[size];
        for(String token : hypothesis) {
            if(tokenIndexes.containsKey(token))
                vector1[tokenIndexes.get(token)] ++;
        }

        double score = 0.0;
        int q = 0;
        int w = 0;
        int wq = 0;
        for(int i = 0; i < vector.length; i++) {
            q += vector1[i] * vector1[i];
            w += vector[i] * vector[i];
            wq += vector[i] * vector1[i];
        }

        return wq / (double)Math.sqrt(w*q);
    }
}

