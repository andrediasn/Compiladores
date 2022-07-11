/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class SlcArray extends Slc {

  private int line, column;
  private Exp id;

  public SlcArray(int line, int column, Exp id) 
  {  
    this.line = line;
    this.column = column;
	this.id = id;
  }

  public int getLine() 
  {
    return line;
  }

  public int getColumn() 
  {
    return column;
  }

  public Exp getId() 
  {
    return id;
  }

  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}
