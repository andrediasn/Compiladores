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

public class PexpFunction extends Pexp{
    private ID id;
    private ArrayList<Exp> exps;
    private Exp exp;

    public PexpFunction (int line, int colunm, ID id,Exp exp){
        super(line,colunm);
        this.id=id;
        this.exp=exp;
        this.exps=new ArrayList<Exp>();
    }

    public void addExp(Exp exp){
        this.exps.add(exp);
    }

    public ID getId(){
        return this.id;
    }

    public ArrayList<Exp> getExps(){
        return this.exps;
    }

    public Exp getExp(){
        return this.exp;
    }

    //@Override
    public String toString(){
        String s= id.toString() + "(";
        if(!exps.isEmpty()){
            s+=exps.get(0).toString();
        }
        for(int i=1;i<exps.size();i++){
            s+=", " + exps.get(i).toString();
        }
        s+=")[" + exp.toString() + "]";
        return s;
    }

    public void accept(Visitor v){v.visit(this);}
}
