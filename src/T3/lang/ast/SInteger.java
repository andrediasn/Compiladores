/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class SInteger extends Exp {

  private int line, column;
  private int value;

	public SInteger(int line, int column, int value) {
    this.value = value;
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

  public int getValue() 
  {
    return value;
  }


  public void accept(Visitor v) 
  {
    v.visit(this);
  }

}