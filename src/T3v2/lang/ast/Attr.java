/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Attr extends Cmd{

  private int line, column;
  private LValue lvalue;
  private Exp expression;

  public Attr(int line, int column, LValue lvalue, Exp expression)	{
    this.line = line;
    this.column = column;
    this.lvalue = lvalue;
    this.expression = expression;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public LValue getValue() {
    return lvalue;
  }

  public Exp getExpression() {
    return expression;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}