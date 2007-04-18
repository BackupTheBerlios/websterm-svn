/**
 *
 */
package org.jaden.websterm.lib.engines;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jaden.websterm.lib.model.InputData;

/**
 * @author jack
 * 
 */
public class DatabaseAccessEngine {
	private SessionFactory sessionFactory;

	private Configuration configuration;

	private List fields;

	private boolean initialized = false;

	public void init(String configPath, String inputConfigPath) {
		sessionFactory = new Configuration().configure(configPath)
				.buildSessionFactory();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new FileInputStream(inputConfigPath));
			fields = doc.getRootElement().elements("field");
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (DocumentException exception) {
			exception.printStackTrace();
		}

		setInitialized(true);
	}

	public List<InputData> getData() {
		Session session = sessionFactory.openSession();
		List<InputData> data = new ArrayList<InputData>();
		Iterator iterator = fields.iterator();
		while (iterator.hasNext()) {
			Element elem = (Element) iterator.next();
			String table = elem.attributeValue("table");
			String column = elem.attributeValue("column");
			List tempData = session.createSQLQuery(
					"SELECT " + column + " FROM " + table).list();
			Iterator i = tempData.iterator();
			while (i.hasNext()) {
				Object obj = i.next();
				InputData d = new InputData();
				d.setData(obj.toString());
				data.add(d);
			}
		}

		return data;
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

	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * @param initialized
	 *            the initialized to set
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * @return the fields
	 */
	public List getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List fields) {
		this.fields = fields;
	}
}
