/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class DoubleT extends Exp {
	
	private int line, column;
  private float value;

  public DoubleT(int line, int column, float value) {
    this.value = value;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public float getValue() {
    return value;
  }


  public void accept(Visitor v) {
    v.visit(this);
  }
}
