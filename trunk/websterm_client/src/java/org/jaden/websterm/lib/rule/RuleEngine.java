/**
 *
 */
package org.jaden.websterm.lib.rule;

import java.util.List;

import org.jaden.websterm.lib.model.InputData;
import org.jaden.websterm.lib.model.Result;
import org.jaden.websterm.lib.model.Rule;

/**
 * @author jack
 *
 */
public class RuleEngine {
	private List<Rule> rules;

	private List<InputData> inputs;

	public void registerRules(List<Rule> rules) {
		this.rules = rules;
	}

	public List<Result> executeRules() {
		return null;
	}

	/**
	 * @return the inputs
	 */
	public List<InputData> getInputs() {
		return inputs;
	}

	/**
	 * @param inputs
	 *            the inputs to set
	 */
	public void setInputs(List<InputData> inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the rules
	 */
	public List<Rule> getRules() {
		return rules;
	}

	/**
	 * @param rules
	 *            the rules to set
	 */
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
}
