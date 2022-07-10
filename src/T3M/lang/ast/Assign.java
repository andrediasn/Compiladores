/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Assign extends Cmd{

  private int line, column;
  private Lvalue value;
  private Exp expression;

  public Assign(int line, int column, Lvalue value, Exp expression)	{
    this.line = line;
    this.column = column;
    this.value = value;
    this.expression = expression;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Lvalue getValue() {
    return value;
  }

  public Exp getExpression() {
    return expression;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}