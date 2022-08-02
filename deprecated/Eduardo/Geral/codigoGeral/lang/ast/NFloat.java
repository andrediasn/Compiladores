/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class NFloat extends Expr {
  private float l;
  private int line;
  private int column;

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public NFloat(int l, int c, float v) {
    this.l = v;
    line = l;
    column = c;
  }

  public float getValue() {
    return l;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
