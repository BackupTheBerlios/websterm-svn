/**
 *
 */
package org.jaden.websterm.lib.model;

import java.util.List;

/**
 * @author jack
 *
 */
public class Function {
	public static final String AND = "and";

	public static final String OR = "or";

	public static final String END = "end";

	private String name;

	private List<String> params;

	private boolean reverse;

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
	 * @return the params
	 */
	public List<String> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(List<String> params) {
		this.params = params;
	}

	/**
	 * @return the not
	 */
	public boolean isReverse() {
		return reverse;
	}

	/**
	 * @param not
	 *            the not to set
	 */
	public void setReverse(boolean not) {
		this.reverse = not;
	}
}
