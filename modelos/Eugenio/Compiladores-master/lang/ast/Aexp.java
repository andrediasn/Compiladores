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

public class Aexp extends Node{
    private ArrayList<Mexp> mexps;
    private ArrayList<String> signals;

    public Aexp (int line, int colunm, Mexp mexp1){
        super(line,colunm);
        this.signals = new ArrayList<String>();
        this.mexps = new ArrayList<Mexp>();
        this.mexps.add(mexp1);
    }

    public void addMexp(String signal, Mexp mexp){
        this.signals.add(signal);
        this.mexps.add(mexp);
    }

    public ArrayList<Mexp> getMexps(){
        return this.mexps;
    }

    public ArrayList<String> getSignals(){
        return this.signals;
    }

    //@Override
    public String toString(){
        String saida = mexps.get(0).toString();
        for(int i=0;i<signals.size();i++){
            saida += " " + signals.get(i) + " " + mexps.get(i+1);
        }
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
