package visitors;

import ast.*;

public abstract  class  Visitor {
     public abstract void visit(Program p);
     
     public abstract void visit(Add e);
          
     public abstract void visit(Var e);
     public abstract void visit(NInt e);
     public abstract void visit(Call e);
     
     public abstract void visit(Attr e);
     public abstract void visit(Print e);
     public abstract void visit(Block e);
     public abstract void visit(Func f);
     
     public abstract void visit(Param e);
     
     public abstract void visit(TyInt t);
     public abstract void visit(TyVoid t);
}
