/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class If extends Cmd{

  private int line, column;
  private Cmd the, els;
  private Exp expression;

  public If(int line, int column, Exp expression, Cmd the, Cmd els)	{
    this.line = line;
    this.column = column;
    this.expression = expression;
    this.the = the;
    this.els = els;
  }

  public If(int line, int column, Exp expression, Cmd the) {
    this.line = line;
    this.column = column;
    this.expression = expression;
    this.the = the;
  }

  public int getLine()  {
    return line;
  }

  public int getColumn()  {
    return column;
  }

  public Exp getExpression()  {
    return expression;
  }

  public Cmd getThe()  {
    return the;
  }

  public Cmd getEls()  {
    return els;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}