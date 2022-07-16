/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Cmds extends Cmd{
	private int line, column;
  private Cmd[] cmds;

  public Cmds(int line , int column, Cmd[] cmds)	{
    this.line = line;
    this.column = column;
    this.cmds = cmds;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Cmd[] getList() { 
	  return cmds; 
	}


  public void accept(Visitor v) {
		v.visit(this); 
	}
}
