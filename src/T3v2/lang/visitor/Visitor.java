/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.visitor;

import lang.ast.*;

public abstract class Visitor
{
	  public abstract void visit(And and);
		public abstract void visit(Assign assign);
    public abstract void visit(CallCmd callCmd);
    public abstract void visit(CallExp callExp);
    public abstract void visit(Caracter caract);
    public abstract void visit(CmdArray cmdArray);
		public abstract void visit(Data data);
  	public abstract void visit(Decl decl);
    public abstract void visit(Div div);
    public abstract void visit(DoubleT doub);
    public abstract void visit(Eq eq);
    public abstract void visit(False fal);
    public abstract void visit(Func func);
		public abstract void visit(If i);
    public abstract void visit(IntegerT integer);
    public abstract void visit(Iterate ite);
    public abstract void visit(Less less);
    public abstract void visit(Lvalue lValue);
    public abstract void visit(Minus minus);
    public abstract void visit(ModuleT module);
    public abstract void visit(Mult mult);
    public abstract void visit(Neg neg);
    public abstract void visit(Neq neq);
    public abstract void visit(New ne);
		public abstract void visit(Not not);
    public abstract void visit(Null nu);
		public abstract void visit(Params params);
    public abstract void visit(Plus plus);
	  public abstract void visit(Print pri);
    public abstract void visit(Prog prog);
    public abstract void visit(Read read);
    public abstract void visit(Return ret);
    public abstract void visit(SelectorArray selector);
    public abstract void visit(SelectorData selector);
		public abstract void visit(True tr);
    public abstract void visit(Type type);
    public abstract void visit(TypeBool type);
		public abstract void visit(TypeChar type);
    public abstract void visit(TypeFloat type);
    public abstract void visit(TypeIDType type);
		public abstract void visit(TypeInt type);
}
