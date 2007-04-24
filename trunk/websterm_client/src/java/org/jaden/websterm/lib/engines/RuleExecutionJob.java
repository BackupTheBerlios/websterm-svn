/**
 * 
 */
package org.jaden.websterm.lib.engines;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.jaden.websterm.lib.model.InputData;
import org.jaden.websterm.lib.model.Result;
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

	private DatabaseAccessEngine dbAccessEngine;

	private RuleExecutionEngine executionEngine;

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDetail details = context.getJobDetail();
		JobDataMap dataMap = details.getJobDataMap();

		String definitionFilePath = dataMap.getString("definitionFilePath");
		String hibernateConfigPath = dataMap.getString("hibernateConfigPath");
		String inputConfigPath = dataMap.getString("inputConfigPath");
		String functionsPath = dataMap.getString("functionsPath");
		String resultsPath = dataMap.getString("resultsPath");

		parser = new WebstermParser();
		List<Rule> rules = parser.parseDefinition(definitionFilePath);
		if (!dbAccessEngine.isInitialized()) {
			dbAccessEngine.init(hibernateConfigPath, inputConfigPath);
		}
		List<InputData> data = dbAccessEngine.getData();

		if (executionEngine == null) {
			executionEngine = new RuleExecutionEngine(functionsPath);
		}

		List<Result> results = executionEngine.executeRules(rules, data);

		Document resultsDoc = DocumentHelper.createDocument();
		for (Result result : results) {

		}
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

	/**
	 * @return the dbAccessEngine
	 */
	public DatabaseAccessEngine getDbAccessEngine() {
		return dbAccessEngine;
	}

	/**
	 * @param dbAccessEngine
	 *            the dbAccessEngine to set
	 */
	public void setDbAccessEngine(DatabaseAccessEngine dbAccessEngine) {
		this.dbAccessEngine = dbAccessEngine;
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
}
