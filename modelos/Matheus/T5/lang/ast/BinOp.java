/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

public abstract class BinOp extends Exp {

  private int line, column;
  private Exp left;
  private Exp right;
 
  public BinOp(int line, int column, Exp left, Exp right)
	{
    this.line = line;
    this.column = column;
    this.left = left;
    this.right = right;
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public void setLeft(Exp left) {
    this.left = left;
  }

  public void setRight(Exp right) {
    this.right = right;
  }

  public Exp getLeft() {
    return left;
  }

  public Exp getRight() {
    return right;
  }

}