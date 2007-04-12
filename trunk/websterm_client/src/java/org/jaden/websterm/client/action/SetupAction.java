/**
 *
 */
package org.jaden.websterm.client.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jaden.websterm.lib.model.Rule;
import org.jaden.websterm.lib.parser.WebstermParser;

/**
 * @author jack
 *
 */
public class SetupAction extends BaseAction {
	private FileBean ruleDefinitionFile;

	private WebstermParser parser;

	private List<Rule> rules;

	private String fileContent;

	private static Log log = LogFactory.getLog(SetupAction.class);

	public static final String RULEDEF_FILE_NAME = "rules.wbs";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution("/WEB-INF/pages/setup/index.jsp");
	}

	public Resolution uploadRuleDefinition() throws Exception {
		InputStream stream = ruleDefinitionFile.getInputStream();
		String configPath = getContext().getServletContext().getRealPath(
				"WEB-INF/config")
				+ "/" + RULEDEF_FILE_NAME;

		// Parse and store the rules for later execution.
		String contents = IOUtils.toString(stream);
		fileContent = new String(contents);
		contents = StringUtils.replace(contents, System
				.getProperty("line.separator"), "<br/>");
		contents = contents.replaceAll("\\s", "&nbsp;");

		// Save the rules as a file for future use.
		OutputStream outStream = new FileOutputStream(configPath);
		IOUtils.write(fileContent, outStream);
		IOUtils.closeQuietly(outStream);
		IOUtils.closeQuietly(stream);

		// Parse the rules
		rules = parser.parseDefinition(fileContent);

		return new ForwardResolution("/WEB-INF/pages/setup/rules.jsp");
	}

	public Resolution viewRules() throws Exception {
		String configPath = getContext().getServletContext().getRealPath(
				"WEB-INF/config")
				+ "/" + RULEDEF_FILE_NAME;
		FileInputStream stream = new FileInputStream(configPath);
		String contents = IOUtils.toString(stream);

		rules = parser.parseDefinition(contents);

		return new ForwardResolution("/WEB-INF/pages/setup/rules.jsp");
	}

	/**
	 * @return the ruleDefinitionFile
	 */
	public FileBean getRuleDefinitionFile() {
		return ruleDefinitionFile;
	}

	/**
	 * @param ruleDefinitionFile
	 *            the ruleDefinitionFile to set
	 */
	public void setRuleDefinitionFile(FileBean ruleDefinitionFile) {
		this.ruleDefinitionFile = ruleDefinitionFile;
	}

	/**
	 * @return the fileContent
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent
	 *            the fileContent to set
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
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
	@SpringBean("webstermParser")
	public void setParser(WebstermParser parser) {
		this.parser = parser;
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
