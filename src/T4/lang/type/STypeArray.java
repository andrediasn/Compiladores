/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.type;

public class STypeArray extends SType {

  private SType arg;

  public STypeArray(SType t) {
    arg = t;
  }

  public SType getArg() {
    return arg;
  }

  @Override
  public boolean match(SType v) {
    return (
      (v instanceof STypeNull) ||
      (v instanceof STypeArray) &&
      (arg.match(((STypeArray) v).getArg()))
    );
  }

  public String toString() {
    return arg.toString() + "[]";
  }
}
