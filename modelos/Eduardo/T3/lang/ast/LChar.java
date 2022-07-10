/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class LChar extends Expr {

    private char car;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public LChar(int l, int c, char ca) {
        car = ca;
        line = l;
        column = c;
    }

    public char getValue() {
        return car;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
