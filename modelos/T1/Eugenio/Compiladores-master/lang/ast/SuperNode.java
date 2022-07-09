/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitable;
import lang.visitor.Visitor;

public abstract class SuperNode implements Visitable  {
   
   // The line and column of the node in the input text
   
    public abstract int getLine();
    public abstract int getColumn();
    public abstract void accept(Visitor v);
}


