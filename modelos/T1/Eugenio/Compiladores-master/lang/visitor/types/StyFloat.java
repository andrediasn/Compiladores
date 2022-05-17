/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

public class StyFloat extends Stype{
    
    private static StyFloat st = new StyFloat();
    
    private StyFloat(){};

    public static StyFloat newStyFloat(){
        return st;
    }

    public boolean match(Stype v){
        return (v instanceof StyFloat);
    }

    public String toString(){
        return "double";
    }
}
