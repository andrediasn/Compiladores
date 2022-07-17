/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class Program extends SuperNode {

  private Data[] datas;
  private Func[] fs;
  private int line;
  private int column;

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public Program(int l, int c, Data[] dat, Func[] f) {
    fs = f;
    datas = dat;
    line = l;
    column = c;
  }

  public Func[] getFuncs() {
    return fs;
  }

  public Data[] getDatas() {
    return datas;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
