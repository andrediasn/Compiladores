/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Decl extends SuperNode {
    private String id;
    private Tipo tip;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Decl(int l, int c, String i, Tipo t) {
        this.id = i;
        tip = t;
        line = l;
        column = c;
    }

    public String getId() {
        return id;
    }

    public Tipo getTipos() {
        return tip;
    }

    public void accept(Visitor v) { v.visit(this); }
}
