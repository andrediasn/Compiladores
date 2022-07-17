/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;
import java.util.*;

public class Lvalue extends Expr {

    private String id;
    private ArrayList<Seletor> seletores;
    private int line;
    private int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Lvalue(int l, int c, String i) {
        id = i;
        seletores = new ArrayList<Seletor>();
        line = l;
        column = c;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Seletor> getSeletores() {
        return seletores;
    }

    public void add(Seletor s) {
        seletores.add(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

}
