/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

public abstract class Cmd extends Node{
    public Cmd (int line, int colunm){
        super(line,colunm);
    }
}
