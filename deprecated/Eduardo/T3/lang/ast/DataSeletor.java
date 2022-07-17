/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class DataSeletor extends Seletor{
    private String id;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public DataSeletor(int l, int c,String i){
        id = i;
        line = l;
        column = c;
    }

    public String getId() { return id; }

    public void accept(Visitor v) { v.visit(this); }
}
