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

public class LValue extends Exp {

  private int line, column;
  private String id;
  private ArrayList<Selector> selectors;

  public LValue(int line, int column, String id) {
    this.id = id;
    this.selectors = new ArrayList<Selector>();
    this.line = line;
    this.column = column;
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
    return selectors;
  }

  public void add(Selector s) {
    selectors.add(s);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

}
