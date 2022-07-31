/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyFloat extends STipo
{
    private static STyFloat st = new STyFloat();

    private STyFloat(){}

    public static STyFloat newSTyFloat() { return st; }

    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyFloat);
    }

    public String toString()
    {
        return "Float";
    }
}