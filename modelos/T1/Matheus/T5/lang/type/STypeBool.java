/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.type;

public class STypeBool extends SType {

  private static STypeBool st = new STypeBool();

  private STypeBool() {}

  public static STypeBool newSTypeBool() {
    return st;
  }

  @Override
  public boolean match(SType v) {
    return (v instanceof STypeBool);
  }

  public String toString() {
    return "Bool";
  }
}
