/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.type;

public class STypeNull extends SType {

  private static STypeNull st = new STypeNull();

  private STypeNull() {}

  public static STypeNull newSTypeNull() {
    return st;
  }

  @Override
  public boolean match(SType v) {
    return (
      (v instanceof STypeNull) ||
      (v instanceof STypeArray) ||
      (v instanceof STypeData)
    );
  }

  public String toString() {
    return "Null";
  }
}
