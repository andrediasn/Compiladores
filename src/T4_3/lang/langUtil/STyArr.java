/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyArr extends SType
{
    private SType a;

    public STyArr(SType t)
    {
        a = t;
    }

    public SType getArg()
    {
        return a;
    }

    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyNull) || (v instanceof STyArr) && (a.match( ((STyArr)v).getArg() ));
    }

    public String toString() {
        return a.toString() + "[]";
    }
}
