/**
 *
 */
package org.jaden.websterm.lib.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jaden.websterm.lib.datastructure.TreeNode;
import org.jaden.websterm.lib.model.Function;
import org.jaden.websterm.lib.model.Rule;

import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

/**
 * @author jack
 *
 */
public class WebstermParser {
	public List<Rule> parseDefinition(String input) {
		WebstermDefinitionLexer lexer = new WebstermDefinitionLexer(
				new StringReader(input));
		WebstermDefinitionParser parser = new WebstermDefinitionParser(lexer);
		try {
			parser.expr();
		} catch (RecognitionException exception) {
			exception.printStackTrace();
		} catch (TokenStreamException exception) {
			exception.printStackTrace();
		}

		CommonAST ast = (CommonAST) parser.getAST();
		System.out.println(ast.toStringList());
		List<Rule> rules = new ArrayList<Rule>();
		while (ast != null) {
			AST child = ast.getFirstChild();

			Rule rule = new Rule();
			String ruleName = child.getText();
			rule.setName(ruleName);

			// Get the priority if it exists
			AST priorityNode = child.getFirstChild();
			if (priorityNode != null) {
				String priority = priorityNode.getText();
				rule.setPriority(priority);
			} else {
				rule.setPriority(Rule.MEDIUM_PRIORITY);
			}

			AST ruleImpl = child.getNextSibling();
			List<Function> functions = new ArrayList<Function>();

			TreeNode root = new TreeNode();
			parseRuleImplementation(functions, root, ruleImpl);

			rule.setFunctions(functions);
			rules.add(rule);
			ast = (CommonAST) ast.getNextSibling();
		}

		return rules;
	}

	private void parseRuleImplementation(List<Function> functions,
			TreeNode parent, AST implNode) {
		int type = implNode.getType();
		if (type == WebstermDefinitionParserTokenTypes.AND
				|| type == WebstermDefinitionParserTokenTypes.OR) {
			parent
					.setValue(type == WebstermDefinitionParserTokenTypes.AND ? "and"
							: "or");
			TreeNode leftNode = new TreeNode();
			parent.setLeftChild(leftNode);
			AST leftChild = implNode.getFirstChild();
			parseRuleImplementation(functions, leftNode, leftChild);

			TreeNode rightNode = new TreeNode();
			parent.setRightChild(rightNode);
			AST rightChild = leftChild.getNextSibling();
			parseRuleImplementation(functions, rightNode, rightChild);
		} else {
			Function function = new Function();
			AST functionAST = implNode;
			if (type == WebstermDefinitionParserTokenTypes.NOT) {
				function.setReverse(true);
				functionAST = implNode.getFirstChild();
			}

			function.setName(functionAST.getText());

			List<String> params = new ArrayList<String>();
			AST paramsAST = functionAST.getFirstChild();
			while (paramsAST != null) {
				String parameter = paramsAST.getText();
				params.add(parameter);
				paramsAST = paramsAST.getNextSibling();
			}
			function.setParams(params);

			parent.setValue(function);
			functions.add(function);
		}
	}
}
