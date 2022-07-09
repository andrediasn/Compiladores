/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor;

import lang.ast.*;

public abstract class Visitor {
    public abstract void visit(Aexp aexp);
    public abstract void visit(Attr attr);
    public abstract void visit(Data data);
    public abstract void visit(Decl decl);
    public abstract void visit(Exp exp);
    public abstract void visit(Func func);
    public abstract void visit(FunctionCall functioncall);
    public abstract void visit(ID id);
    public abstract void visit(If i);
    public abstract void visit(Iterate iterate);
    public abstract void visit(LvalueArray lvaluearray);
    public abstract void visit(LvalueSelect lvalueselect);
    public abstract void visit(LvalueUnic lvalueunic);
    public abstract void visit(Mexp mexp);
    public abstract void visit(PexpFunction pexpfunction);
    public abstract void visit(PexpLvalue pexplvalue);
    public abstract void visit(PexpNew pexpnew);
    public abstract void visit(PexpParenteses pexpparenteses);
    public abstract void visit(Print print);
    public abstract void visit(Prog prog);
    public abstract void visit(Read read);
    public abstract void visit(Return ret);
    public abstract void visit(Rexp rexp);
    public abstract void visit(Sexp sexp);
    public abstract void visit(SexpValueBool sexpvaluebool);
    public abstract void visit(SexpValueChar sexpvaluechar);
    public abstract void visit(SexpValueFloat sexpvaluefloat);
    public abstract void visit(SexpValueInt sexpvalueint);
    public abstract void visit(SexpValueNull sexpvaluenull);
    public abstract void visit(SexpValuePexp sexpvaluepexp);
    public abstract void visit(Type type);
}
