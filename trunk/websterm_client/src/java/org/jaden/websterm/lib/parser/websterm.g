header {
package org.jaden.websterm.lib.parser;
}

class WebstermDefinitionParser extends Parser;
options {

}

tokens {
	RULE = "rule";
	NOT = "not";
	AND = "and";
	OR = "or";
}

expr : (rule)+
	;

rule : rule_decl COLON^
	impl
	;

rule_decl : RULE! ID LPAREN! params RPAREN!
	;

params : ("high"|"medium"|"low")
	;

impl : (NOT)? (func (AND | OR)?)+ ";"
	;

func : ID LPAREN! func_params RPAREN!
	;

func_params : ((STRING | NUM) (","!)?)*
	;

class WebstermDefinitionLexer extends Lexer;

LPAREN
options {
	paraphrase = "(";
}
	: '('
	;

RPAREN
options {
	paraphrase = ")";
}
	: ')'
	;

COLON
options {
	paraphrase = ":";
}
	: ':'
	;

WS : ( ' ' | '\t' | '\n' { newline(); } | '\r' )+
	{ $setType(Token.SKIP); }
	;

ID : 'a'..'z' ('a'..'z' | '0'..'9' | '_')*
	;

STRING : '"' ('a'..'z' | 'A'..'Z' | '0'..'9' | '.' | ':' | '/' | '\\' )+ '"'
	;

NUM : ('0'..'9')* ('.' ('0'..'9')*)?
	;