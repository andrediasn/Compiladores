/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
