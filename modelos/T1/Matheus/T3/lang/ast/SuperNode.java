/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.ast;

import lang.visitor.Visitor;

public abstract class SuperNode {
   
    public abstract int getLine();
    public abstract int getColumn();

		public abstract void accept(Visitor v);
}


