/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang;

import java.io.IOException;
import lang.parser.*;
import lang.ast.*;
import lang.visitor.*;

public class Main{
    public static void main(String[] args) throws IOException{
        Parser p = new Parser();
        Prog n = (Prog) p.parseFile(args[0]);
        if(n==null){
            System.out.println("Parser                [ FAIL ]");
        }else{
            System.out.println("Parser                [  OK  ]");
            VisitorStatic v = new VisitorStatic();
            v.visit(n);
            System.out.println("Analise semântica     [  OK  ]");
            if(args[1].equals("true")){
                System.out.println("Interpretação:");
                VisitorInterpreter interpreter = new VisitorInterpreter(v);
                interpreter.visit(n);
            }
            if(args[2].equals("true")){
                VisitorCompiler compiler = new VisitorCompiler(v);
                compiler.visit(n);
                compiler.saveFile("Main.java");
                System.out.println("Compilacao:           [  OK  ]");
            }
        }
        System.out.println("");
    }
}