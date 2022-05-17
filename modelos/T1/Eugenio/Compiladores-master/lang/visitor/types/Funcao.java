package lang.visitor.types;

import java.util.ArrayList;

public class Funcao {
    public String id;
    public ArrayList<Stype> params;

    public Funcao(String id, ArrayList<Stype> params){
        this.id=id;
        this.params=params;
    }
    
    @Override
    public boolean equals(Object o) {
        boolean b=false;
        if(o instanceof Funcao){
            if(this.id.equals(((Funcao)o).id)){
                if(this.params.size()==((Funcao)o).params.size()){
                    b=true;
                    for(int i=0;i<this.params.size();i++){
                        b = b && this.params.get(i).match(((Funcao)o).params.get(i));
                    }
                }
            }
        }
        return b;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString(){
        String s=this.id;
        s=s+params.toString();
        return s;
    }
}