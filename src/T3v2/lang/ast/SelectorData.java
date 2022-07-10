/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class SelectorData extends Selector{

	private int line,  column;
  private String index;

  public SelectorData(int line, int column, String index){
    this.index = index;
    this.line = line;
    this.column = column;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getIndex() { 
		return index; 
	}

  public void accept(Visitor v) { 
		v.visit(this); 
	}
}
