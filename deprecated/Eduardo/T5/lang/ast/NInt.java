/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class NInt extends Expr {

    private int l;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public NInt(int l, int c, int v) {
        this.l = v;
        line = l;
        column = c;
    }

    public int getValue() {
        return l;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
