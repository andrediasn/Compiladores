/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.ast;

import lang.visitor.Visitor;
import java.util.*;

public class Lvalue extends Exp {

  private int line, column;
  private String id;
  private ArrayList<Selector> selectors;


  public Lvalue(int line, int column, String id) {
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