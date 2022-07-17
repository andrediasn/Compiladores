/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ASTVisitor;

import lang.ast.*;

public abstract class Visitor
{
	public abstract void visit(Prog prog);
	public abstract void visit(Data data);
	public abstract void visit(Decl decl);
	public abstract void visit(Type type);
	public abstract void visit(Func func);
	public abstract void visit(Params params);
	public abstract void visit(Cmd cmd);
	public abstract void visit(Exp exp);
	public abstract void visit(Int typeInt);
	public abstract void visit(Bool typeBool);
	public abstract void visit(Char typeChar);
	public abstract void visit(TFloat typeFloat);
	public abstract void visit(IDType idType);
	public abstract void visit(If cIf);
	public abstract void visit(Iterate iterate);
	public abstract void visit(Read read);
	public abstract void visit(Lvalue lvalue);
	public abstract void visit(Print print);
	public abstract void visit(Assign assign);
	public abstract void visit(CmdFunction cmdFunction);
	public abstract void visit(BinOp BinOp);
	public abstract void visit(And and);
	public abstract void visit(Less less);
	public abstract void visit(Eq eq);
	public abstract void visit(Neq neq);
	public abstract void visit(Plus plus);
	public abstract void visit(Minus minus);
	public abstract void visit(Mult mult);
	public abstract void visit(Div div);
	public abstract void visit(CModule module);
	public abstract void visit(SMinus sminus);
	public abstract void visit(True cTrue);
	public abstract void visit(False cFalse);
	public abstract void visit(Null cNull);
	public abstract void visit(SInteger sInteger);
	public abstract void visit(SDouble sDouble);
	public abstract void visit(Caracter caracter);	
	public abstract void visit(New cNew);
	public abstract void visit(PExp pExp);
	public abstract void visit(Selector selector);
	public abstract void visit(Lexp lexp);
	public abstract void visit(Ldata lData);
 	
}
