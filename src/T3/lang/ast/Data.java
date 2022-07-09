/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.ASTVisitor.Visitor;

public class Data extends SuperNode
{
  private int line, column;
  /*private String id;
  private Decl[] recordDataTypes;*/

  public Data(int line, int column/*, String id, Decl[] recordDataTypes*/)
  {
    this.line = line;
    this.column = column;
    /*this.id = id;
    this.types = recordDataTypes;*/
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  /*public String getId()
  {
    return id;
  }

  public Decl[] getRecordDataTypes()
  {
    return recordDataTypes;
  }

  public void accept(Visitor v) 
  { 
	v.visit(this); 
  }*/
}