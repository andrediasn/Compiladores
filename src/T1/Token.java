/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme
Matrícula: 

*/

public class Token {
    private int line, column; // Marcadores de linhas e colunas
    private TokenType type; // Tipo do token
    private String lexeme; // Lexema
    private Object value; // Valor Atribuido

    public Token (TokenType type, String lexeme, Object obj, int line, int column){
        this.type = type;
        this.lexeme = lexeme;
        this.value = obj;
        this.line = line;
        this.column = column;
    }

    public Token (TokenType type, String lexeme, int line, int column){
        this.type = type;
        this.lexeme = lexeme;
        this.value = null;
        this.line = line;
        this.column = column;
    }

    public Token (TokenType type, Object obj, int line, int column ){
        this.type = type;
        this.lexeme = null;
        this.value = obj;
        this.line = line;
        this.column = column;
    }

    public int getLine(){
        return line;
    }

    public int getColumn(){
        return column;
    }

    public TokenType getToken(){
        return type;
    }

    public String getLexeme(){
        return lexeme;
    }

    public Object getValue(){
        return value;
    }

    @Override public String toString(){
        //Saída com linha e columa
        //return "(" + line + "," + column + ") " + (lexeme == null ? type.name(): lexeme) + (value == null ? "" : " : " + value.toString());

        //Saída padrão
        return (lexeme == null ? type.name(): lexeme) + (value == null ? "" : ": " + value.toString());
    }
}