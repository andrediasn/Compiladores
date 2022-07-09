/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class CallExp extends Exp {

  private int line, column;
  private String name;
  private Exp[] expressions;
  private Exp returnable;

  public CallExp(int line, int column, String name, Exp[] expressions, Exp returnable) {
    this.name = name;
    this.expressions = expressions;
    this.returnable = returnable;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getName() {
    return name;
  }

  public Exp[] getExpressions() {
    return expressions;
  }

  public Exp getReturnable() {
    return returnable;
  }


  public void accept(Visitor v) {
    v.visit(this);
  }
}
