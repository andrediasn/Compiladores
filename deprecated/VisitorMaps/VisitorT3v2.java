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
    public abstract void visit(Program p); 
		public abstract void visit(Data e);
    public abstract void visit(LData e);  //SelectorData - acesso a elemento de data
  	public abstract void visit(Decl e);
    public abstract void visit(StmtList e); // CmdArray - Lista de comandos
    public abstract void visit(CallCmd e);
    public abstract void visit(CallExpr e); //para chamadas de função como expressão;
    public abstract void visit(Attr e); // Assign - Atribuições de variáveis
		
    public abstract void visit(Func f);
    public abstract void visit(Param e);
	  public abstract void visit(Print e);
    public abstract void visit(Read e);
    public abstract void visit(Return e);

    public abstract void visit(LValue e);
    public abstract void visit(Int e);
    public abstract void visit(FloatV e);
    public abstract void visit(Caracter e);
    public abstract void visit(Iterate e);
    public abstract void visit(New e);
    public abstract void visit(LExpr e); //SelectorArray - acesso a posição da array
    
    public abstract void visit(CModule e); // ModuleT
    public abstract void visit(Div e);
    public abstract void visit(Mult e);
    public abstract void visit(Add e);
    public abstract void visit(Sub e);
    
    public abstract void visit(SMinus e); // Neg - menos unário
		public abstract void visit(Not e);
    public abstract void visit(Neq e);
    public abstract void visit(And e);
    public abstract void visit(Eq e);
    public abstract void visit(Lt e);
    
		public abstract void visit(If e);
    public abstract void visit(False e);
    public abstract void visit(True e);
    public abstract void visit(Null e);
  
    public abstract void visit(Type t);
    public abstract void visit(TyBool t);
		public abstract void visit(TyChar t);
    public abstract void visit(TyFloat t);
    public abstract void visit(TyID t);
		public abstract void visit(TyInt t);
}
