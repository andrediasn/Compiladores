/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class Read extends Cmd{

  private int line, column;
  private Lvalue lvalue;

  public Read(int line, int column, Lvalue lvalue)
	{
    this.line = line;
    this.column = column;
    this.lvalue = lvalue;
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


  public void accept(Visitor v) 
  { 
	v.visit(this); 
  }
}