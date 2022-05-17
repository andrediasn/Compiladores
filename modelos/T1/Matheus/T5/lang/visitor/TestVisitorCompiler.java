/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.visitor;

import java.io.*;
import lang.ast.*;
import lang.parser.*;
import lang.type.*;
import java.util.List;

// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public class TestVisitorCompiler {
   private ParseAdaptor adp;
   private String okSrcs = "testes/semantica/certo/";
   private File f;
   
   public TestVisitorCompiler(ParseAdaptor adp){
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
										 	 	VisitorCompilers v = new VisitorCompilers();
												node.accept(v);
												String name = s.getName();
												if(name.indexOf('.') > 0){
													name = name.substring(0, name.indexOf('.'));
												}
												VisitorJava vj = new VisitorJava(name, v.getCompilersEnvs(), v.getCompilersFuncs());
												node.accept(vj);
												System.out.println(" CERTO ");
                       	flips++;
                   }else{ 
                      System.out.println(" FALHOU ");
                      flops++;
                   }
									 System.out.println("----------------------------");
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
}

