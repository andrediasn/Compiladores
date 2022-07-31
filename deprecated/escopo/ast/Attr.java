package ast;

/*
 * Esta classe representa um comando de atribuição.
 * ID = Expr
 */
 
import java.util.HashMap; 
import visitors.Visitor;

public class Attr extends Node {
      
      private String id;
      private Expr e; 
      
      public Attr(String id, Expr e){
           this.id = id;
           this.e  = e;
      }
      
      public String getID(){ return id;} 
      public Expr getExp(){   return e; }
      
      public String toString(){
          return id + " = " + e.toString() + ";";
      }
      
      public void accept(Visitor v){ v.visit(this);}

}
