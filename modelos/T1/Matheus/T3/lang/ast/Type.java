/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class Type extends SuperNode {
	
	private int line, column;
  private BType typeB;
  private int braces;

  public Type(int line, int column, BType typeB, int braces) {
    this.typeB = typeB;
    this.braces = braces;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public BType getBtype() {
    return typeB;
  };

  public int getBraces() {
		return braces;
  };


  public void accept(Visitor v) {
    v.visit(this);
  }
}
