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

public class Prog extends Node{

    private ArrayList<Data> datas;
    private ArrayList<Func> funcs;
    
    public Prog (int line, int colunm){
        super(line,colunm);
        this.datas=new ArrayList<Data>();
        this.funcs=new ArrayList<Func>();
    }
    
    public void addData (Data data){
        this.datas.add(0,data);
    }

    public void addFunc (Func func){
        this.funcs.add(0,func);
    }

    public ArrayList<Func> getFuncs(){
        return this.funcs;
    } 

    public ArrayList<Data> getDatas(){
        return this.datas;
    }

    //@Override
    public String toString(){
        String saida = "";
        for (Data data : datas){
            saida+=data.toString();
            saida+='\n';
        }
        saida+='\n';
        for (Func func : funcs){
            saida+=func.toString();
            saida+='\n';
        }
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
