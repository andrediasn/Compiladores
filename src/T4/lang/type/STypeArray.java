/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
