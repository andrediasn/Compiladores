/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class CmdArray extends Cmd{
	private int line, column;
  private Cmd[] array;

  public CmdArray(int line , int column, Cmd[] array)	{
    this.line = line;
    this.column = column;
    this.array = array;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Cmd[] getArray() { 
	return array; 
	}


  public void accept(Visitor v) {
		v.visit(this); 
	}
}
