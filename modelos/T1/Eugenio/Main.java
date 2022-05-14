/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

import java.io.FileReader;
import java.io.IOException;

public class Main{
    public static void main(String args[]) throws IOException{
        Lexical lx = new Lexical(new FileReader(args[0]));
        Token t = lx.nextToken();
        while(t != null){
            System.out.println(t.toString()); 
            t= lx.nextToken();
        }
    }
}