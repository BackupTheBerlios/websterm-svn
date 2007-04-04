/**
 *
 */
package org.jaden.websterm.lib.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

			List<Function> functions = new ArrayList<Function>();

			// Get the priority if it exists
			child = child.getNextSibling();
			int type = child.getType();
			if (type == WebstermDefinitionParserTokenTypes.HIGH
					|| type == WebstermDefinitionParserTokenTypes.MEDIUM
					|| type == WebstermDefinitionParserTokenTypes.LOW) {
				String priority = child.getText();
				rule.setPriority(priority);
			} else if (type == WebstermDefinitionParserTokenTypes.NOT) {
				Function func = new Function();
				func.setReverse(true);
			}

			rules.add(rule);
			ast = (CommonAST) ast.getNextSibling();
		}

		return rules;
	}
}
