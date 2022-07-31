/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.type;

public class STypeFunc {

  private SType[] returnable;
  private SType[] parameter;
  private String id;

  public SType[] getReturnable() {
    return returnable;
  }

  public SType[] getParameter() {
    return parameter;
  }

  public String getId() {
    return id;
  }

  public STypeFunc(String id, SType[] parameter, SType[] returnable) {
    this.id = id;
    this.returnable = returnable;
    this.parameter = parameter;
  }
}
