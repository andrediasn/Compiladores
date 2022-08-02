/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class If extends Cmd {

    private Expr teste;
    private Cmd thn;
    private Cmd els;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public If(int l, int c, Expr teste, Cmd thn, Cmd els) {
        this.teste = teste;
        this.thn = thn;
        this.els = els;
        line = l;
        column = c;
    }

    public If(int l, int c, Expr teste, Cmd thn) {
        this.teste = teste;
        this.thn = thn;
        this.els = null;
        line = l;
        column = c;
    }

    public Expr getTeste() {
        return teste;
    }

    public Cmd getThen() {
        return thn;
    }

    public Cmd getElse() {
        return els;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
