/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class CmdList extends Cmd{
    private Cmd[] list;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public CmdList(int l , int c, Cmd[] ls)
    {
        line = l;
        column = c;
        list = ls;
    }

    public Cmd[] getList() { return list; }

    public void accept(Visitor v) { v.visit(this); }
}
