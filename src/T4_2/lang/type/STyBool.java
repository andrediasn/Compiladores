/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.tipos;

public class STyBool extends STipo
{
    private static STyBool st = new STyBool();

    private STyBool(){}

    public static STyBool newSTyBool(){ return st; }


    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyBool);
    }

    public String toString(){
        return "Bool";
    }
}
