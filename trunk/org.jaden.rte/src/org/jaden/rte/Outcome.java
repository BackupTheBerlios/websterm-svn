

/*
 * Outcome.java 1.00 Fri Feb 22 00:21:06 +0000 2008
 *
 */

package org.jaden.rte;

import java.io.Serializable;


/**
 * TODO: Class description
 *
 * @version 1.00 
 * @author 
 */
public class Outcome implements Serializable {
    private String correct;
	
    private String predicted;

    private int id;

    public Outcome(int id, String correct, String predicted) {
        this.id = id;
        this.correct = correct;
        this.predicted = predicted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	/**
	 * get the value of predicted
	 * @return the value of predicted
	 */
	public String getPredicted(){
		return this.predicted;
	}

    /**
	 * set a new value to predicted
	 * @param predicted the new value to be used
	 */
	public void setPredicted(String predicted) {
		this.predicted = predicted;
	}

    /**
	 * get the value of correct
	 * @return the value of correct
	 */
	public String getCorrect(){
		return this.correct;
	}

    /**
	 * set a new value to correct
	 * @param correct the new value to be used
	 */
	public void setCorrect(String correct) {
		this.correct = correct;
	}
}

