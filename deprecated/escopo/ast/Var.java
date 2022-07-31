package ast;

/*
 * Esta classe representa uma variável.
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Var extends Expr {
      
      private String l;
     
      public Var(String name){
           this.l = name;
      }
      
      public String getName(){return l;}
      
      //@Override
      public String toString(){
         return l; 
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
