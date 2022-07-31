package ast;

/*
 * Esta classe representa um comando de Impressão.
 */
import java.util.HashMap; 
import visitors.Visitor;
 
public class Print extends Node {
      
      private Expr e; 
      
      public Print(Expr e){
           this.e  = e;
      }
      
      public Expr getExpr(){ return e;}
      
      //@Override
      public String toString(){
         return   "print " + e.toString() + ";"; 
      }
      
      public void accept(Visitor v){ v.visit(this); }
}
