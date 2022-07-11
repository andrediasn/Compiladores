/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.visitor;

import java.io.*;
import lang.ast.*;
import lang.parser.*;
import java.util.List;

// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public class TestInterpretator {
    private ParseAdaptor adp;
    private String okSrcs = "testes/sintaxe/certo/";
    private File f;
   
    public TestInterpretator(ParseAdaptor adp){
        this.adp = adp;
        f = new File(okSrcs);
        runOkTests();
    }
   
    private String filler(int n){
        String s = "";
        for(int i =0; i< n; i++){ s += " "; }
        return s;
    }
   
    public void runOkTests(){
        File inst[];
        int flips, flops;
        flips = 0;
        flops = 0;
        try{
            if( f.isDirectory() ){
                String pth;
                inst = f.listFiles();
                for(File s : inst){
                    pth = s.getPath();
                    System.out.print("Testando arquivo:  " + pth + filler(50 -pth.length()) + "-> Saída: ");
					SuperNode node = adp.parseFile(s.getPath());
                    if(node != null){
					    Visitor v = new VisitorInterpretator();
					    node.accept(v);
						System.out.println("[ OK ]");
                       	flips++;
                    } else { 
                        System.out.println("[ FALHOU ]");
                        flops++;
                    }
					//System.out.println("----------------------------");
                }
                System.out.println("Total de acertos: " + flips );
                System.out.println("Total de erros: " + flops );
            } else {
                System.out.println("O caminho " + f.getPath() + " não é um diretório ou não existe.");
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
   }
}
/* 
public class TestParser {
    private ParseAdaptor adp;
    private String okSrcs = "testes/sintaxe/certo/";
    private File f;
    
    public TestParser(ParseAdaptor adp){
         this.adp = adp;
         f = new File(okSrcs);
         runOkTests();
    }
    
    private String filler(int n){
       String s = "";
       for(int i =0; i< n; i++){ s += " "; }
       return s;
    }
    
    public void runOkTests(){
        File inst[];
        int flips, flops;
        flips = 0;
        flops = 0;
        try{
            if( f.isDirectory() ){
                String pth;
                inst = f.listFiles();
                for(File s : inst){
                    pth = s.getPath();
                    System.out.print("Testando " + pth + filler(50 -pth.length()) + "[");
                    if(adp.parseFile(s.getPath()) != null){
                        System.out.println("  OK  ]");
                        flips++;
                    }else{ 
                       System.out.println("FALHOU]");
                       flops++;
                    }
                }
                System.out.println("Total de acertos: " + flips );
                System.out.println("Total de erros: " + flops );
            }else{
               System.out.println("O caminho " + f.getPath() + " não é um diretório ou não existe.");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 } */