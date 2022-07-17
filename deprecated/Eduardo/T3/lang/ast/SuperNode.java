/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public abstract class SuperNode {
   
   // The line and column of the node in the input text
   
    public abstract int getLine();
    public abstract int getColumn();

    public abstract void accept(Visitor v);

}


