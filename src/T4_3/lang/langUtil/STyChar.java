/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

public class STyChar extends SType{
    private static STyChar st = new STyChar();

    private STyChar(){}

    public static STyChar newSTyChar(){ return st; }


    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyChar);
    }

    public String toString(){
        return "Char";
    }
}
