/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyInt extends STipo
{
    private static STyInt st = new STyInt();

    private STyInt(){}

    public static STyInt newSTyInt() { return st; }

    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyInt);
    }

    public String toString()
    {
        return "Int";
    }
}
