/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class Print extends Cmd {
    private Exp exp;

    public Print (int line, int colunm, Exp exp){
        super(line,colunm);
        this.exp=exp;
    }

    public Exp getExp(){
        return this.exp;
    }

    //@Override
    public String toString(){
        return "print " + exp.toString() + ";\n"; 
    }

    public void accept(Visitor v){v.visit(this);}
}
