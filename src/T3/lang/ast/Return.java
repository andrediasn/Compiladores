/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.ASTVisitor.Visitor;

public class Return extends Cmd{

  private int line, column;
  private Exp[] exps;

  public Return(int line, int column, Exp[] exps)
	{
    this.line = line;
    this.column = column;
    this.exps = exps;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  public Exp[] getExps()
  {
    return exps;
  }


  public void accept(Visitor v) 
  { 
		v.visit(this);
  }
}