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
  private Lvalue lvalue;
  private Exp exp;

  public Assign(int line, int column, Lvalue lvalue, Exp exp)
	{
    this.line = line;
    this.column = column;
    this.lvalue = lvalue;
    this.exp = exp;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  public Lvalue getLvalue()
  {
    return lvalue;
  }

  public Exp getExp()
  {
    return exp;
  }

  public void accept(Visitor v) 
  { 
	v.visit(this); 
  }
}