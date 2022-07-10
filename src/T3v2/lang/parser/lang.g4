/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

grammar lang;

@parser::header
{
    package lang.parser;
}

@lexer::header
{
    package lang.parser;
}

/* Regra da gramática */

/* start */
prog: ( data )* ( func )*;

/* data */
data: DATA IDTYPE LEFTBRACKET ( decl )* RIGHTBRACKET;
decl: (ID|IDTYPE) DOUBLECOLON type SEMICOLON;

/* func */
func: (ID|IDTYPE) LEFTPARENT (params)? RIGHTPARENT (COLON type (COMMA type)*)? LEFTBRACKET ( cmd )* RIGHTBRACKET;
params: (ID|IDTYPE) DOUBLECOLON type (COMMA (ID|IDTYPE) DOUBLECOLON type)*;

/* type */
type: type LEFTBRACE RIGHTBRACE
	| btype
	;

/* btype */		
btype: INT
	| CHAR
	| BOOL
	| FLOAT
	| IDTYPE
	;

/* cmd */	
cmd: LEFTBRACKET ( cmd )* RIGHTBRACKET
	| IF LEFTPARENT exp RIGHTPARENT cmd
	| IF LEFTPARENT exp RIGHTPARENT cmd ELSE cmd
	| ITERATE LEFTPARENT exp RIGHTPARENT cmd
	| READ lvalue SEMICOLON
	| PRINT exp SEMICOLON
	| RETURN exp ( COMMA exp )* SEMICOLON
	| lvalue ASSIGN exp SEMICOLON
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT (LESS lvalue ( COMMA lvalue )* GREATER)? SEMICOLON
	;
	
/* exp */	
exp: exp AND exp
	| rexp
	;

/* rexp */	
rexp: aexp LESS aexp
	| rexp EQ aexp
	| rexp NEQ aexp
	| aexp
	;
	
/* aexp */	
aexp: aexp PLUS mexp
	| aexp MINUS mexp
	| mexp
	;

/* mexp */	
mexp: mexp MULT sexp
	| mexp DIV sexp
	| mexp MODULE sexp
	| sexp
	;

/* sexp */	
sexp: NOT sexp
	| MINUS sexp
	| TRUE
	| FALSE
	| NULL
	| INTEGER
	| DOUBLE
	| CARACTER
	| pexp
	;

/* pexp */	
pexp: lvalue
	| LEFTPARENT exp RIGHTPARENT
	| NEW type (LEFTBRACE exp RIGHTBRACE)?
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT LEFTBRACE exp RIGHTBRACE
	;

/* lvalue */	
lvalue: (ID|IDTYPE)
	| lvalue LEFTBRACE exp RIGHTBRACE
	| lvalue DOT (ID|IDTYPE)
	;

/* exps */	
exps: exp ( COMMA exp )*;


/* Regras léxicas */

//
INT: 			'Int';
FLOAT: 			'Float';
DATA: 			'data';
CHAR: 			'Char';
BOOL: 			'Bool';
TRUE: 			'true';
FALSE: 			'false';
NULL: 			'null';
//
LEFTPARENT: 	'(';
RIGHTPARENT:	')';
LEFTBRACE: 		'[';
RIGHTBRACE: 	']';
LEFTBRACKET: 	'{';
RIGHTBRACKET: 	'}';
//
GREATER: 		'>';
LESS: 			'<';
//
DOT: 			'.';
COMMA: 			',';
COLON: 			':';
SEMICOLON: 		';';
DOUBLECOLON: 	'::';
//
ASSIGN: 		'=';
EQ: 			'==';
NEQ:			'!=';
//
PLUS: 			'+';
MINUS: 			'-';
MULT: 			'*';
DIV: 			'/';
MODULE: 		'%';
//
AND: 			'&&';
NOT: 			'!';
//
IF: 			'if';
ELSE: 			'else';
ITERATE: 		'iterate';
READ: 			'read';
PRINT: 			'print';
RETURN: 		'return';
NEW: 			'new';
//

INTEGER: 		('0'..'9')+;
DOUBLE: 		('0'..'9')* '.'('0'..'9')+;
CARACTER: 		'\'' ( ~[\\'] | '\\n' | '\\t' | '\\b' | '\\r' | '\\\\' | '\\\'' ) '\'' ;

FIMDELINHA: 	        '\r'? '\n' -> skip;
BRANCOS: 		        [ \t]+ -> skip;
SINGLELINECOMMENT :     '--' ~[\r\n]* -> channel(HIDDEN);
MULTIPLELINESCOMMENT:   '{-' .*? '-}' -> channel(HIDDEN);

ID: 			[a-z] [a-zA-Z0-9_]* ;
IDTYPE: 		[A-Z] [a-zA-Z0-9_]* ;