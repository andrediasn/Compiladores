/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.type;

public class STypeVar extends SType {

  private static STypeVar st = new STypeVar();

  private STypeVar() {}

  public static STypeVar newSTypeVar() {
    return st;
  }

  @Override
  public boolean match(SType v) {
    return (v instanceof STypeVar);
  }

  public String toString() {
    return "Var";
  }
}
