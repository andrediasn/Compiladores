/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Call extends Cmd {

    private String l;
    private Expr[] args;
    private Lvalue[] ret;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Call(int l, int c, String name, Expr[] xs, Lvalue[] retorno) {
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

    public Lvalue[] getRet() {
        return ret;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
