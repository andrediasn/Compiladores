/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class IntegerT extends Exp {

  private int line, column;
  private int value;

	public IntegerT(int line, int column, int value) {
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

  public int getValue() {
    return value;
  }


  public void accept(Visitor v) {
    v.visit(this);
  }

}
