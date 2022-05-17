/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class PexpNew extends Pexp {
    private Type type;
    private Exp exp;

    public PexpNew (int line, int colunm, Type type){
        super(line,colunm);
        this.type=type;
        exp=null;
    }

    public PexpNew (int line, int colunm, Type type, Exp exp){
        super(line,colunm);
        this.type=type;
        this.exp=exp;
    }

    public Type getType(){
        return this.type;
    }

    public Exp getExp(){
        return this.exp;
    }

    //@Override
    public String toString(){
        String saida = "new " + type.toString();
        if(exp!=null){
            saida+=" [" + exp.toString() + "] ";
        }
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
