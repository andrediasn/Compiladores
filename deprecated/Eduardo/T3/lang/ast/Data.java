/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Data extends SuperNode {
    private String id;
    private Decl[] tipos;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Data(int l, int c, String i, Decl[] t){
        this.id = i;
        tipos = t;
        line = l;
        column = c;
    }

    public String getId() { return id; }
    public Decl[] getDecls() { return tipos; }

    public void accept(Visitor v){ v.visit(this);}
}
