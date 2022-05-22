/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

%%

%unicode
%line
%column
%public
%class Lexical
%function nextToken
%type Token

%{
    private Token symbol (TokenType type) {
        return new Token (type, yytext(), yyline+1, yycolumn+1);
    }

    private Token symbol (TokenType type, Object value) {
        return new Token (type, value, yyline+1, yycolumn+1);
    }
%}

/* Expressões regulares */
empty = {endLine} | [ \t\f]
endLine = \r|\n|\r\n
identificador = [:letter:] + ([:letter:] | [:digit:] | "_")*
inteiro = [:digit:]+
decimal = [:digit:]* + "." + [:digit:]+
caracter = "\'" + [:letter:]* + "\'" | "\'" [^\\'] "\'" | "\'\\n\'" | "\'\\t\'" | "\'\\b\'" | "\'\\r\'" | "\'\\\\\'" | "\'\\\'\'" 

%state SINGLELINECOMMENT
%state MULTLINECOMMENT

%%

<YYINITIAL>
{
    "Int"  {return symbol(TokenType.IDINT); }
    "Float" {return symbol(TokenType.IDFLOAT); }
    "Char" {return symbol(TokenType.IDCHAR); }
    "Bool" {return symbol(TokenType.BOOL); }
    "true" {return symbol(TokenType.TRUE); }
    "false" {return symbol(TokenType.FALSE); }
    "null"  {return symbol(TokenType.NULL); }
    "("  {return symbol(TokenType.LEFTPARENT); }
    ")"  {return symbol(TokenType.RIGHTPARENT); }
    "["  {return symbol(TokenType.LEFTBRACKET); }
    "]"  {return symbol(TokenType.RIGHTBRACKET); }
    "{"  {return symbol(TokenType.LEFTCURLY); }
    "}"  {return symbol(TokenType.RIGHTCURLY); }
    ">"  {return symbol(TokenType.GREATER); }
    "<"  {return symbol(TokenType.LESS); }
    "."  {return symbol(TokenType.DOT); }
    ","  {return symbol(TokenType.COMMA); }
    ":"  {return symbol(TokenType.COLON); }
    ";"  {return symbol(TokenType.SEMICOLON); }
    "::"  {return symbol(TokenType.DOUBLECOLON); }
    "="  {return symbol(TokenType.ASSIGN); }
    "=="  {return symbol(TokenType.EQ); }
    "!="  {return symbol(TokenType.NEQ); }
    "+"  {return symbol(TokenType.PLUS); }
    "-"  {return symbol(TokenType.MINUS); }
    "*"  {return symbol(TokenType.MULT); }
    "/"  {return symbol(TokenType.DIV); }
    "%"  {return symbol(TokenType.MOD); }
    "&&"  {return symbol(TokenType.AND); }
    "!"  {return symbol(TokenType.NOT); }
    "if" {return symbol(TokenType.IF); }
    "else" {return symbol(TokenType.ELSE); }
    "iterate"  {return symbol(TokenType.ITERATE); }
    "read"  {return symbol(TokenType.READ); }
    "print"  {return symbol(TokenType.PRINT); }
    "return"  {return symbol(TokenType.RETURN); }
    "new"  {return symbol(TokenType.NEW); }
    {inteiro}  {return symbol(TokenType.INT, Integer.parseInt(yytext())); }
    {decimal}  {return symbol(TokenType.FLOAT, Float.parseFloat(yytext())); }
    {caracter}  {return symbol(TokenType.CHAR, yytext().substring(1,yytext().length()-1)); }
    {identificador}  { return symbol(TokenType.ID, yytext()); }
    "--"  { yybegin(SINGLELINECOMMENT); }
    "{-"  { yybegin(MULTLINECOMMENT); }
    {empty} { /* Não faz nada */ }
}

<SINGLELINECOMMENT>
{
    {endLine}  { yybegin(YYINITIAL); }
    [^]  { /* Não faz nada */ }
}

<MULTLINECOMMENT>
{
    "-}" { yybegin(YYINITIAL); }
    [^]  { /* Não faz nada */ }
}

[^]   { throw new RuntimeException("Illegal character: " + yytext()); }