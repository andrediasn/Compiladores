/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

import java.util.ArrayList;
import java.util.HashMap;

public class StyFunc extends Stype {
    private ArrayList<Stype> ret;
    private HashMap<String,Stype> vars;
    
    public StyFunc(ArrayList<Stype> ret){
        this.ret=ret;
    }

    public void setVars(HashMap<String,Stype> vars){
        this.vars=vars;
    }

    public HashMap<String,Stype> getVars(){
        return this.vars;
    }

    public ArrayList<Stype> getRet(){
        return this.ret;
    }


    public boolean match(Stype v){
        boolean r=false;
        if(v instanceof StyFunc){
            if(((StyFunc)v).getRet().size() == this.ret.size()){
                r=true;
                for(int i=0;i<this.ret.size();i++){
                    r= r && this.ret.get(i).match(((StyFunc)v).getRet().get(i));
                }
            }
        }
        return r;
    }

    public String toString(){
        String s="(";
        if(!this.ret.isEmpty()){
            s = s + this.ret.get(0).toString();
            for(int i=1;i<this.ret.size();i++){
                s = s + ", " + this.ret.get(i).toString();
            }
        }
        s=s+")";
        return s;
    }
}