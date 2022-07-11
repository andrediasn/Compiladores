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
  private Cmd cIf, cElse;
  private Exp exp;

  public If(int line, int column, Exp exp, Cmd cIf, Cmd cElse)
	{
    this.line = line;
    this.column = column;
    this.exp = exp;
    this.cIf = cIf;
    this.cElse = cElse;
  }

  public If(int line, int column, Exp exp, Cmd cIf)
	{
    this.line = line;
    this.column = column;
    this.exp = exp;
    this.cIf = cIf;
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

  public Cmd getCIf()
  {
    return cIf;
  }

  public Cmd getCElse()
  {
    return cElse;
  }

  public void accept(Visitor v) 
  { 
		v.visit(this); 
  }
}