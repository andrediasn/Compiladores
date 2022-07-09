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

public class Data extends Node{
    
    private ID id;
    private ArrayList<Decl> d;

    public Data (int line, int colunm, ID id){
        super(line,colunm);
        this.id = id;
        this.d = new ArrayList<Decl>();
    }

    public void addDecl(Decl d){
        this.d.add(0,d);
    }

    public ID getId(){
        return id;
    }

    public ArrayList<Decl> getDecl(){
        return this.d;
    }

    //@Override
    public String toString(){
        String saida = "data ";
        saida+=id.toString();
        saida+="{\n";
        for(int i=0;i<d.size();i++){
            saida+=d.get(i).toString();
        }
        saida+="}\n";
        return saida;
    }
    
    public void accept(Visitor v){v.visit(this);}
}
