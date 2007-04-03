/**
 *
 */
package org.jaden.websterm.client.action;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author jack
 *
 */
public class SetupAction extends BaseAction {
	private FileBean ruleDefinitionFile;

	private String fileContent;

	private static Log log = LogFactory.getLog(SetupAction.class);

	public static final String RULEDEF_FILE_NAME = "rules.wbs";

	@DefaultHandler
	public Resolution setupIndex() {
		return new ForwardResolution("/WEB-INF/pages/setup/index.jsp");
	}

	public Resolution uploadRuleDefinition() throws Exception {
		InputStream stream = ruleDefinitionFile.getInputStream();
		String configPath = getContext().getServletContext().getRealPath(
				"WEB-INF/config")
				+ "/" + RULEDEF_FILE_NAME;

		// Parse and store the rules for later execution.
		fileContent = IOUtils.toString(stream);
		fileContent = StringUtils.replace(fileContent, System
				.getProperty("line.separator"), "<br/>");
		fileContent = fileContent.replaceAll("\\s", "&nbsp;");

		// Save the rules as a file for future use.
		OutputStream outStream = new FileOutputStream(configPath);
		IOUtils.write(fileContent, outStream);
		IOUtils.closeQuietly(outStream);
		IOUtils.closeQuietly(stream);

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
}
