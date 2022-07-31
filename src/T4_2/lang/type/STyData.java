/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.tipos;
import java.util.HashMap;
public class STyData extends STipo{
    private String id;
    public HashMap<String, STipo> elem = new HashMap<String, STipo>();
    public STyData(String i){id = i;}
    public String getId() {return id;}
    @Override
    public boolean match(STipo v)
    {
        STyData aux = null; 
        boolean a = false;
        if(v instanceof STyData)
        {
            aux = (STyData)v;
            a = aux.getId() == id;
        }
        return (v instanceof STyNull) || a;
    }

    public boolean matchData(STipo v, String i)
    {
        return (v instanceof STyNull) || (v instanceof STyData && i == id);
    }

    public String toString(){
        return "Data: " + id;        
    }
}
