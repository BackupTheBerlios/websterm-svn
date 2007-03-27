header {
package org.jaden.websterm.lib.parser;
}

class WebstermParser extends Parser;
options {

}

tokens {
	RULE = "rule";
	LEFT_PAREN = "(";
	RIGHT_PAREN = ")";
	COLON = ":";
}

rule : RULE! ID LEFT_PAREN params RIGHT_PAREN COLON
	;

params : ("high"|"medium"|"low" ",")? ("document"|"sentence"|"word")?
	;

class WebstermLexer extends Lexer;

WS : ( ' ' | '\t' | '\n' { newline(); } | '\r' )+
	{ $setType(Token.SKIP); }
	;

ID : 'a'..'z' ('a'..'z' | '0'..'9')*
	;
