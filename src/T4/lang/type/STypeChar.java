/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
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
