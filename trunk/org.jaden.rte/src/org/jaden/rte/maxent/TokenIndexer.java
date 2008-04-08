
/*
 * TokenIndexer.java 1.00 Thu Mar 06 18:00:25 +0000 2008
 *
 */

package org.jaden.rte.maxent;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import org.jaden.rte.RTEDataIterator;
import org.jaden.rte.THPair;


/**
 * @version 1.00 
 * @author 
 */
public class TokenIndexer {
    private Map<String, Integer> indexTable =
        new HashMap<String, Integer>();

    private int currentIndex = 0;

    public TokenIndexer() {

    }

    public void indexTokens(List<String> tokens) {
        for(String token : tokens) {
            if(!indexTable.containsKey(token)) {
                indexTable.put(token, currentIndex++);
            }
        }
    }

    public void saveIndex(String file) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(
                    new FileOutputStream(file));
            stream.writeObject(indexTable);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Map<String, Integer> loadIndex(String file) {
        Map<String, Integer> tokenIndexes = null;
        try {
            ObjectInputStream stream = new ObjectInputStream(
                    new FileInputStream(file));
            tokenIndexes = (Map<String, Integer>)stream.readObject();
        } catch(IOException exception) {
            exception.printStackTrace();
        } catch(ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return tokenIndexes;
    }

    public static void main(String args[]) throws Exception {
        TokenIndexer indexer = new TokenIndexer();
        RTEDataIterator iterator = new RTEDataIterator(
                new FileInputStream("data/rte3_dev.xml"));
        while(iterator.hasNext()) {
            THPair pair = iterator.next();
            List<String> text = pair.getText();
            List<String> hypothesis = pair.getHypothesis();
            indexer.indexTokens(text);
            indexer.indexTokens(hypothesis);
        }

        indexer.saveIndex("data/token_indexes");
    }
}

