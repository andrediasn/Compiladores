/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class PexpParenteses extends Pexp {
    private Exp exp;
    public PexpParenteses (int line, int colunm, Exp exp){
        super(line,colunm);
        this.exp=exp;
    }

    public Exp getExp(){
        return this.exp;
    }

    //@Override
    public String toString(){
        return "(" + exp.toString() + ")"; 
    }

    public void accept(Visitor v){v.visit(this);}
}
