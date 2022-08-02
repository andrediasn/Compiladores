/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.tipos;

public class STyNull extends STipo{
    private static STyNull st = new STyNull();

    private STyNull(){}

    public static STyNull newSTyNull(){ return st; }


    @Override
    public boolean match(STipo v)
    {
        return (v instanceof STyNull) || (v instanceof STyArray) || (v instanceof STyData);
    }

    public String toString(){
        return "Null";
    }
}
