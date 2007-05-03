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
}

expr : (rule SEMICOLON!)+
	;

rule : rule_decl COLON^
	impl
	;

rule_decl : RULE! ID^ LPAREN! params RPAREN!
	;

params : (ID (COMMA!)?)*
	;

impl : (((NOT^)? func) (AND^ | OR^)?)+
	;

func : ID^ LPAREN! func_params RPAREN!
	;

func_params : ((STRING | NUM | REGEX) (COMMA!)?)*
	;

class WebstermDefinitionLexer extends Lexer;

options {
	k = 2;
}

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

NUM : ('0'..'9')* ('.' ('0'..'9')*)?
	;

REGEX : '/' (ESC|~('"'|'\\'|'\n'|'\r'|'/'))* '/'
	;

// an identifier.  Note that testLiterals is set to true!  This means
// that after we match the rule, we look in the literals table to see
// if it's a literal or really an identifer
ID
	options {testLiterals=true;}
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

// string literals
STRING
	:	'"' (ESC|~('"'|'\\'|'\n'|'\r'))* '"'
	;

// escape sequence -- note that this is protected; it can only be called
//   from another lexer rule -- it will not ever directly return a token to
//   the parser
// There are various ambiguities hushed in this rule.  The optional
// '0'...'9' digit matches should be matched here rather than letting
// them go back to STRING_LITERAL to be matched.  ANTLR does the
// right thing by matching immediately; hence, it's ok to shut off
// the FOLLOW ambig warnings.
protected
ESC
	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	('u')+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
		|	'0'..'3'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
				(
					options {
						warnWhenFollowAmbig = false;
					}
				:	'0'..'7'
				)?
			)?
		|	'4'..'7'
			(
				options {
					warnWhenFollowAmbig = false;
				}
			:	'0'..'7'
			)?
		)
	;

// hexadecimal digit (again, note it's protected!)
protected
HEX_DIGIT
	:	('0'..'9'|'A'..'F'|'a'..'f')
	;