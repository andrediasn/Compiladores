/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Prog extends SuperNode {

  private int line, column;
  private Data[] datas;
  private Func[] funcs;

  public Prog(int line, int column, Data[] data, Func[] func) {
    this.funcs = func;
    this.datas = data;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Func[] getFuncs() {
    return funcs;
  }

  public Data[] getDatas() {
    return datas;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
