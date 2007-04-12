/**
 *
 */
package org.jaden.websterm.client.action;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jaden.websterm.lib.model.DatabaseConfiguration;
import org.jaden.websterm.lib.model.FileConfiguration;
import org.jaden.websterm.lib.model.DatabaseConfiguration.DatabaseField;

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

	private List<DatabaseField> fields;

	@DefaultHandler
	public Resolution getDbConfigDetails() {
		return new ForwardResolution("/WEB-INF/pages/setup/dbconfig.jsp");
	}

	public Resolution getFileConfigDetails() {
		return new ForwardResolution("/WEB-INF/pages/setup/fileconfig.jsp");
	}

	public Resolution fetchFields() throws Exception {
		Class.forName(databaseConfiguration.getDriverClass());
		Connection conn = DriverManager.getConnection(databaseConfiguration
				.getConnectionUrl(), databaseConfiguration.getUsername(),
				databaseConfiguration.getPassword());

		ResultSet rs = conn.getMetaData().getColumns(null, "%", "%", "%");
		fields = new ArrayList<DatabaseField>();
		while (rs.next()) {
			String table = rs.getString("TABLE_NAME");
			String column = rs.getString("COLUMN_NAME");
			DatabaseField field = new DatabaseField();
			field.setColumn(column.toUpperCase());
			field.setTable(table.toUpperCase());
			fields.add(field);
		}

		databaseConfiguration.setFields(fields);

		return new ForwardResolution("/WEB-INF/pages/setup/fields.jsp");
	}

	public Resolution configureDatabase() throws Exception {
		Document doc = databaseConfiguration.getHibernateConfiguration();
		String configFilePath = getContext().getServletContext().getRealPath(
				"WEB-INF/config/hibernate.cfg.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileWriter(configFilePath), format);
		writer.write(doc);
		writer.flush();

		doc = databaseConfiguration.getFieldsConfiguration();
		String fieldsConfigPath = getContext().getServletContext().getRealPath(
				"WEB-INF/config/fields.xml");
		writer = new XMLWriter(new FileWriter(fieldsConfigPath), format);
		writer.write(doc);
		writer.flush();

		return new StreamingResolution("text/plain",
				"Database Configuration Successfull");
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

	/**
	 * @return the fields
	 */
	public List<DatabaseField> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<DatabaseField> fields) {
		this.fields = fields;
	}
}
