/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;
import java.util.*;

public class Lvalue extends Exp {

  private int line, column;
  private String id;
  private ArrayList<Slc> slc;


  public Lvalue(int line, int column, String id) {
    this.id = id;
    this.slc = new ArrayList<Slc>();
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

  public String getId()
  {
    return id;
  }

  public ArrayList<Slc> getslc() 
  {
    return slc;
  }

  public void add(Slc s) 
  {
    slc.add(s);
  }


  public void accept(Visitor v) 
  {
    v.visit(this);
  }

}
