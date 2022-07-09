/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class PExp extends Exp {

  private int line, column;
  private String name;
  private Exp[] exps;
  private Exp returnable;

  public PExp(int line, int column, String name, Exp[] exps, Exp returnable) 
  {
    this.name = name;
    this.exps = exps;
    this.returnable = returnable;
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

  public Exp[] getExps()
  {
    return exps;
  }

  public Exp getReturnable()
  {
    return returnable;
  }


  public void accept(Visitor v)
  {
    v.visit(this);
  }
}
