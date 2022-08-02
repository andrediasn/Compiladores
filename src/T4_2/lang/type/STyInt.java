/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

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
