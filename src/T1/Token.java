/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

public class Token {
    private int line, column; // Marcadores de linhas e colunas
    private TokenType type; // Tipo do token
    private String lexeme; // Lexema
    private Object value; // Valor Atribuido

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

    @Override public String toString(){
        //Saída com linha e columa
        return "(" + line + "," + column + ") " + (lexeme == null ? type.name(): lexeme) + (value == null ? "" : " : " + value.toString());

        //Saída padrão
        //return (lexeme == null ? type.name(): lexeme) + (value == null ? "" : ": " + value.toString());
    }
}