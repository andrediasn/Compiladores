/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class Assign extends Cmd{

  private int line, column;
  private Lvalue value;
  private Exp expression;

  public Assign(int line, int column, Lvalue value, Exp expression)
	{
    this.line = line;
    this.column = column;
    this.value = value;
    this.expression = expression;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  public Lvalue getValue()
  {
    return value;
  }

  public Exp getExpression()
  {
    return expression;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}