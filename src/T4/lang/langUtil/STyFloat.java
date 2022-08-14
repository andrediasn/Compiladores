/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

public class STyFloat extends SType
{
    private static STyFloat st = new STyFloat();

    private STyFloat(){}

    public static STyFloat newSTyFloat() { return st; }

    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyFloat);
    }

    public String toString()
    {
        return "Float";
    }
}