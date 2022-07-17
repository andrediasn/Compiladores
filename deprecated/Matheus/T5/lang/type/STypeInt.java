/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.type;

public class STypeInt extends SType {

  private static STypeInt st = new STypeInt();

  private STypeInt() {}

  public static STypeInt newSTypeInt() {
    return st;
  }

  @Override
  public boolean match(SType v) {
    return (v instanceof STypeInt);
  }

  public String toString() {
    return "Int";
  }
}
