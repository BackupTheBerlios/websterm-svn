

/*
 * THPair.java 1.00 Thu Feb 21 23:35:11 +0000 2008
 *
 */

package org.jaden.rte;
import java.io.Serializable;
import java.util.List;


/**
 * TODO: Class description
 *
 * @version 1.00 
 * @author 
 */
public class THPair implements Serializable {
    private List<String> text;

    private List<String> hypothesis;

    private int id;

    private boolean entailment;

    private String length;

    public THPair(int id, List<String> text, List<String> hypothesis,
            boolean entailment, String length) {
        this.id = id;
        this.text = text;
        this.hypothesis = hypothesis;
        this.entailment = entailment;
        this.length = length;
    }

	/**
	 * get the value of length
	 * @return the value of length
	 */
	public String getLength() {
		return this.length;
	}

    /**
	 * set a new value to length
	 * @param length the new value to be used
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * get the value of entailment
	 * @return the value of entailment
	 */
	public boolean getEntailment() {
		return this.entailment;
	}

    /**
	 * set a new value to entailment
	 * @param entailment the new value to be used
	 */
	public void setEntailment(boolean entailment) {
		this.entailment = entailment;
	}

	/**
	 * get the value of id
	 * @return the value of id
	 */
	public int getId() {
		return this.id;
	}

    /**
	 * set a new value to id
	 * @param id the new value to be used
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get the value of hypothesis
	 * @return the value of hypothesis
	 */
	public List<String> getHypothesis() {
		return this.hypothesis;
	}

    /**
	 * set a new value to hypothesis
	 * @param hypothesis the new value to be used
	 */
	public void setHypothesis(List<String> hypothesis) {
		this.hypothesis = hypothesis;
	}

    /**
	 * get the value of text
	 * @return the value of text
	 */
	public List<String> getText() {
		return this.text;
	}

    /**
	 * set a new value to text
	 * @param text the new value to be used
	 */
	public void setText(List<String> text) {
		this.text = text;
	}
}

