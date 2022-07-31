package ast;

import visitors.Visitor;

/*
 * Esta classe representa um tipo void.
 */
public class TyVoid extends Tipo {
      
      public TyVoid(){}
      
      public boolean match(Tipo t){
         return t instanceof TyVoid; 
      }
      
      public String toString(){ return "void"; }
      
      public void accept(Visitor v){ v.visit(this);}
}
