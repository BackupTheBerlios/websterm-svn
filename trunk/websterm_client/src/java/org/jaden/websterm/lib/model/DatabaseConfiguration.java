/**
 *
 */
package org.jaden.websterm.lib.model;

import java.io.Serializable;
import java.util.List;

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

	public static class DatabaseField implements Serializable {
		private String table;

		private String field;

		private boolean selected;

		/**
		 * @return the field
		 */
		public String getField() {
			return field;
		}

		/**
		 * @param field
		 *            the field to set
		 */
		public void setField(String field) {
			this.field = field;
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
