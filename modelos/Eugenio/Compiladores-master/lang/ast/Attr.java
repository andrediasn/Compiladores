/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class Attr extends Cmd {
    private LvalueUnic lvalue;
    private Exp exp;

    public Attr (int line, int colunm, LvalueUnic lvalue, Exp exp){
        super(line,colunm);
        this.exp=exp;
        this.lvalue=lvalue;
    }

    public LvalueUnic getLvalue(){
        return this.lvalue;
    }

    public Exp getExp(){
        return this.exp;
    }
    //@Override
    public String toString(){
        return lvalue.toString() + " = " + exp.toString() + ";\n";
    }

    public void accept(Visitor v){v.visit(this);}
}
