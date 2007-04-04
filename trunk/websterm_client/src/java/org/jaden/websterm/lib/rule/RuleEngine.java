/**
 *
 */
package org.jaden.websterm.lib.rule;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.List;

import javax.rules.ConfigurationException;
import javax.rules.InvalidRuleSessionException;
import javax.rules.RuleExecutionSetNotFoundException;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.RuleSessionCreateException;
import javax.rules.RuleSessionTypeUnsupportedException;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetRegisterException;

import org.jaden.websterm.lib.model.InputData;
import org.jaden.websterm.lib.model.Result;
import org.jaden.websterm.lib.model.Rule;

/**
 * @author jack
 *
 */
public class RuleEngine {
	private RuleRegistrationEngine registrationEngine;

	private RuleExecutionEngine executionEngine;

	private RuleAdministrator ruleAdministrator;

	private RuleServiceProvider ruleServiceProvider;

	public RuleEngine() {
		try {
			// RuleServiceProviderImpl is registered to "http://drools.org/" via
			// a static initialization block
			Class.forName("org.drools.jsr94.rules.RuleServiceProviderImpl");
			// Get the rule service provider from the provider manager.
			RuleServiceProvider ruleServiceProvider = RuleServiceProviderManager
					.getRuleServiceProvider("http://drools.org/");
			ruleAdministrator = ruleServiceProvider.getRuleAdministrator();
		} catch (ConfigurationException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	public void registerRules() {
		try {
			List<Rule> rules = registrationEngine.getRules();
			LocalRuleExecutionSetProvider executionSetProvider = ruleAdministrator
					.getLocalRuleExecutionSetProvider(null);

			StringBuffer ruleBuffer = new StringBuffer();
			for (Rule rule : rules) {
				ruleBuffer.append(rule.getDefinition());
			}

			RuleExecutionSet executionSet = executionSetProvider
					.createRuleExecutionSet(new StringReader(ruleBuffer
							.toString()), null);
			String uri = executionSet.getName();
			registrationEngine.setRuleUri(uri);
			ruleAdministrator.registerRuleExecutionSet(uri, executionSet, null);
		} catch (RuleExecutionSetCreateException exception) {
			exception.printStackTrace();
		} catch (RuleExecutionSetRegisterException exception) {
			exception.printStackTrace();
		} catch (RemoteException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public List<Result> executeRules(List<InputData> data) {
		List<Result> results = null;

		try {
			results = executionEngine.executeRules(registrationEngine
					.getRuleUri(), data, ruleServiceProvider);
		} catch (InvalidRuleSessionException exception) {
			exception.printStackTrace();
		} catch (RuleExecutionSetNotFoundException exception) {
			exception.printStackTrace();
		} catch (RuleSessionTypeUnsupportedException exception) {
			exception.printStackTrace();
		} catch (RuleSessionCreateException exception) {
			exception.printStackTrace();
		}

		return results;
	}

	/**
	 * @return the executionEngine
	 */
	public RuleExecutionEngine getExecutionEngine() {
		return executionEngine;
	}

	/**
	 * @param executionEngine
	 *            the executionEngine to set
	 */
	public void setExecutionEngine(RuleExecutionEngine executionEngine) {
		this.executionEngine = executionEngine;
	}

	/**
	 * @return the registrationEngine
	 */
	public RuleRegistrationEngine getRegistrationEngine() {
		return registrationEngine;
	}

	/**
	 * @param registrationEngine
	 *            the registrationEngine to set
	 */
	public void setRegistrationEngine(RuleRegistrationEngine registrationEngine) {
		this.registrationEngine = registrationEngine;
	}

	/**
	 * @return the ruleServiceProvider
	 */
	public RuleServiceProvider getRuleServiceProvider() {
		return ruleServiceProvider;
	}

	/**
	 * @param ruleServiceProvider
	 *            the ruleServiceProvider to set
	 */
	public void setRuleServiceProvider(RuleServiceProvider ruleServiceProvider) {
		this.ruleServiceProvider = ruleServiceProvider;
	}

	/**
	 * @return the ruleAdministrator
	 */
	public RuleAdministrator getRuleAdministrator() {
		return ruleAdministrator;
	}

	/**
	 * @param ruleAdministrator
	 *            the ruleAdministrator to set
	 */
	public void setRuleAdministrator(RuleAdministrator ruleAdministrator) {
		this.ruleAdministrator = ruleAdministrator;
	}
}
