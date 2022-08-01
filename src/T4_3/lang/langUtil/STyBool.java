/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyBool extends SType
{
    private static STyBool st = new STyBool();

    private STyBool(){}

    public static STyBool newSTyBool(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyBool);
    }

    public String toString(){
        return "Bool";
    }
}
