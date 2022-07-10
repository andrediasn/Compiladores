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

public class Rexp extends Node{
    private Aexp aexpLess1;
    private Aexp aexpLess2;
    private ArrayList<String> signal;
    private ArrayList<Aexp> aexp;

    public Rexp (int line, int colunm, Aexp aexpLess1){
        super(line,colunm);
        this.aexpLess1 = aexpLess1;
        this.aexpLess2 = null;
        this.signal = new ArrayList<String>();
        this.aexp = new ArrayList<Aexp>();
    }

    public Rexp (int line, int colunm, Aexp aexpLess1, Aexp aexpLess2){
        super(line,colunm);
        this.aexpLess1 = aexpLess1;
        this.aexpLess2 = aexpLess2;
        this.signal = new ArrayList<String>();
        this.aexp = new ArrayList<Aexp>();
    }

    public void addAexp(String signal, Aexp aexp){
        this.signal.add(0,signal);
        this.aexp.add(0,aexp);
    }

    public Aexp getAexpLess1(){
        return this.aexpLess1;
    }

    public Aexp getAexpLess2(){
        return this.aexpLess2;
    }

    public ArrayList<String> getSignal(){
        return this.signal;
    }

    public ArrayList<Aexp> getAexp(){
        return this.aexp;
    }

    //@Override
    public String toString(){
        String saida = aexpLess1.toString();
        if(aexpLess2!=null){
            saida += " < " + aexpLess2.toString();
        }
        for(int i=0;i<signal.size();i++){
            saida += signal.get(i) + aexp.get(i).toString();
        }
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
