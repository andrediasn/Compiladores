/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class CallExpr extends Expr {

    private String l;
    private Expr[] args;
    private Expr ret;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public CallExpr(int l, int c, String name, Expr[] xs, Expr retorno) {
        this.l = name;
        args = xs;
        ret = retorno;
        line = l;
        column = c;
    }

    public String getName() {
        return l;
    }

    public Expr[] getArgs() {
        return args;
    }

    public Expr getRet() {
        return ret;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
