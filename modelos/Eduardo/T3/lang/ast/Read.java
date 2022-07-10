/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Read extends Cmd {
    private Lvalue var;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Read(int l, int c, Lvalue v) {
        var = v;
        line = l;
        column = c;
    }

    public Lvalue getVar() {
        return var;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
