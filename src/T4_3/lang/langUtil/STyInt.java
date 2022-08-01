/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

public class STyInt extends SType
{
    private static STyInt st = new STyInt();

    private STyInt(){}

    public static STyInt newSTyInt() { return st; }

    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyInt);
    }

    public String toString()
    {
        return "Int";
    }
}
