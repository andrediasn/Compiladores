/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme
Matrícula: 

*/

import java.io.FileReader;
import java.io.IOException;

public class Main{

    public static void main( String args[] ) throws IOException {

        LexicalText lx = new LexicalText(new FileReader( args[0] ));
        Token token = lx.nextToken(); // Busca pelo primeiro token

        while( token != null ) { // Enquanto existir tokens

            System.out.println( token.toString() );  // Chama analisador léxico
            token = lx.nextToken(); // Busca próximo token

        }

        System.out.println("Total de tokens lidos " + lx.readedTokens());
    }
}