/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
