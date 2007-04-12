/**
 *
 */
package org.jaden.websterm.lib.model;

import java.io.Serializable;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentType;

/**
 * @author jack
 *
 */
public class DatabaseConfiguration implements Serializable {
	private String databaseType;

	private String connectionUrl;

	private String driverClass;

	private String username;

	private String password;

	private List<DatabaseField> fields;

	/**
	 * @return the databaseType
	 */
	public String getDatabaseType() {
		return databaseType;
	}

	/**
	 * @param databaseType
	 *            the databaseType to set
	 */
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	/**
	 * @return the connectionUrl
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}

	/**
	 * @param connectionUrl
	 *            the connectionUrl to set
	 */
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
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

	public Document getHibernateConfiguration() {
		Document doc = DocumentHelper.createDocument();
		DocumentType docType = new DOMDocumentType();
		docType.setPublicID("-//Hibernate/Hibernate Configuration DTD//EN");
		docType.setSystemID("http://hibernate.sourceforge.net/hibernate"
				+ "-configuration-3.0.dtd");
		docType.setName("hibernate-configuration");
		doc.setDocType(docType);

		Element hibernateConfig = doc.addElement("hibernate-configuration");
		Element sessionFactory = hibernateConfig.addElement("session-factory");
		Element prop = sessionFactory.addElement("property");
		prop.addAttribute("name", "hibernate.connection.driver_class");
		prop.setText(driverClass);
		prop = sessionFactory.addElement("property");
		prop.addAttribute("name", "hibernate.connection.url");
		prop.setText(connectionUrl);
		prop = sessionFactory.addElement("property");
		prop.addAttribute("name", "hibernate.connection.username");
		prop.setText(username);
		prop = sessionFactory.addElement("property");
		prop.addAttribute("name", "hibernate.connection.password");
		prop.setText(password);
		prop = sessionFactory.addElement("property");
		prop.addAttribute("name", "hibernate.dialect");
		prop.setText(databaseType);

		return doc;
	}

	public Document getFieldsConfiguration() {
		Document doc = DocumentHelper.createDocument();

		Element database = doc.addElement("fields");
		for (DatabaseField field : fields) {
			if (field.isSelected()) {
				Element fieldElem = database.addElement("field");
				fieldElem.addAttribute("table", field.getTable());
				fieldElem.addAttribute("column", field.getColumn());
			}
		}

		return doc;
	}

	public static class DatabaseField implements Serializable {
		private String table;

		private String column;

		private boolean selected = false;

		/**
		 * @return the field
		 */
		public String getColumn() {
			return column;
		}

		/**
		 * @param field
		 *            the field to set
		 */
		public void setColumn(String field) {
			this.column = field;
		}

		/**
		 * @return the selected
		 */
		public boolean isSelected() {
			return selected;
		}

		/**
		 * @param selected
		 *            the selected to set
		 */
		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		/**
		 * @return the table
		 */
		public String getTable() {
			return table;
		}

		/**
		 * @param table
		 *            the table to set
		 */
		public void setTable(String table) {
			this.table = table;
		}
	}

	/**
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return driverClass;
	}

	/**
	 * @param driverClass
	 *            the driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
