/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class SexpValuePexp extends SexpValue {
    Pexp value;
    public SexpValuePexp (int line, int colunm, Pexp value){
        super(line,colunm);
        this.value=value;
    }

    public Pexp getValue(){
        return this.value;
    }

    //@Override
    public String toString(){
        return value.toString();
    }

    public void accept(Visitor v){v.visit(this);}
}
