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
  private Var var;
  private Exp expression;

  public Attr(int line, int column, Var var, Exp expression)	{
    this.line = line;
    this.column = column;
    this.var = var;
    this.expression = expression;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Var getValue() {
    return var;
  }

  public Exp getExpression() {
    return expression;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}