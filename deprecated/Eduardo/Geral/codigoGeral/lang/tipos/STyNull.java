/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyNull extends STipo{
    private static STyNull st = new STyNull();

    private STyNull(){}

    public static STyNull newSTyNull(){ return st; }


    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyNull) || (v instanceof STyArray) || (v instanceof STyData);
    }

    public String toString(){
        return "Null";
    }
}
