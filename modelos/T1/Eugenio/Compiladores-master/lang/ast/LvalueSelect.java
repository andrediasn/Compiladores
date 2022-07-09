/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class LvalueSelect extends Lvalue {
    private ID id;

    public LvalueSelect (int line, int colunm, ID id){
        super(line,colunm);
        this.id=id;
    }

    public ID getID(){
        return this.id;
    }

    //@Override
    public String toString(){
        return "." + id.toString();
    }

    public void accept(Visitor v){v.visit(this);}
}