/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
