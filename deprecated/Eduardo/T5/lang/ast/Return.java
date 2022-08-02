/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Return extends Cmd {
  private Expr[] s;
  private int line;
  private int column;

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Return(int l, int c, Expr[] s) {
    this.s = s;
    line = l;
    column = c;
  }

  public Expr[] getExpr() {
    return s;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
