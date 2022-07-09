/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class Type extends SuperNode {
	
  private int line, column;
  private Btype btype;

  public Type(int line, int column, Btype btype) 
  {
    
	this.line = line;
    this.column = column;
	this.btype = btype;

  }

  public int getLine() 
  {
    return line;
  }

  public int getColumn() 
  {
    return column;
  }

  public Btype getBtype() 
  {
    return btype;
  };

  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}
