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

public class If extends Cmd{
    private Exp cond;
    private ArrayList<Cmd> then;
    private ArrayList<Cmd> els;

    public If (int line, int colunm, Exp cond){
        super(line,colunm);
        this.then = new ArrayList<Cmd>();
        this.els = new ArrayList<Cmd>();
        this.cond=cond;
    }

    public void addThen(Cmd then){
        this.then.add(0,then);
    }

    public void addEls(Cmd els){
        this.els.add(0,els);
    }

    public Exp getCond(){
        return this.cond;
    }

    public ArrayList<Cmd> getThen(){
        return this.then;
    }

    public ArrayList<Cmd> getElse(){
        return this.els;
    }

    //@Override
    public String toString(){
        String saida="if (" + cond.toString() + ") {\n";
        for(int i=0;i<then.size();i++){
            saida+=then.get(i).toString();
        }
        saida+="}\n";
        if(!els.isEmpty()){
            saida+="else{\n";
        }
        for(int i=0;i<els.size();i++){
            saida+=els.get(i).toString();
        }
        if(!els.isEmpty()){
            saida+="}\n";
        }
        return saida; 
    }
    
    public void accept(Visitor v){v.visit(this);}
}
