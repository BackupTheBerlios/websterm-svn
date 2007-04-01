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
public class InputData extends Model implements Serializable {
	private String name;

	private List<String> data;

	/**
	 * @return the data
	 */
	public List<String> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<String> data) {
		this.data = data;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
