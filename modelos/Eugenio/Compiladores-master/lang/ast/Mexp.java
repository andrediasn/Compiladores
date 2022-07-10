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

public class Mexp extends Node{
    private ArrayList<SexpValue> sexps;
    private ArrayList<String> signals;

    public Mexp (int line, int colunm, SexpValue sexp){
        super(line,colunm);
        this.sexps = new ArrayList<SexpValue>();
        this.signals = new ArrayList<String>();
        sexps.add(sexp);
    }

    public void addSexp(String signal, SexpValue sexp){
        this.sexps.add(sexp);
        this.signals.add(signal);
    }

    public ArrayList<SexpValue> getSexps(){
        return this.sexps;
    }

    public ArrayList<String> getSignals(){
        return this.signals;
    }

    //@Override
    public String toString(){
        String saida= sexps.get(0).toString();
        for(int i=0;i<signals.size();i++){
            saida+= signals.get(i) + sexps.get(i+1);
        }
        return saida;
    }
    
    public void accept(Visitor v){v.visit(this);}
}
