/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Tipo extends SuperNode {
  private Btype t;
  private int colchetes;
  private int line;
  private int column;

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Tipo(int l, int c, Btype tip, int colchetes) {
    t = tip;
    this.colchetes = colchetes;
    line = l;
    column = c;
  }

  public Btype getBtype() {
    return t;
  };

  public int getColchetes() {
    return colchetes;
  };

  public void accept(Visitor v) {
    v.visit(this);
  }
}
