/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

public class StyBool extends Stype{
    
    private static StyBool st = new StyBool();
    
    private StyBool(){};

    public static StyBool newStyBool(){
        return st;
    }

    public boolean match(Stype v){
        return (v instanceof StyBool);
    }

    public String toString(){
        return "boolean";
    }
}
