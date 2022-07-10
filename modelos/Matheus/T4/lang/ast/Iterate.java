/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class Iterate extends Cmd{

  private int line, column;
  private Exp expression;
  private Cmd body;

  public Iterate(int line, int column, Exp expression, Cmd body)
	{
    this.line = line;
    this.column = column;
    this.expression = expression;
    this.body = body;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  public Exp getExpression()
  {
    return expression;
  }

  public Cmd getBody()
  {
    return body;
  }


  public void accept(Visitor v) { 
		v.visit(this); 
	}
}