/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

public class StyArr extends Stype{
    
    private Stype a;
    
    public StyArr(Stype t){
        a=t;
    }

    public Stype getArg(){
        return a;
    }

    public boolean match(Stype v){
        return ((v instanceof StyArr) && a.match( ((StyArr)v).getArg() ));
    }

    public String toString(){
        return a.toString()+"[]";
    }
}
