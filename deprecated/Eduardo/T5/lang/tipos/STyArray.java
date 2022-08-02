/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyArray extends STipo
{
    private STipo a;

    public STyArray(STipo t)
    {
        a = t;
    }

    public STipo getArg()
    {
        return a;
    }

    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyNull) || (v instanceof STyArray) && (a.match( ((STyArray)v).getArg() ));
    }

    public String toString() {
        return a.toString() + "[]";
    }
}
