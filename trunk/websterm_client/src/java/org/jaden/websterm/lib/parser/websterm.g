header {
package org.jaden.websterm.lib.parser;
}

class WebstermDefinitionParser extends Parser;
options {
	buildAST = true;
	k = 4;
}

tokens {
	RULE = "rule";
	NOT = "not";
	AND = "and";
	OR = "or";
	HIGH = "high";
	MEDIUM = "medium";
	LOW = "low";
}

expr : (rule)+
	;

rule : rule_decl COLON^
	impl
	;

rule_decl : RULE! ID^ LPAREN! params RPAREN!
	;

params : (HIGH|MEDIUM|LOW)?
	;

impl : (((NOT^)? func) (AND^ | OR^)?)+ SEMICOLON!
	;

func : ID^ LPAREN! func_params RPAREN!
	;

func_params : ((STRING | NUM) (COMMA!)?)*
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

SEMICOLON
options {
	paraphrase = ";";
}
	: ';'
	;

COMMA
options {
	paraphrase = ",";
}
	: ','
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