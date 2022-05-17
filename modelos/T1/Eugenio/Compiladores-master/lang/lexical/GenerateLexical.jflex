package lang.lexical;

/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

import lang.*;

%%

%unicode 
%line 
%column
%public
%class Lexical
%function nextToken
%type Token

/* Código que será inserido diretamente no analisador. */
%{  
    private Token symbol (TOKEN_TYPE t) {
        return new Token(t, yyline+1, yycolumn+1);
    }

    private Token symbol (TOKEN_TYPE t, Object value) {
        return new Token(t, value, yyline+1, yycolumn+1);
    }
%}

/* Definindo algumas macros */
    fimLinha = \r|\n|\r\n
    brancos = {fimLinha} | [ \t\f]
    inteiro = [:digit:]+
    double = [:digit:]* "." [:digit:]+
    id = [:letter:]("_"|[:letter:]|[:digit:])*
    caracter = "\'" [^\\'] "\'" | "\'\\n\'" | "\'\\t\'" | "\'\\b\'" | "\'\\r\'" | "\'\\\\\'" | "\'\\\'\'"
    
%state BLOCKCOMMENT
%state LINECOMMENT

%%

<YYINITIAL>{
    "("                         {return symbol(TOKEN_TYPE.LPARENT);}
    ")"                         {return symbol(TOKEN_TYPE.RPARENT);}
    "["                         {return symbol(TOKEN_TYPE.LBRACKET);}
    "]"                         {return symbol(TOKEN_TYPE.RBRACKET);}
    "{"                         {return symbol(TOKEN_TYPE.LBRACE);}
    "}"                         {return symbol(TOKEN_TYPE.RBRACE);}
    ";"                         {return symbol(TOKEN_TYPE.SEMICOLON);}
    "."                         {return symbol(TOKEN_TYPE.DOT);}
    ","                         {return symbol(TOKEN_TYPE.COMMA);}
    ":"                         {return symbol(TOKEN_TYPE.COLON);}
    "::"                        {return symbol(TOKEN_TYPE.DCOLON);}
    ">"                         {return symbol(TOKEN_TYPE.GREATER);}
    "="                         {return symbol(TOKEN_TYPE.ASSIGN);}
    "<"                         {return symbol(TOKEN_TYPE.LESS);}
    "=="                        {return symbol(TOKEN_TYPE.EQ);}
    "!="                        {return symbol(TOKEN_TYPE.NEQ);}
    "+"                         {return symbol(TOKEN_TYPE.PLUS);}
    "-"                         {return symbol(TOKEN_TYPE.MINUS);}
    "*"                         {return symbol(TOKEN_TYPE.MULT);}
    "/"                         {return symbol(TOKEN_TYPE.DIV);}
    "%"                         {return symbol(TOKEN_TYPE.MOD);}
    "&&"                        {return symbol(TOKEN_TYPE.AND);}
    "!"                         {return symbol(TOKEN_TYPE.NOT);}
    "null"                      {return symbol(TOKEN_TYPE.NULL);}
    "true"                      {return symbol(TOKEN_TYPE.TRUE);}
    "false"                     {return symbol(TOKEN_TYPE.FALSE);}
    "Int"                       {return symbol(TOKEN_TYPE.INT);}
    "Char"                      {return symbol(TOKEN_TYPE.CHAR);}
    "Bool"                      {return symbol(TOKEN_TYPE.BOOL);}
    "Float"                     {return symbol(TOKEN_TYPE.FLOAT);}
    "if"                        {return symbol(TOKEN_TYPE.IF);}
    "else"                      {return symbol(TOKEN_TYPE.ELSE);}
    "iterate"                   {return symbol(TOKEN_TYPE.ITERATE);}
    "read"                      {return symbol(TOKEN_TYPE.READ);}
    "print"                     {return symbol(TOKEN_TYPE.PRINT);}
    "return"                    {return symbol(TOKEN_TYPE.RETURN);}
    "data"                      {return symbol(TOKEN_TYPE.DATA);}
    "new"                       {return symbol(TOKEN_TYPE.NEW);}
    {id}                        {return symbol(TOKEN_TYPE.ID,yytext());}
    {double}                    {return symbol(TOKEN_TYPE.DOUBLE,Float.parseFloat(yytext()));}
    {inteiro}                   {return symbol(TOKEN_TYPE.INTEGER,Integer.parseInt(yytext()));}
    {caracter}                  {return symbol(TOKEN_TYPE.CARACTER,yytext().substring(1,yytext().length()-1));}
    {brancos}                   {/* Não faz nada */}
    "{-"                        { yybegin(BLOCKCOMMENT); }
    "--"                        { yybegin(LINECOMMENT); }
}

<BLOCKCOMMENT>{
    "-}"                        {yybegin(YYINITIAL); }
    [^]                         {/* Não faz nada */}
}

<LINECOMMENT>{
    {fimLinha}                  {yybegin(YYINITIAL); }
    [^]                         {/* Não faz nada */}
}

[^]                             { return symbol(TOKEN_TYPE.ERROR,yytext()); }