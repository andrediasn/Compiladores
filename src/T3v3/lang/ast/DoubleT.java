/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class DoubleT extends Exp {
	
	private int line, column;
  private float value;

  public DoubleT(int line, int column, float value) 
  {
    
    this.line = line;
    this.column = column;
	this.value = value;
  }

  public int getLine() 
  {
    return line;
  }

  public int getColumn() 
  {
    return column;
  }

  public float getValue() 
  {
    return value;
  }

  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}
