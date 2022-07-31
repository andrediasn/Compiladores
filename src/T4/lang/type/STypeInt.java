/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
