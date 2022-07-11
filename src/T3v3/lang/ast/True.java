/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class True extends Exp {

  private int line, column;

  public True(int line, int column) 
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

  public boolean getValue() 
  {
    return true;
  }


  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}
