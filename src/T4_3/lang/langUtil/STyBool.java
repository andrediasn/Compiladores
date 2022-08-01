/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

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
