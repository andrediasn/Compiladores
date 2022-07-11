/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitor.Visitor;

public class Less extends BinOp {

  public Less(int line, int column, Exp left, Exp right)
  {
    super(line, column, left, right);
  }


  public void accept(Visitor v)
  {
  	v.visit(this);
  }
}
