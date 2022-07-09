/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class Iterate extends Cmd{

  private int line, column;
  private Exp exp;
  private Cmd cmd;

  public Iterate(int line, int column, Exp exp, Cmd cmd)
	{
    this.line = line;
    this.column = column;
    this.exp = exp;
    this.cmd = cmd;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  public Exp getExp()
  {
    return exp;
  }

  public Cmd getCmd()
  {
    return cmd;
  }


  public void accept(Visitor v) 
  { 
	v.visit(this); 
  }
}