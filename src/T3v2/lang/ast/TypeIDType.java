/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class TypeIDType extends BType {
  private int line, column;
  private String idType;

  public TypeIDType(int line, int column, String idType) {
    this.idType = idType;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getIdType() {
    return idType;
  }

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}