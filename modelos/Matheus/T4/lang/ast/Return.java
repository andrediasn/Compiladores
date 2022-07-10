/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class Return extends Cmd{

  private int line, column;
  private Exp[] expressions;

  public Return(int line, int column, Exp[] expressions)
	{
    this.line = line;
    this.column = column;
    this.expressions = expressions;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  public Exp[] getExpressions()
  {
    return expressions;
  }


  public void accept(Visitor v) { 
		v.visit(this);
  }
}