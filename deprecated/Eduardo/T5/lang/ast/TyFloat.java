/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class TyFloat extends Btype {

  private int line;
  private int column;

  public TyFloat(int l, int c) {
    line = l;
    column = c;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
