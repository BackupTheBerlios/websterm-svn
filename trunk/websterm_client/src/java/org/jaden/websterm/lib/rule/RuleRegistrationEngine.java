/**
 *
 */
package org.jaden.websterm.lib.rule;

import java.util.ArrayList;
import java.util.List;

import org.jaden.websterm.lib.model.Rule;

/**
 * @author jack
 *
 */
public class RuleRegistrationEngine {
	private List<Rule> rules;

	private String ruleUri;

	public RuleRegistrationEngine() {
		rules = new ArrayList<Rule>();
	}

	public void registerRule(Rule rule) {
		rules.add(rule);
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

	/**
	 * @return the ruleUri
	 */
	public String getRuleUri() {
		return ruleUri;
	}

	/**
	 * @param ruleUri
	 *            the ruleUri to set
	 */
	public void setRuleUri(String ruleUri) {
		this.ruleUri = ruleUri;
	}
}
