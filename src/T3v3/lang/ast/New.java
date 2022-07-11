/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class New extends Exp {

  private int line,  column;
  private Type type;
  private Exp exp;

  public New(int line, int column, Type type, Exp exp) 
  {
    this.type = type;
    this.exp = exp;
    this.line = line;
    this.column = column;
  }

  public int getLine() 
  {
    return line;
  }

  public int getColumn() 
  {
    return column;
  }

  public Type getType() 
  {
    return type;
  }

  public Exp getExp() 
  {
    return exp;
  }


  public void accept(Visitor v) 
  {
    v.visit(this);
  }
}
