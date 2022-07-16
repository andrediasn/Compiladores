/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.visitors;

import lang.ast.*;

public abstract class Visitor
{
	  
    public abstract void visit(Program p); // prog - 
    public abstract void visit(Attr e);  // Assign - para armazenar atribuições de variáveis
    public abstract void visit(Func f); // - para armazenar definição de funções;
    public abstract void visit(CallCmd e);
    public abstract void visit(CallExp e);
    public abstract void visit(CmdArray e);
		public abstract void visit(Data e);

    public abstract void visit(IntegerT e);
    public abstract void visit(Caracter e);
    public abstract void visit(Iterate e);
  	public abstract void visit(Decl e);
    public abstract void visit(Div e);
    public abstract void visit(DoubleT e);
    
    
		public abstract void visit(If e);
    public abstract void visit(Lvalue e);
    public abstract void visit(ModuleT e);
    
    public abstract void visit(Mult e);
    public abstract void visit(Plus e);
    public abstract void visit(Minus e);

    public abstract void visit(New e);

    public abstract void visit(And e);
    public abstract void visit(Neg e);
    public abstract void visit(Neq e);
		public abstract void visit(Not e);
    public abstract void visit(Eq e);
    public abstract void visit(Less e);
    public abstract void visit(False e);
    public abstract void visit(True e);

    public abstract void visit(Null e);
		public abstract void visit(Params e);
	  public abstract void visit(Print e);
    public abstract void visit(Read e);
    public abstract void visit(Return e);

    public abstract void visit(AccessArray e); //SelectorArray - para demonstrar o acesso a um elemento de array em determinada posição;
    public abstract void visit(AccessData e);  //SelectorData - para demonstrar o acesso a um elemento de data com determinado identificador
		
    public abstract void visit(Type t);
    public abstract void visit(TyBool t);
		public abstract void visit(TyChar t);
    public abstract void visit(TyFloat t);
    public abstract void visit(TyIDType t);
		public abstract void visit(TyInt t);
}
