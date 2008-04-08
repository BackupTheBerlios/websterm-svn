

/*
 * Classifier.java 1.00 Fri Feb 22 00:23:53 +0000 2008
 *
 */

package org.jaden.rte;

public interface Classifier {
    /**
     * Eval the Text-Hypothesis pair for entailment.
     *
     * @param pair The T-H pair under classification
     * @return The outcome of the classification containing both
     *     the correct entailment and the predicted entailment.
     */
    public Outcome eval(THPair pair);
}

