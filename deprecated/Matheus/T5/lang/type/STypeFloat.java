/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
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
