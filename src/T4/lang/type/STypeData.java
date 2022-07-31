/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.type;

import java.util.HashMap;

public class STypeData extends SType {

  private String id;
  public HashMap<String, SType> elem = new HashMap<String, SType>();

  public STypeData(String i) {
    this.id = i;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean match(SType v) {
    STypeData aux = null;
    boolean a = false;
    if (v instanceof STypeData) {
      aux = (STypeData) v;
      a = aux.getId() == id;
    }
    return (v instanceof STypeNull) || a;
  }

  public boolean matchData(SType v, String i) {
    return (v instanceof STypeNull) || (v instanceof STypeData && i == id);
  }

  public String toString() {
    return "Data: " + id;
  }
}
