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
	NOT = "!";
	CONFIG_HEADER = "[Config]";
	RULE_HEADER = "[Rules]";
	ASSIGN = "=";
	AND = "and";
	OR = "or";
}

expr : CONFIG_HEADER
	(config)+
	RULE_HEADER
	(rule)+
	;

config : ID ASSIGN^ VALUE;

rule : RULE ID LEFT_PAREN^ params RIGHT_PAREN^ COLON
	impl
	;

params : ("high"|"medium"|"low")
	;

impl : (NOT)? (func (AND | OR)?)+
	;

func : ID LEFT_PAREN^ func_params RIGHT_PAREN^
	;

func_params : ((ID | NUM) (",")?)*
	;

class WebstermLexer extends Lexer;

WS : ( ' ' | '\t' | '\n' { newline(); } | '\r' )+
	{ $setType(Token.SKIP); }
	;

ID : 'a'..'z' ('a'..'z' | '0'..'9')*
	;

VALUE : ('a'..'z' | '0'..'9' | '.' | ':' | '/' | '\\' )+
	;

NUM : ('0'..'9')* ('.' ('0'..'9')*)?
	;