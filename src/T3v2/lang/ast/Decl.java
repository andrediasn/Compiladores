/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Decl extends SuperNode {

	private int line, column;
  private String id;
  private Type type;

  public Decl(int line, int column, String id, Type type) {
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

  public String getId() {
    return id;
  }

  public Type getType() {
    return type;
  }
  
  public void accept(Visitor v) { 
		v.visit(this); 
	}
}
