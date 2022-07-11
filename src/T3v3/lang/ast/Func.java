/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Func extends SuperNode {

  private int line, column;
  private String id;
  private Params[] params;
  private Type[] returnFunction; 
  private Cmd[] cmds;


  public Func(int line, int column, String id, Type[] returnFunction, Params[] params,  Cmd[] cmds) {
    
	this.line = line;
    this.column = column;
	this.id = id;
    this.returnFunction = returnFunction;
    this.params = params;
    this.cmds = cmds;

  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getID() {
    return id;
  }

  public Type[] getReturnFunction() {
    return returnFunction;
  }

  public Params[] getParams() {
    return params;
  }

  public Cmd[] getCmds() {
    return cmds;
  }

  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}