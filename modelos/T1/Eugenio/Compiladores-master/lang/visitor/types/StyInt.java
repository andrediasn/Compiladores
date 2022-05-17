/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

public class StyInt extends Stype{
    
    private static StyInt st = new StyInt();
    
    private StyInt(){};

    public static StyInt newStyInt(){
        return st;
    }

    public boolean match(Stype v){
        return (v instanceof StyInt);
    }

    public String toString(){
        return "int";
    }
}
