/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class Prog extends SuperNode {

  private int line, column;
  private Data[] datas;
  private Func[] funcs;


  public Prog(int line, int column, Data[] data, Func[] func) {
    this.line = line;
    this.column = column;
	this.datas = data;
	this.funcs = func;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Data[] getDatas() {
    return datas;
  }

  public Func[] getFuncs() {
    return funcs;
  }

  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}
