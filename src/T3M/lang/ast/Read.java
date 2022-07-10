/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Read extends Cmd{

  private int line, column;
  private Lvalue value;

  public Read(int line, int column, Lvalue value)	{
    this.line = line;
    this.column = column;
    this.value = value;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Lvalue getValue() {
    return value;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}