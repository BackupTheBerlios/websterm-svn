/**
 *
 */
package org.jaden.websterm.lib.rule;

import java.rmi.RemoteException;
import java.util.List;

import javax.rules.ConfigurationException;
import javax.rules.InvalidRuleSessionException;
import javax.rules.RuleExecutionSetNotFoundException;
import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleSessionCreateException;
import javax.rules.RuleSessionTypeUnsupportedException;
import javax.rules.StatelessRuleSession;

import org.jaden.websterm.lib.model.InputData;
import org.jaden.websterm.lib.model.Result;

/**
 * @author jack
 *
 */
public class RuleExecutionEngine {
	public List<Result> executeRules(String uri, List<InputData> originalData,
			RuleServiceProvider ruleServiceProvider)
			throws InvalidRuleSessionException,
			RuleExecutionSetNotFoundException,
			RuleSessionTypeUnsupportedException, RuleSessionCreateException {
		List<Result> results = null;
		try {
			RuleRuntime runtime = ruleServiceProvider.getRuleRuntime();
			StatelessRuleSession session = (StatelessRuleSession) runtime
					.createRuleSession(uri, null,
							RuleRuntime.STATELESS_SESSION_TYPE);
			results = session.executeRules(originalData);

		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return results;
	}
}
