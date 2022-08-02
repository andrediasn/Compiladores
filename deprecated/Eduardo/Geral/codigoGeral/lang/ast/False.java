/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

import lang.visitors.Visitor;

public class False extends Expr
{
      private int line;
      private int column;

      public int getLine() {
            return line;
      }

      public int getColumn() {
            return column;
      }

      public False(int l, int c){
            line = l;
            column = c;
      }
      
      public boolean getValue(){ return false;}

      public void accept(Visitor v) { v.visit(this); }

}
