/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class Char extends Btype
{
  private int line, column;

  public Char(int line, int column)
  {
    this.line = line;
    this.column = column;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }


  public void accept(Visitor v) 
  { 
	v.visit(this); 
  }
}