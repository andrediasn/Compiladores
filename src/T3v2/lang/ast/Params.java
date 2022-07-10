/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Params extends SuperNode {

  private int line, column;
  private String id;
  private Type type;

  public Params(int line, int column, String id, Type type) {
    this.id = id;
    this.type = type;
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

  public Type getType() {
    return type;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
