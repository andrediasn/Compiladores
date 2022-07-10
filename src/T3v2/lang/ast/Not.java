/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Not extends Expr {
  Expr e;
  private int line;
  private int column;

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Not(int li, int c, Expr l) {
    e = l;
    line = li;
    column = c;
  }

  public Expr getExpr() {
    return e;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
