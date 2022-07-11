/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Caracter extends Exp {

  private int line, column;
  private char value;

  public Caracter(int line, int column, char value) 
  {
    
    this.line = line;
    this.column = column;
	this.value = value;
  }

  public int getLine() 
  {
    return line;
  }

  public int getColumn() 
  {
    return column;
  }

  public char getValue() 
  {
    return value;
  }

  public void accept(Visitor v) 
  {
    v.visit(this);
  }

}