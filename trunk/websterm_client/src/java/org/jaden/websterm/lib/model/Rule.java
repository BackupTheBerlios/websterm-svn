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
public class Rule implements Serializable {
	private String name;

	private String priority;

	private List<Function> functions;

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

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the functions
	 */
	public List<Function> getFunctions() {
		return functions;
	}

	/**
	 * @param functions
	 *            the functions to set
	 */
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
}
