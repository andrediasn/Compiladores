/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Type extends SuperNode {
	
  private int line, column;
  private Btype btype;
  private int braces;

  public Type(int line, int column, Btype btype, int braces) 
  {
    
	this.line = line;
    this.column = column;
	this.btype = btype;
	this.braces = braces;

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
  
   public int getBraces() 
  {
		return braces;
  };

  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}
