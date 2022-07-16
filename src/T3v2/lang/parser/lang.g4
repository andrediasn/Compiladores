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

/* Gramatic Rules */

/* start */
program: ( data )* ( func )+;			

/* data */																				
data: DATA IDTYPE LEFTBRACKET ( decl )+ RIGHTBRACKET; 																		
decl: (ID|IDTYPE) DOUBLECOLON type SEMICOLON; 		

/* func */
func: (ID|IDTYPE) LEFTPARENT (params)? RIGHTPARENT (COLON type (COMMA type)*)? LEFTBRACKET ( cmd )* RIGHTBRACKET; 			
params: param (COMMA param)*; 
param: (ID|IDTYPE) DOUBLECOLON type;	

/* type */
type: btype (brace)*;		
brace: LEFTBRACE RIGHTBRACE;	

/* btype */	
btype: INT 		#tyInt
	| CHAR 		#tyChar
	| BOOL 		#tyBool
	| FLOAT 	#tyFloat
	| IDTYPE 	#tyIDType
	;

/* cmd */
cmd: LEFTBRACKET ( cmd )* RIGHTBRACKET				#cmdArray
	| IF LEFTPARENT exp RIGHTPARENT cmd 			#if
	| IF LEFTPARENT exp RIGHTPARENT cmd ELSE cmd 	#ifElse										
	| ITERATE LEFTPARENT exp RIGHTPARENT cmd		#iterate
	| READ lvalue SEMICOLON							#read
	| PRINT exp SEMICOLON 							#print
	| RETURN exp ( COMMA exp )* SEMICOLON 			#return
	| lvalue ATTR exp SEMICOLON 					#attr
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT (LESS lvalue ( COMMA lvalue )* GREATER)? SEMICOLON		#callCMD
	;

/* exp */	
exps: exp ( COMMA exp )*; 
exp: exp AND exp		#and
	| rexp				#rex
	;
rexp: aexp LESS aexp 	#less
	| rexp EQ aexp		#eq
	| rexp NEQ aexp		#neq
	| aexp 				#aex
	;
aexp: aexp PLUS mexp	#plus
	| aexp MINUS mexp	#minus
	| mexp 				#mex
	;
mexp: mexp MULT sexp	#mult
	| mexp DIV sexp		#div
	| mexp MOD sexp		#mod
	| sexp 				#sex
	;
sexp: NOT sexp			#not
	| MINUS sexp		#SMinus
	| TRUE				#true
	| FALSE				#false
	| NULL				#null
	| INTEGER			#integer
	| DOUBLE			#double
	| CARACTER			#caracter
	| pexp				#pex																																													
	;																																																
pexp: lvalue 																	#lvalues																								
	| LEFTPARENT exp RIGHTPARENT 												#expression
	| NEW type (LEFTBRACE exp RIGHTBRACE)? 										#new
	| (ID|IDTYPE) LEFTPARENT (exps)? RIGHTPARENT LEFTBRACE exp RIGHTBRACE 		#callExp
	;

/* lvalue */	
lvalue: (ID|IDTYPE) 							#lvalueIds
	| lvalue LEFTBRACE exp RIGHTBRACE 			#accessArray
	| lvalue DOT (ID|IDTYPE) 					#accessData
	;
																									


/* Lexic Rules */

INT: 			'Int';
FLOAT: 			'Float';
DATA: 			'data';
CHAR: 			'Char';
BOOL: 			'Bool';
TRUE: 			'true';
FALSE: 			'false';
NULL: 			'null';
LEFTPARENT: 	'(';
RIGHTPARENT:	')';
LEFTBRACE: 		'[';
RIGHTBRACE: 	']';
LEFTBRACKET: 	'{';
RIGHTBRACKET: 	'}';
GREATER: 		'>';
LESS: 			'<';
DOT: 			'.';
COMMA: 			',';
COLON: 			':';
SEMICOLON: 		';';
DOUBLECOLON: 	'::';
ATTR: 			'=';
EQ: 			'==';
NEQ:			'!=';
PLUS: 			'+';
MINUS: 			'-';
MULT: 			'*';
DIV: 			'/';
MOD: 			'%';
AND: 			'&&';
NOT: 			'!';
IF: 			'if';
ELSE: 			'else';
ITERATE: 		'iterate';
READ: 			'read';
PRINT: 			'print';
RETURN: 		'return';
NEW: 			'new';
INTEGER: 		('0'..'9')+;
DOUBLE: 		('0'..'9')* '.'('0'..'9')+;
CARACTER: 		'\'' ( ~[\\'] | '\\n' | '\\t' | '\\b' | '\\r' | '\\\\' | '\\\'' ) '\'' ;

ENDLINE: 	        '\r'? '\n' -> skip;
EMPTY: 		        [ \t]+ -> skip;
LINECOMMENT:     	'--' ~[\r\n]* -> channel(HIDDEN);
MULTLINESCOMMENT:   '{-' .*? '-}' -> channel(HIDDEN);

ID: 			[a-z] [a-zA-Z0-9_]* ;
IDTYPE: 		[A-Z] [a-zA-Z0-9_]* ;