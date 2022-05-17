/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class SexpValueFloat extends SexpValue {
    float value;
    public SexpValueFloat (int line, int colunm, float value){
        super(line,colunm);
        this.value=value;
    }

    public Float getValue(){
        return this.value;
    }

    //@Override
    public String toString(){
        return Float.toString(value);
    }

    public void accept(Visitor v){v.visit(this);}
}
