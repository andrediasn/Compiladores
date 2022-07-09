/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

public class StyNull extends Stype{
    
    private static StyNull st = new StyNull();
    
    private StyNull(){};

    public static StyNull newStyNull(){
        return st;
    }

    public boolean match(Stype v){
        return (v instanceof StyNull);
    }

    public String toString(){
        return "null";
    }
}
