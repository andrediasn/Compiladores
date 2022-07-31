/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.type;

public class STypeFloat extends SType {

  private static STypeFloat st = new STypeFloat();

  private STypeFloat() {}

  public static STypeFloat newSTypeFloat() {
    return st;
  }

  @Override
  public boolean match(SType v) {
    return (v instanceof STypeFloat);
  }

  public String toString() {
    return "Float";
  }
}
