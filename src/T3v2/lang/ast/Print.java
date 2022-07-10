/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Print extends Cmd{

  private int line, column;
  private Exp expression;

  public Print(int line, int column, Exp expression) {
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