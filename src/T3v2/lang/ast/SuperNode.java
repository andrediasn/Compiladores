/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import lang.visitor.Visitor;

public abstract class SuperNode {
   
    public abstract int getLine();
    public abstract int getColumn();
		public abstract void accept(Visitor v);
}


