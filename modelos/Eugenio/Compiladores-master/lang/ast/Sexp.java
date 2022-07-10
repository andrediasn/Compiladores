/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class Sexp extends SexpValue {
    SexpValue sexpValue;
    String op;
    public Sexp (int line, int colunm, SexpValue sexpValue, String op){
        super(line,colunm);
        this.op = op;
        this.sexpValue = sexpValue;
    }

    public Sexp (int line, int colunm, SexpValue sexpValue){
        super(line,colunm);
        this.op = null;
        this.sexpValue = sexpValue;
    }

    public SexpValue getSexpValue(){
        return this.sexpValue;
    }

    public String getOperator(){
        return this.op;
    }
    
    //@Override
    public String toString(){
        String saida="";
        if(this.op!=null){
            saida+=op;
        }
        saida+=sexpValue.toString();
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
