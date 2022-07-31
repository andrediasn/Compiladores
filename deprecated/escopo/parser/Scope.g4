grammar Scope;

@parser::header
{
    package parser;
    
    import ast.*;
    
    import java.util.List;
    import java.util.ArrayList;
}

@lexer::header
{
    package parser;
}

/* Regras da gramática */

prog returns[Program p]: {List<Func> l = new ArrayList<Func>();} (func {l.add($func.f);})+ call {$p = new Program(l.toArray(new Func[0]), $call.e);};

func returns[Func f]: type ID '(' params ')' block {$f = new Func($ID.text, $params.l.toArray(new Param[0]), $type.t, $block.s);};

params returns[List<Param> l]: {$l = new ArrayList<Param>();} d1=param {$l.add($d1.p);} (','d2=param {$l.add($d2.p);})* | {$l = new ArrayList<Param>();};

block returns[Block s]: '{' {List<Node> l = new ArrayList<Node>();} (stmt {l.add($stmt.s);} | decl {l.add($decl.d);})* '}' {$s = new Block(l.toArray(new Node[0]));};

decl returns[Node d]:  param {$d = $param.p;} ';' | func {$d = $func.f;};

param returns[Param p]: type ID {$p = new Param($ID.text, $type.t);};

stmt returns[Node s] :
 ID '=' expr ';' {$s = new Attr($ID.text, $expr.e);}
|
 call ';' {$s = $call.e;}
|
 block {$s = $block.s;}
|
 'print' expr ';' {$s = new Print($expr.e);}
;

expr returns[Expr e]:
 factor '+' expr {$e = new Add($factor.e, $expr.e);}
|
 factor {$e = $factor.e;}
;

factor returns [Expr e]:
 ID {$e = new Var($ID.text);}
|
 INT {$e = new NInt(Integer.parseInt($INT.text));}
|
 call {$e = $call.e;}
;

call returns[Call e]: ID '(' args ')'  {$e = new Call($ID.text, $args.l.toArray(new Expr[0]));}    ;

args returns[List<Expr> l]: {$l = new ArrayList<Expr>();} e1=expr {$l.add($e1.e);} (',' e2=expr {$l.add($e2.e);})* | {$l = new ArrayList<Expr>();};

type returns[Tipo t]: 'int' {$t = new TyInt();} | 'void' {$t = new TyVoid();} ;

/* Regras léxicas */

ID : [a-zA-Z] [A-Za-z0-9]*;
INT : [0-9]+;

NEWLINE: '\r'? '\n' -> skip;
WS : [ \t]+ -> skip;
LINE_COMMENT : '//' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT: '/*' .*?  '*/' -> skip;

