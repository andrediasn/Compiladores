/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
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

/*Regras da Gramatica*/

prog : ( data )* ( func )*;
data : DATA IDtype LBRACE ( decl )* RBRACE;
decl : (ID|IDtype) COLONCOLON type SEMI;
func : (ID|IDtype) LPAREN (params)? RPAREN (COLON type (COMMA type)*)? LBRACE ( cmd )* RBRACE;
params : param (COMMA param)*;
param : (ID|IDtype) COLONCOLON type;
type : btype (bracks)*;
bracks : LBRACK RBRACK;
btype : INT # intType
    | CHAR # charType
    | BOOL # boolType
    | FLOAT # floatType
    | IDtype # idType
    ;
cmd : LBRACE ( cmd )* RBRACE # cmdList
    | IF LPAREN exp RPAREN cmd # if
    | IF LPAREN exp RPAREN cmd ELSE cmd # ifElse
    | ITERATE LPAREN exp RPAREN cmd # iterate
    | READ lvalue SEMI # read
    | PRINT exp SEMI # print
    | RETURN exp (COMMA exp)* SEMI # return
    | lvalue ASSIGN exp SEMI # atribuir
    | (ID|IDtype) LPAREN (exps)? RPAREN (LT lvalue (COMMA lvalue)* CRETURN)? SEMI # callCmd
    ;
exp : exp AND exp # and
    | rexp # rex
    ;
rexp : aexp LT aexp # lt 
    | rexp EQUAL aexp # equal
    | rexp NOTEQUAL aexp # notEqual
    | aexp # aex
    ;
aexp : aexp ADD mexp # add
    | aexp SUB mexp # sub
    | mexp # mex
    ;
mexp : mexp MUL sexp # mul 
    | mexp DIV sexp # div 
    | mexp MOD sexp # mod 
    | sexp # sex
    ;
sexp : BANG sexp # not
    | SUB sexp # neg
    | TRUE # true
    | FALSE # false
    | NULL_LITERAL # null
    | INT_LITERAL # int
    | FLOAT_LITERAL # float
    | CHAR_LITERAL # char 
    | pexp # pex
    ;
pexp : lvalue # lval
    | LPAREN exp RPAREN  # ex 
    | NEW type (LBRACK exp RBRACK)? # new
    | (ID|IDtype) LPAREN (exps)? RPAREN LBRACK exp RBRACK # callExp 
    ;
lvalue : (ID|IDtype) # lvalueID
    | lvalue LBRACK exp RBRACK # seletorArray
    | lvalue DOT (ID|IDtype) # seletorData
    ;
exps : exp ( COMMA exp )*;

/*Regras lexicas*/

// Keywords

BOOL:               'Bool';
DATA:               'data';
CHAR:               'Char';
ELSE:               'else';
FLOAT:              'Float';
IF:                 'if';
INT:                'Int';
NEW:                'new';
RETURN:             'return';
ITERATE:            'iterate';
PRINT:              'print';
READ:               'read';

// Literals

INT_LITERAL : ('0'..'9')+ ;
FLOAT_LITERAL : ('0'..'9')* '.' ('0'..'9')+ ;
CHAR_LITERAL : '\'' (~['\\\r\n] | EscapeSequence) '\'';
NULL_LITERAL : 'null';
TRUE : 'true';
FALSE : 'false';

// Separators

LPAREN:             '(';
RPAREN:             ')';
LBRACE:             '{';
RBRACE:             '}';
LBRACK:             '[';
RBRACK:             ']';
SEMI:               ';';
COMMA:              ',';
DOT:                '.';
COLONCOLON:         '::';
COLON:              ':';
CRETURN:            '>';

// Operators

ASSIGN:             '=';
LT:                 '<';
BANG:               '!';
QUESTION:           '¿';
EQUAL:              '==';
NOTEQUAL:           '!=';
AND:                '&&';
ADD:                '+';
SUB:                '-';
MUL:                '*';
DIV:                '/';
MOD:                '%';

// Whitespace and comments

WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '{-' .*? '-}'    -> channel(HIDDEN);
LINE_COMMENT:       '--' ~[\r\n]*    -> channel(HIDDEN);

// Identifiers

ID : [a-z] [a-zA-Z0-9_]* ;
IDtype : [A-Z] [a-zA-Z0-9_]* ;

// Auxiliaries

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

fragment HexDigit
    : [0-9a-fA-F]
    ;
