
/*

Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 

*/


%%

%unicode
%line
%column
%public
%class LexicalText
%function nextToken
%type Token

%{
    /* 

    Declarar variáveis e métodos adicionais que julgarmos necessários.

    */

    private int ntk;

    public int readedTokens()
    {
        return ntk;
    }

    private Token symbol( TOKEN_TYPE t )
    {
        ntk++;
        return new Token( t, yytext(), yyline+1, yycolumn+1 );
    }

    private Token symbol( TOKEN_TYPE t, Object value )
    {
        ntk++;
        return new Token( t, value, yyline+1, yycolumn+1 );
    }
%}

%init{
    ntk = 0; // Copiado no construtor do analisador léxico
%init}


/* Definição de macros (expressões reguladores com nome definido) */

FimDeLinha = \r|\n|\r\n
Brancos = {FimDeLinha} | [ \t\f]
identificador = [:letter:] + ([:letter:] | [:digit:] | "_")*
integer = [:digit:]+
double = [:digit:]* + "." + [:digit:]+
caracter = "\'" [^\\'] "\'" | "\'\\n\'" | "\'\\t\'" | "\'\\b\'" | "\'\\r\'" | "\'\\\\\'" | "\'\\\'\'" 


%state SINGLELINECOMMENT
%state MULTIPLELINESCOMMENT

%%

<YYINITIAL>
{
    "Int"  {return symbol(TOKEN_TYPE.INT); }
    "Float" {return symbol(TOKEN_TYPE.FLOAT); }
    "Char" {return symbol(TOKEN_TYPE.CHAR); }
    "Bool" {return symbol(TOKEN_TYPE.BOOL); }
    "true" {return symbol(TOKEN_TYPE.TRUE); }
    "false" {return symbol(TOKEN_TYPE.FALSE); }
    "null"  {return symbol(TOKEN_TYPE.NULL); }

    "("  {return symbol(TOKEN_TYPE.LEFTPARENT); }
    ")"  {return symbol(TOKEN_TYPE.RIGHTPARENT); }
    "["  {return symbol(TOKEN_TYPE.LEFTBRACE); }
    "]"  {return symbol(TOKEN_TYPE.RIGHTBRACE); }
    "{"  {return symbol(TOKEN_TYPE.LEFTBRACKET); }
    "}"  {return symbol(TOKEN_TYPE.RIGHTBRACKET); }

    ">"  {return symbol(TOKEN_TYPE.GREATER); }
    "<"  {return symbol(TOKEN_TYPE.LESS); }

    "."  {return symbol(TOKEN_TYPE.DOT); }
    ","  {return symbol(TOKEN_TYPE.COMMA); }
    ":"  {return symbol(TOKEN_TYPE.COLON); }
    ";"  {return symbol(TOKEN_TYPE.SEMICOLON); }
    "::"  {return symbol(TOKEN_TYPE.DOUBLECOLON); }

    "="  {return symbol(TOKEN_TYPE.ASSIGN); }
    "=="  {return symbol(TOKEN_TYPE.EQ); }
    "!="  {return symbol(TOKEN_TYPE.NEQ); }

    "+"  {return symbol(TOKEN_TYPE.PLUS); }
    "-"  {return symbol(TOKEN_TYPE.MINUS); }
    "*"  {return symbol(TOKEN_TYPE.MULT); }
    "/"  {return symbol(TOKEN_TYPE.DIV); }
    "%"  {return symbol(TOKEN_TYPE.MODULE); }
    
    "&&"  {return symbol(TOKEN_TYPE.AND); }
    "!"  {return symbol(TOKEN_TYPE.NOT); }

    "if" {return symbol(TOKEN_TYPE.IF); }
    "else" {return symbol(TOKEN_TYPE.ELSE); }
    "iterate"  {return symbol(TOKEN_TYPE.ITERATE); }
    "read"  {return symbol(TOKEN_TYPE.READ); }
    "print"  {return symbol(TOKEN_TYPE.PRINT); }
    "return"  {return symbol(TOKEN_TYPE.RETURN); }
    "new"  {return symbol(TOKEN_TYPE.NEW); }

    {integer}  {return symbol(TOKEN_TYPE.INTEGER, Integer.parseInt(yytext())); }
    {double}  {return symbol(TOKEN_TYPE.DOUBLE, Float.parseFloat(yytext())); }
    {caracter}  {return symbol(TOKEN_TYPE.CARACTER,yytext().substring(1,yytext().length()-1)); }
    {identificador}  { return symbol(TOKEN_TYPE.ID, yytext()); }

    "--"  { yybegin(SINGLELINECOMMENT); }
    "{-"  { yybegin(MULTIPLELINESCOMMENT); }

    {Brancos} { /* Não faz nada */ }
}

<SINGLELINECOMMENT>
{
    {FimDeLinha}  { yybegin(YYINITIAL); }
    [^]  { /* Não faz nada */ }
}

<MULTIPLELINESCOMMENT>
{
    "-}" { yybegin(YYINITIAL); }
    [^]  { /* Não faz nada */ }
}

[^]   { throw new RuntimeException("Illegal character <" + yytext() + ">"); }