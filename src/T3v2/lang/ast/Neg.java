/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Neg extends Exp {

  private int line, column;
  private Exp expression;

  public Neg(int line, int column, Exp expression) {
    this.line = line;
  	this.column = column;
    this.expression = expression;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Exp getExpression() {
    return expression;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
