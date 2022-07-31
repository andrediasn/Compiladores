package ast;

/*
 * Esta classe representa uma bloco de comandos e/ou declarações
 */
import java.util.HashMap; 
import visitors.Visitor;

public class Block extends Node {
      
      private Node[] list; 
      
      public Block(Node[] l){
           this.list =  l;
      }
      
      public Node[] getStmts(){ return list;} 
      
      //@Override
      public String toString() {
	  String s = "";
	  for(Node stmt : list) {
	      s = s + "\n" + stmt.toString();
	  }
         return s;
      }
      
      public void accept(Visitor v){ v.visit(this);}
}

