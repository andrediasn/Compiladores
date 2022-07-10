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
  private Type[] typeReturn;
  private Params[] params;
  private Cmd[] body;


  public Func(int line, int column, String id, Params[] params, Type[] typeReturn, Cmd[] body) {
    this.id = id;
    this.typeReturn = typeReturn;
    this.params = params;
    this.body = body;
    this.line = line;
    this.column = column;
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

  public Type[] getTypeReturn() {
    return typeReturn;
  }

  public Params[] getParams() {
    return params;
  }

  public Cmd[] getBody() {
    return body;
  }


  public void accept(Visitor v) {
    v.visit(this);
  }
}
