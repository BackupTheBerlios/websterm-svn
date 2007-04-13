/**
 *
 */
package org.jaden.websterm.lib.engines;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jaden.websterm.lib.model.DatabaseConfiguration;

/**
 * @author jack
 *
 */
public class DatabaseAccessEngine {
	private SessionFactory sessionFactory;

	private Configuration configuration;

	public void init(String configPath) {
		sessionFactory = new Configuration().configure(configPath)
				.buildSessionFactory();
	}

	public List<DatabaseConfiguration.DatabaseField> fetchFields() {

		return null;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration
	 *            the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
