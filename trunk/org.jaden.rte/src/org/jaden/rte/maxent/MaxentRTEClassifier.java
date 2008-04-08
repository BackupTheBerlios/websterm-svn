

/*
 * MaxentRTEClassifier.java 1.00 Fri Feb 22 01:58:07 +0000 2008
 *
 */

package org.jaden.rte.maxent;
import org.jaden.rte.Classifier;
import opennlp.maxent.GISModel;
import opennlp.maxent.ContextGenerator;
import org.jaden.rte.THPair;
import org.jaden.rte.Outcome;


/**
 * TODO: Class description
 *
 * @version 1.00 
 * @author 
 */
public class MaxentRTEClassifier implements Classifier {
    private GISModel model;

    private ContextGenerator cg;

    public MaxentRTEClassifier(GISModel model) {
        this.model = model;
        this.cg = new MaxentRTEContextGenerator();
    }

    public Outcome eval(THPair pair) {
        String[] context = cg.getContext(pair);
        String correct = Boolean.toString(pair.getEntailment());

        double[] probs = model.eval(context);
        String predicted = model.getBestOutcome(probs);

        return new Outcome(pair.getId(), correct, predicted);
    }
}

