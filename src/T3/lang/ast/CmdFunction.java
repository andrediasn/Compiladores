/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class CmdFunction extends Cmd {

  private int line, column;
  private String name;
  private Exp[] exp;
  private Lvalue[] lvalue;

  public CmdFunction(int line, int column, String name, Exp[] exp, Lvalue[] lvalue)
	{
    this.name = name;
    this.exp = exp;
    this.lvalue = lvalue;
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

  public String getName() 
  {
    return name;
  }

  public Exp[] getExp() 
  {
    return exp;
  }

  public Lvalue[] getLvalue() 
  {
    return lvalue;
  }


  public void accept(Visitor v) 
  {
    v.visit(this);
  }

}
