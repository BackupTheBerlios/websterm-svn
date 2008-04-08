
/*
 * MaxentRTETrainer.java 1.00 Fri Feb 22 00:29:20 +0000 2008
 *
 */

package org.jaden.rte.maxent;

import java.io.FileInputStream;

import org.jaden.rte.Classifier;
import org.jaden.rte.Outcome;
import org.jaden.rte.RTEDataIterator;
import org.jaden.rte.THPair;
import org.jaden.rte.Trainer;

import opennlp.maxent.DataIndexer;
import opennlp.maxent.Event;
import opennlp.maxent.EventCollector;
import opennlp.maxent.EventCollectorAsStream;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;
import opennlp.maxent.OnePassDataIndexer;
import java.util.Map;


/**
 * TODO: Class description
 *
 * @version 1.00 
 * @author 
 */
public class MaxentRTETrainer implements Trainer<GISModel> {
    private int numIterations;

    private int cutoff;

    private boolean smoothing;

    public MaxentRTETrainer(int numIterations, int cutoff,
            boolean smoothing) {
        this.numIterations = numIterations;
        this.cutoff = cutoff;
        this.smoothing = smoothing;
    }

    public GISModel train(RTEDataIterator trainData) {
        EventCollector collector = new MaxentRTEEventCollector(trainData);
        Event[] events = collector.getEvents();
        DataIndexer indexer = new OnePassDataIndexer(
                new EventCollectorAsStream(collector), cutoff);
        GISModel model = GIS.trainModel(numIterations, indexer, true,
                smoothing);
        return model;
    }

    public static void main(String args[]) throws Exception {
        RTEDataIterator iterator = new RTEDataIterator(
                new FileInputStream("data/rte3_dev.xml"));
        Trainer<GISModel> trainer = new MaxentRTETrainer(2, 0, false);
        GISModel model = trainer.train(iterator);

        RTEDataIterator testIterator = new RTEDataIterator(
                new FileInputStream("data/rte3_test.xml"));
        Classifier classifier = new MaxentRTEClassifier(model);

        int numCorrect = 0;
        int numTotal = 0;
        int numTotalPredicted = 0;
        int numTotalCorrect = 0;
        for(THPair pair : testIterator) {
            Outcome outcome = classifier.eval(pair);
            String correct = outcome.getCorrect();
            String predicted = outcome.getPredicted();

            if(predicted.equals(correct))
                numCorrect++;
            else {
                System.out.print(pair.getId() + " ");
            }

            numTotal++;
        }
        System.out.println();
        System.out.println("Accuracy: " +
                (numCorrect / (double)numTotal));
    }
}

