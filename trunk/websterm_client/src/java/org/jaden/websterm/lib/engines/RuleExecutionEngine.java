/**
 * 
 */
package org.jaden.websterm.lib.engines;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaden.websterm.lib.datastructure.TreeNode;
import org.jaden.websterm.lib.exception.FunctionNotValidException;
import org.jaden.websterm.lib.function.FunctionImpl;
import org.jaden.websterm.lib.model.Function;
import org.jaden.websterm.lib.model.InputData;
import org.jaden.websterm.lib.model.Result;
import org.jaden.websterm.lib.model.Rule;

/**
 * @author jack
 * 
 */
public class RuleExecutionEngine {
	private String functionsDefinitionPath;

	private Element functionRoot;

	public RuleExecutionEngine(String functionsDefinitionPath) {
		this.functionsDefinitionPath = functionsDefinitionPath;
		SAXReader reader = new SAXReader();
		try {
			functionRoot = reader.read(
					new FileInputStream(this.functionsDefinitionPath))
					.getRootElement();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (DocumentException exception) {
			exception.printStackTrace();
		}
	}

	public List<Result> executeRules(List<Rule> rules, List<InputData> data) {
		List<Result> results = new ArrayList<Result>();
		boolean valid = true;
		for (Rule rule : rules) {
			TreeNode functions = rule.getFunctionsTree();
			for (InputData dat : data) {
				valid = valid && executeFunctions(results, functions, dat);
			}
		}

		return results;
	}

	private FunctionImpl getFunctionImplementation(String functionName) {
		Element elem = functionRoot.elementByID(functionName);
		String implClassName = elem.elementText("implClass");
		FunctionImpl impl = null;
		try {
			impl = (FunctionImpl) Class.forName(implClassName).newInstance();
		} catch (InstantiationException exception) {
			exception.printStackTrace();
		} catch (IllegalAccessException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}

		return impl;
	}

	private boolean executeFunctions(List<Result> results, TreeNode functions,
			InputData data) {
		Object value = functions.getValue();
		boolean valid = true;
		if (value != null) {
			if (value.equals("or")) {
				valid = executeFunctions(results, functions.getLeftChild(),
						data);
				valid = valid
						|| executeFunctions(results, functions.getRightChild(),
								data);
			} else if (value.equals("and")) {
				valid = executeFunctions(results, functions.getLeftChild(),
						data);
				valid = valid
						&& executeFunctions(results, functions.getRightChild(),
								data);
			} else if (value instanceof Function) {
				Function function = (Function) value;
				FunctionImpl impl = getFunctionImplementation(function
						.getName());
				impl.setInputData(data);
				try {
					valid = impl.execute(function.getParams());
				} catch (FunctionNotValidException exception) {
					exception.printStackTrace();
				}

				if (!valid) {
					Result result = new Result();
					result.setOriginalData(data);
					result.setMessage(impl.explain());
					results.add(result);
				}
			}
		}
		return valid;
	}

	/**
	 * @return the functionDefinitionPath
	 */
	public String getFunctionDefinitionPath() {
		return functionsDefinitionPath;
	}

	/**
	 * @param functionDefinitionPath
	 *            the functionDefinitionPath to set
	 */
	public void setFunctionDefinitionPath(String functionDefinitionPath) {
		this.functionsDefinitionPath = functionDefinitionPath;
	}
}
