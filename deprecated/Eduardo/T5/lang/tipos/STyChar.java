/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;

public class STyChar extends STipo{
    private static STyChar st = new STyChar();

    private STyChar(){}

    public static STyChar newSTyChar(){ return st; }


    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyChar);
    }

    public String toString(){
        return "Char";
    }
}
