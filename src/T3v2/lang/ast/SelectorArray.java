/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class SelectorArray extends Selector {

	private int line, column;
  private Exp index;

  public SelectorArray(int line, int column, Exp index) {
    this.index = index;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Exp getIndex() {
    return index;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
