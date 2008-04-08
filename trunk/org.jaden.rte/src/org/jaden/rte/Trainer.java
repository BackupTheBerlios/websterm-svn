

/*
 * Trainer.java 1.00 Fri Feb 22 00:16:54 +0000 2008
 *
 */

package org.jaden.rte;

import java.io.InputStream;

import org.jaden.rte.RTEDataIterator;

public interface Trainer<T> {
    /**
     * Train the RTE training data for classification or inference.
     *
     * @param trainData The supplied set of training data.
     * @return The trained model instance.
     */
    public T train(RTEDataIterator trainData);
}

