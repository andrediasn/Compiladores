/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
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
