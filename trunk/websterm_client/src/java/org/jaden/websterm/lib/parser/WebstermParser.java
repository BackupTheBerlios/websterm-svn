/**
 *
 */
package org.jaden.websterm.lib.parser;

import java.io.StringReader;
import java.util.List;

import org.jaden.websterm.lib.model.Rule;

import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;

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
		System.out.println(ast.getNextSibling().getNumberOfChildren());

		return null;
	}
}
