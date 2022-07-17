/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class CallCmd extends Cmd {

  private int line, column;
  private String name;
  private Exp[] exps;
  private LValue[] ret;

  public CallCmd(int line, int column, String name, Exp[] exps, LValue[] ret)	{
    this.name = name;
    this.exps = exps;
    this.ret = ret;
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
    return exps;
  }

  public LValue[] getReturnable() {
    return ret;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
