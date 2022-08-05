/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

public class STyVar extends SType{
    private static STyVar st = new STyVar();

    private STyVar(){}

    public static STyVar newSTyVar(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyVar);
    }

    public String toString(){
        return "Var";
    }
}
