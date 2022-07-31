/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.ast;

public abstract class BinOP extends Expr {

      private Expr l;
      private Expr r;
      private int line;
      private int column;

      public BinOP(int li, int c, Expr l, Expr r) {
            line = li;
            column = c;
            this.l = l;
            this.r = r;
      }

      public int getLine() {
            return line;
      }

      public int getColumn() {
            return column;
      }

      public void setLeft(Expr n) {
            l = n;
      }

      public void setRight(Expr n) {
            r = n;
      }

      public Expr getLeft() {
            return l;
      }

      public Expr getRight() {
            return r;
      }

}
