/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class SexpValueInt extends SexpValue {
    int value;
    public SexpValueInt (int line, int colunm, int value){
        super(line,colunm);
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }

    //@Override
    public String toString(){
        return Integer.toString(value);
    }

    public void accept(Visitor v){v.visit(this);}
}
