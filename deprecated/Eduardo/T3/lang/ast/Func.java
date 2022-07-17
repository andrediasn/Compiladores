/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Func extends SuperNode {

    private String id;
    private Tipo[] retorno;
    private Param[] ps;
    private Cmd[] body;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Func(int l, int c, String id, Param[] p, Tipo[] retorno, Cmd[] n) {
        this.id = id;
        this.retorno = retorno;
        this.ps = p;
        body = n;
        line = l;
        column = c;
    }

    public String getID() {
        return id;
    }

    public Tipo[] getTipo() {
        return retorno;
    }

    public Param[] getParams() {
        return ps;
    }

    public Cmd[] getBody() {
        return body;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
