/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Iterate extends Cmd {

  private Expr teste;
  private Cmd body;
  private int line;
  private int column;

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Iterate(int l, int c, Expr teste, Cmd body) {
    this.teste = teste;
    this.body = body;
    line = l;
    column = c;
  }

  public Expr getTeste() {
    return teste;
  }

  public Cmd getBody() {
    return body;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
