/**
 *
 */
package org.jaden.websterm.lib.model;

import java.io.Serializable;
import java.util.List;

import org.jaden.websterm.lib.datastructure.TreeNode;

/**
 * @author jack
 *
 */
public class Rule implements Serializable {
	public static final String HIGH_PRIORITY = "high";

	public static final String MEDIUM_PRIORITY = "medium";

	public static final String LOW_PRIORITY = "low";

	private String name;

	private String priority;

	private List<Function> functions;

	private TreeNode functionsTree;

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

	/**
	 * @return the functionTree
	 */
	public TreeNode getFunctionsTree() {
		return functionsTree;
	}

	/**
	 * @param functionTree
	 *            the functionTree to set
	 */
	public void setFunctionsTree(TreeNode functionTree) {
		this.functionsTree = functionTree;
	}
}
