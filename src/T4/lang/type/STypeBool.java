/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
