/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class TyID extends Btype {
    private String idTipo;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public TyID(int l, int c, String id) {
        idTipo = id;
        line = l;
        column = c;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
