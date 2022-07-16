/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Read extends Cmd{

  private int line, column;
  private Var var;

  public Read(int line, int column, Var var)	{
    this.line = line;
    this.column = column;
    this.var = var;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Var getValue() {
    return var;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}