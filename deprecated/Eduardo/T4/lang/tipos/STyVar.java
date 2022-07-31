/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyVar extends STipo{
    private static STyVar st = new STyVar();

    private STyVar(){}

    public static STyVar newSTyVar(){ return st; }


    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyVar);
    }

    public String toString(){
        return "Var";
    }
}
