/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class ArraySeletor extends Seletor {
    private Expr indice;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public ArraySeletor(int l, int c, Expr ind) {
        indice = ind;
        line = l;
        column = c;
    }

    public Expr getIndice() {
        return indice;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
