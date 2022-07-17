/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;

public class Data extends SuperNode
{
  private int line, column;
  private String id;
  private TData[] types;

  public Data(int line, int column, String id, TData[] types) {
    this.line = line;
    this.column = column;
    this.id = id;
    this.types = types;
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

  public TData[] getTypes() {
    return types;
  }


  public void accept(Visitor v) { 
		v.visit(this); 
	}
}