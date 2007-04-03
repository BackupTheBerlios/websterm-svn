header {
package org.jaden.websterm.lib.parser;
}

class WebstermDefinitionParser extends Parser;
options {

}

tokens {
	RULE = "rule";
	LEFT_PAREN = "(";
	RIGHT_PAREN = ")";
	COLON = ":";
	NOT = "!";
	CONFIG_HEADER = "[Config]";
	RULE_HEADER = "[Rules]";
	ASSIGN = "=";
	AND = "and";
	OR = "or";
}

expr : (rule)+
	;

rule : RULE ID LEFT_PAREN^ params RIGHT_PAREN^ COLON
	impl
	;

params : ("high"|"medium"|"low")
	;

impl : (NOT)? (func (AND | OR)?)+
	;

func : ID LEFT_PAREN^ func_params RIGHT_PAREN^
	;

func_params : ((STRING | NUM) (",")?)*
	;

class WebstermDefinitionLexer extends Lexer;

WS : ( ' ' | '\t' | '\n' { newline(); } | '\r' )+
	{ $setType(Token.SKIP); }
	;

ID : 'a'..'z' ('a'..'z' | '0'..'9')*
	;

STRING : '"' ('a'..'z' | 'A'..'Z' | '0'..'9' | '.' | ':' | '/' | '\\' )+ '"'
	;

NUM : ('0'..'9')* ('.' ('0'..'9')*)?
	;