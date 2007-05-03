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
	private String name;

	private List<Function> functions;

	private TreeNode functionsTree;

	private List<String> fields;

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

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}
}
