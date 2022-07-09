/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class Caracter extends Exp {

  private int line, column;
  private char value;

  public Caracter(int line, int column, char value) {
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

  public char getValue() {
    return value;
  }


  public void accept(Visitor v) {
    v.visit(this);
  }

}
