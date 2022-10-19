/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

public class STyNull extends SType{
    private static STyNull st = new STyNull();

    private STyNull(){}

    public static STyNull newSTyNull(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyNull) || (v instanceof STyArr) || (v instanceof STyData);
    }

    public String toString(){
        return "Null";
    }
}
