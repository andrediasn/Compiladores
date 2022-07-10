/*

Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 

*/


public class Token 
{
    public int l, c;
    public TOKEN_TYPE t;
    public String lexeme;
    public Object info;

    public Token( TOKEN_TYPE t, String lexeme, Object o, int l, int c)
    {
        this.t = t;
        this.lexeme = lexeme;
        info = o;
        this.l = l;
        this.c = c;
    }

    public Token( TOKEN_TYPE t, String lexeme, int l, int c )
    {
        this.t = t;
        this.lexeme = lexeme;
        info = null;
        this.l = l;
        this.c = c;
    }

    public Token( TOKEN_TYPE t, Object o, int l, int c )
    {
        this.t = t;
        this.lexeme = null;
        info = o;
        this.l = l;
        this.c = c;
    }

    public int getLine()
    {
        return l;
    }

    public int getColumn()
    {
        return c;
    }

    public TOKEN_TYPE getToken()
    {
        return t;
    }

    public String getLexeme()
    {
        return lexeme;
    }

    public Object getInfo()
    {
        return info;
    }

    @Override
    public String toString()
    {
        return "[(" + l + "," + c + ") \"" + (lexeme == null ? t.name(): lexeme) + "\" : <" + (info == null ? "" : info.toString()) + ">]";
    }
}