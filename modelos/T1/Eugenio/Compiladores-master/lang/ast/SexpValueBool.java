/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class SexpValueBool extends SexpValue {
    Boolean value;
    public SexpValueBool (int line, int colunm, Boolean value){
        super(line,colunm);
        this.value=value;
    }

    //@Override
    public String toString(){
        return Boolean.toString(value);
    }

    public Boolean getValue(){
        return this.value;
    }

    public void accept(Visitor v){v.visit(this);}
}
