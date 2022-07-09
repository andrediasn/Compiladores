/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor.types;

public class StyChar extends Stype{
    
    private static StyChar st = new StyChar();
    
    private StyChar(){};

    public static StyChar newStyChar(){
        return st;
    }

    public boolean match(Stype v){
        return (v instanceof StyChar);
    }

    public String toString(){
        return "char";
    }
}
