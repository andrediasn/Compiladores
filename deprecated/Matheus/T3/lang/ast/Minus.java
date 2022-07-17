/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;

public class Minus extends BinOp {

  public Minus(int line, int column, Exp left, Exp right){
    super(line, column, left, right);
  }


  public void accept(Visitor v) {
    v.visit(this);
  }
}
