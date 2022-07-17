/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.visitors;

import lang.ast.*;

public abstract class Visitor
{
    public abstract void visit(Program p);
    public abstract void visit(Add e);
    
    public abstract void visit(Sub e);
    public abstract void visit(Mul e);
    public abstract void visit(Div e);
    public abstract void visit(Mod e);

    public abstract void visit(And e);
    public abstract void visit(Lt e);
    public abstract void visit(Eq e);
    public abstract void visit(Not e);

    public abstract void visit(True e);
    public abstract void visit(False e);
    public abstract void visit(NInt e);
    public abstract void visit(NFloat e);
    public abstract void visit(Call e);

    public abstract void visit(Attr e);
    public abstract void visit(If e);
    public abstract void visit(Print e);
    public abstract void visit(Func f);
    public abstract void visit(Data d);
    public abstract void visit(Decl e);

    public abstract void visit(Return e);
    public abstract void visit(Param e);

    public abstract void visit(TyInt t);
    public abstract void visit(TyFloat t);
    public abstract void visit(TyBool t);
    public abstract void visit(TyChar t);
    public abstract void visit(TyID t);

    public abstract void visit(ArraySeletor e);
    public abstract void visit(CallExpr e);
    public abstract void visit(CmdList e);
    public abstract void visit(Iterate e);
    public abstract void visit(LChar e);
    public abstract void visit(Lvalue e);
    public abstract void visit(Neg e);
    public abstract void visit(NEq e);
    public abstract void visit(New e);
    public abstract void visit(Null e);
    public abstract void visit(Read e);
    public abstract void visit(Tipo e);

    public abstract void visit(DataSeletor e);
}
