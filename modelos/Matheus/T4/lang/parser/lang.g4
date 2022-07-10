/*
 * Alunos:
 *
 * Daniel Souza Ferreira               201665519B
 * Matheus de Oliveira Carvalho        201665568C 
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

prog: ( data )* ( func )*;																									
data: DATA IDTYPE LEFTBRACKET ( decl )* RIGHTBRACKET; 																		
decl: (ID|IDTYPE) DOUBLECOLON type SEMICOLON; 																				
func: (ID|IDTYPE) LEFTPARENT (params)? RIGHTPARENT (COLON type (COMMA type)*)? LEFTBRACKET ( cmd )* RIGHTBRACKET; 			
params: param (COMMA param)*; 
param: (ID|IDTYPE) DOUBLECOLON type;									
type: btype (brace)*;		
brace: LEFTBRACE RIGHTBRACE;																						
btype: INT 																													#typeInt
	| CHAR 																													#typeChar
	| BOOL	 																												#typeBool
	| FLOAT	 																												#typeFloat
	| IDTYPE 																												#typeIDType
	;
cmd: LEFTBRACKET ( cmd )* RIGHTBRACKET 																						#cmdArray
	| IF LEFTPARENT exp RIGHTPARENT cmd 																					#if
	| IF LEFTPARENT exp RIGHTPARENT cmd ELSE cmd 																			#ifElse										
	| ITERATE LEFTPARENT exp RIGHTPARENT cmd																				#iterate
	| READ lvalue SEMICOLON																									#read
	| PRINT exp SEMICOLON 																									#print
	| RETURN exp ( COMMA exp )* SEMICOLON 																					#return
	| lvalue ASSIGN exp SEMICOLON 																							#assign
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT (LESS lvalue ( COMMA lvalue )* GREATER)? SEMICOLON							#callCMD
	;
exp: exp AND exp																											#and
	| rexp																													#rex
	;
rexp: aexp LESS aexp 																										#less
	| rexp EQ aexp																											#eq
	| rexp NEQ aexp																											#neq
	| aexp 																													#aex
	;
aexp: aexp PLUS mexp																										#plus
	| aexp MINUS mexp																										#minus
	| mexp 																													#mex
	;
mexp: mexp MULT sexp																										#mult
	| mexp DIV sexp																											#div
	| mexp MODULE sexp																										#module
	| sexp 																													#sex
	;
sexp: NOT sexp																												#not
	| MINUS sexp																											#neg
	| TRUE																													#true
	| FALSE																													#false
	| NULL																													#null
	| INTEGER																												#integer
	| DOUBLE																												#double
	| CARACTER																												#caracter
	| pexp					 																								#pex																																													
	;																																																
pexp: lvalue 																												#lvalues																								
	| LEFTPARENT exp RIGHTPARENT 																							#expression
	| NEW type (LEFTBRACE exp RIGHTBRACE)? 																					#new
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT LEFTBRACE exp RIGHTBRACE 													#callExp
	;
lvalue: (ID|IDTYPE) 																										#lvalueIds
	| lvalue LEFTBRACE exp RIGHTBRACE 																						#selectorArray
	| lvalue DOT (ID|IDTYPE) 																								#selectorData
	;
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