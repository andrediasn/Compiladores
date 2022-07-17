/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.ast;

import lang.visitors.Visitor;
import java.util.*;

public class LValue extends Expr {

  private int line, column;
  private String id;
  private ArrayList<Selector> s;

  public LValue(int line, int column, String id) {
    this.line = line;
    this.column = column;
    this.id = id;
    this.s = new ArrayList<Selector>();
  }

  public int getLine() {
    return line;
  }

  public int getColumn() {
    return column;
  }

  public String getId() {
    return id;
  }

  public ArrayList<Selector> getSelectors() {
    return s;
  }

  public void add(Selector x) {
    s.add(x);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
