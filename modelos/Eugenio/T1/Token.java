/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

public class Token {
    private int l, c;
    private TOKEN_TYPE t;
    private Object lexeme;
    
    public Token(TOKEN_TYPE t, Object lex, int l, int c){
        this.t = t;
        lexeme = lex;
        this.l = l;
        this.c = c;
    }

    public Token(TOKEN_TYPE t, int l, int c){
        this.t = t;
        lexeme = null;
        this.l = l;
        this.c = c;
    }
    
    public int getLine(){
        return l;
    }

    public int getColumn(){
        return c;
    }

    public TOKEN_TYPE getToken(){
        return t;
    }

    public Object getLexeme(){
        return lexeme;
    }

    @Override public String toString(){
        return "("+ l +","+ c + ")\t<" + t + ">" + (lexeme==null ? "" : ": \"" + lexeme + "\"");
    }
}