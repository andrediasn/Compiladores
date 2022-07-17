/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Attr extends Cmd {

    private Lvalue id;
    private Expr e;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Attr(int l, int c, Lvalue id, Expr e) {
        this.id = id;
        this.e = e;
        line = l;
        column = c;
    }

    public Lvalue getID() {
        return id;
    }

    public Expr getExp() {
        return e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
