

/*
 * SimpleClassifier.java 1.00 Thu Mar 20 14:30:38 +0000 2008
 *
 */

package org.jaden.rte.maxent;
import org.jaden.rte.RTEDataIterator;
import org.jaden.rte.THPair;
import java.util.List;
import org.jaden.rte.Outcome;
import java.util.ArrayList;
import java.io.FileInputStream;


/**
 * @version 1.00 
 * @author 
 */
public class SimpleClassifier {
    public List<Outcome> classify(RTEDataIterator iterator) {
        iterator.reset();

        List<Outcome> outcomes = new ArrayList<Outcome>();
        for(THPair pair : iterator) {
            List<String> text = pair.getText();
            List<String> hypothesis = pair.getHypothesis();
            double distance = MaxentRTEContextGenerator.getDistance(text, hypothesis);
            String prediction = "false";
            if(distance > 0.3) {
                prediction = "true";
            }
            String correct = Boolean.toString(pair.getEntailment());
            Outcome outcome = new Outcome(pair.getId(),
                    correct, prediction);
            outcomes.add(outcome);
        }

        return outcomes;
    }

    public double getWordOverlap(List<String> text, List<String> hyp) {
        double overlap = 0.0;
        for(String token : hyp) {
            if(text.contains(token))
                overlap++;
        }
        return overlap / hyp.size();
    }

    public static void main(String args[]) throws Exception {
        RTEDataIterator testIterator = new RTEDataIterator(
                new FileInputStream("data/rte3_test.xml"));
        SimpleClassifier classifier = new SimpleClassifier();
        List<Outcome> outcomes = classifier.classify(testIterator);
        
        int numCorrect = 0;
        int numTotal = 0;
        int numTotalPredicted = 0;
        int numTotalCorrect = 0;
        for(Outcome outcome : outcomes) {
            String correct = outcome.getCorrect();
            String predicted = outcome.getPredicted();

            if(predicted.equals(correct))
                numCorrect++;
            else {
                System.out.print(outcome.getId() + ",");
            }

            numTotal++;
        }
        System.out.println();
        System.out.println("Accuracy: " +
                (numCorrect / (double)numTotal));
    }
}

