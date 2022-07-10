/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class CallCmd extends Cmd {

  private int line, column;
  private String name;
  private Exp[] expressions;
  private Lvalue[] returnable;

  public CallCmd(int line, int column, String name, Exp[] expressions, Lvalue[] returnable)	{
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

  public Lvalue[] getReturnable() {
    return returnable;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
