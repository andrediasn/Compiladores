/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

import java.util.HashMap;

public class StyData extends Stype{

    private HashMap<String,Stype> a;
    private String id;

    public StyData(String id, HashMap<String,Stype> t){
        this.a=t;
        this.id=id;
    }

    public HashMap<String,Stype> getCampos(){
        return a;
    }

    public String getID(){
        return this.id;
    }

    public boolean match(Stype v){
        if(v instanceof StyData){
            if(this.id.equals(((StyData)v).getID())){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return id;
    }
}