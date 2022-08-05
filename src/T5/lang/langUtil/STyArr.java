/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.langUtil;

public class STyArr extends SType
{
    private SType a;

    public STyArr(SType t)
    {
        a = t;
    }

    public SType getArg()
    {
        return a;
    }

    @Override
    public boolean match(SType v)
    {
        return (v instanceof STyNull) || (v instanceof STyArr) && (a.match( ((STyArr)v).getArg() ));
    }

    public String toString() {
        return a.toString() + "[]";
    }
}
