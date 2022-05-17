/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class SexpValueChar extends SexpValue {
    String value;
    public SexpValueChar (int line, int colunm, String value){
        super(line,colunm);
        this.value=value;
    }

    //@Override
    public String toString(){
        return  "'"+value+"'";
    }

    public String getValue(){
        return this.value;
    }

    public void accept(Visitor v){v.visit(this);}
}
