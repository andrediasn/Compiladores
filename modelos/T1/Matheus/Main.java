import java.io.FileReader;
import java.io.IOException;

/*

Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 

*/

public class Main{

    public static void main( String args[] ) throws IOException
    {
        LexicalText lx = new LexicalText(new FileReader( args[0] ));
        Token t = lx.nextToken();

        while( t != null )
        {
            System.out.println( t.toString() );
            t = lx.nextToken();
        }

        System.out.println("Total de tokens lidos " + lx.readedTokens());
    }
}