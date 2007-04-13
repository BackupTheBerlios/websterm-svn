/**
 * 
 */
package org.jaden.websterm.lib.engines;

import java.util.List;

import org.jaden.websterm.lib.model.Rule;
import org.jaden.websterm.lib.parser.WebstermParser;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author jack
 * 
 */
public class RuleExecutionJob implements Job {
	private WebstermParser parser;

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDetail details = context.getJobDetail();
		JobDataMap dataMap = details.getJobDataMap();

		String definitionFilePath = dataMap.getString("definitionFilePath");
		String hibernateConfigPath = dataMap.getString("hibernateConfigPath");
		String inputConfigPath = dataMap.getString("inputConfigPath");
		String functionsPath = dataMap.getString("functionsPath");

		List<Rule> rules = parser.parseDefinition(definitionFilePath);
	}

	/**
	 * @return the parser
	 */
	public WebstermParser getParser() {
		return parser;
	}

	/**
	 * @param parser
	 *            the parser to set
	 */
	public void setParser(WebstermParser parser) {
		this.parser = parser;
	}
}
