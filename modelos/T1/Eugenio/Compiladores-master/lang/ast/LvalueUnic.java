/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import java.util.ArrayList;

import lang.visitor.Visitor;

public class LvalueUnic extends Lvalue{
    private ID id;
    private ArrayList<Lvalue> lvalues;

    public LvalueUnic (int line, int colunm, ID id){
        super(line,colunm);
        this.id=id;
        this.lvalues = new ArrayList<Lvalue>();
    }

    public void addLvalue(Lvalue lvalue){
        this.lvalues.add(lvalue);
    }

    public ID getId(){
        return this.id;
    }

    public ArrayList<Lvalue> getLvalues(){
        return this.lvalues;
    }

    //@Override
    public String toString(){
        String saida=id.toString();
        for(int i=0;i<this.lvalues.size();i++){
            saida+=this.lvalues.get(i).toString();
        }
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}