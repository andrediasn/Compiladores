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

public class Exp extends Node{
    private ArrayList<Rexp> rexps;

    public Exp (int line, int colunm){
        super(line,colunm);
        this.rexps = new ArrayList<Rexp>();
    }

    public void addRexp(Rexp rexp){
        this.rexps.add(0,rexp);
    }

    public ArrayList<Rexp> getRexps(){
        return this.rexps;
    }
    
    //@Override
    public String toString(){
        String saida = rexps.get(0).toString();
        for(int i=1;i<rexps.size();i++){
            saida+=" && " + rexps.get(i).toString();
        }
        return saida; 
    }
    
    public void accept(Visitor v){v.visit(this);}
    //se size==1 so retorna o ser, se nao, retorna and da porra toda
}
