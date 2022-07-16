/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class StmtList extends Cmd{
	private int line, column;
  private Cmd[] stmtList;

  public StmtList(int line , int column, Cmd[] stmtList)	{
    this.line = line;
    this.column = column;
    this.stmtList = stmtList;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Cmd[] getList() { 
	  return stmtList; 
	}


  public void accept(Visitor v) {
		v.visit(this); 
	}
}
