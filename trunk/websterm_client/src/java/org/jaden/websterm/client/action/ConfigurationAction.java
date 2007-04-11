/**
 *
 */
package org.jaden.websterm.client.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jaden.websterm.lib.model.DatabaseConfiguration;
import org.jaden.websterm.lib.model.FileConfiguration;

/**
 * @author jack
 *
 */
public class ConfigurationAction extends BaseAction {
	private static Log log = LogFactory.getLog(ConfigurationAction.class);

	private boolean database;

	private boolean file;

	private DatabaseConfiguration databaseConfiguration;

	private FileConfiguration fileConfiguration;

	public Resolution getConfigurationDetails() {
		return new ForwardResolution("/WEB-INF/pages/setup/configuration.jsp");
	}

	/**
	 * @return the database
	 */
	public boolean isDatabase() {
		return database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(boolean database) {
		this.database = database;
	}

	/**
	 * @return the file
	 */
	public boolean isFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(boolean file) {
		this.file = file;
	}

	/**
	 * @return the databaseConfiguration
	 */
	public DatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	/**
	 * @param databaseConfiguration
	 *            the databaseConfiguration to set
	 */
	public void setDatabaseConfiguration(
			DatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}

	/**
	 * @return the fileConfiguration
	 */
	public FileConfiguration getFileConfiguration() {
		return fileConfiguration;
	}

	/**
	 * @param fileConfiguration
	 *            the fileConfiguration to set
	 */
	public void setFileConfiguration(FileConfiguration fileConfiguration) {
		this.fileConfiguration = fileConfiguration;
	}
}
