/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.type;

public class STypeChar extends SType {

  private static STypeChar st = new STypeChar();

  private STypeChar() {}

  public static STypeChar newSTypeChar() {
    return st;
  }

  @Override
  public boolean match(SType v) {
    return (v instanceof STypeChar);
  }

  public String toString() {
    return "Char";
  }
}
