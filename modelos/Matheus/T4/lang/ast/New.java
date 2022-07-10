/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class New extends Exp {

  private int line,  column;
  private Type type;
  private Exp expression;

  public New(int line, int column, Type type, Exp expression) {
    this.type = type;
    this.expression = expression;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Type getType() {
    return type;
  }

  public Exp getExpression() {
    return expression;
  }


  public void accept(Visitor v) {
    v.visit(this);
  }
}
