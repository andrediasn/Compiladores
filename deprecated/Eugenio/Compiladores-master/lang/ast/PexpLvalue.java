/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class PexpLvalue extends Pexp {
    private Lvalue lvalue;

    public PexpLvalue (int line, int colunm, Lvalue lvalue){
        super(line,colunm);
        this.lvalue=lvalue;
    }

    public Lvalue getLvalue(){
        return this.lvalue;
    }

    //@Override
    public String toString(){
        return lvalue.toString();
    }

    public void accept(Visitor v){v.visit(this);}
}
