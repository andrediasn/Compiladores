/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme
Matrícula: 

*/

public class Token {
    private int line, column;
    private TokenType type;
    private Object lexeme;


    public int getLine(){
        return line;
    }

    public int getColumn(){
        return column;
    }

    public TokenType getToken(){
        return type;
    }

    public Object getLexeme(){
        return lexeme;
    }

    @Override public String toString(){
        return "("+ line +","+ column + ")\t<" + type + ">" + (lexeme==null ? "" : ": \"" + lexeme + "\"");
    }
}