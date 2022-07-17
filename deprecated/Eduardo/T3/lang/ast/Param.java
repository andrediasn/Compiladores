/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Param extends SuperNode {
  private String s;
  private Tipo t;
  private int line;
  private int column;

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Param(int l, int c, String s, Tipo t) {
    this.s = s;
    this.t = t;
    line = l;
    column = c;
  }

  public String getID() {
    return s;
  }

  public Tipo getTipo() {
    return t;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
