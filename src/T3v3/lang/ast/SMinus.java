/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class SMinus extends Exp {

  private int line, column;
  private Exp exp;

  public SMinus(int line, int column, Exp exp) {
    this.line = line;
  	this.column = column;
    this.exp = exp;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Exp getExp() {
    return exp;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
