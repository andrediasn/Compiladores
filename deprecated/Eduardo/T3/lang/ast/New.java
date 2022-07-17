/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class New extends Expr {
    private Tipo tipo;
    private Expr exp;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public New(int l, int c, Tipo t, Expr e) {
        tipo = t;
        exp = e;
        line = l;
        column = c;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Expr getExp() {
        return exp;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
