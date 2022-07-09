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

public class Func extends Node{
    private ID id;
    private ArrayList<ID> idsParams;
    private ArrayList<Type> typeParams;
    private ArrayList<Type> typeReturn;
    private ArrayList<Cmd> cmds;
    
    public Func (int line, int colunm, ID id){
        super(line,colunm);
        this.id = id;
        this.idsParams = new ArrayList<ID>();
        this.typeParams = new ArrayList<Type>();
        this.typeReturn = new ArrayList<Type>();
        this.cmds = new ArrayList<Cmd>();
    }

    public void addParam(ID id, Type type){
        this.typeParams.add(0,type);
        this.idsParams.add(0,id);
    }

    public void addReturn(Type ret){
        this.typeReturn.add(0,ret);
    }

    public void addCmd (Cmd cmd){
        this.cmds.add(0,cmd);
    }

    public ID getID(){
        return this.id;
    }

    public ArrayList<ID> getIdsParams(){
        return this.idsParams;
    }

    public ArrayList<Type> getTypeParams(){
        return this.typeParams;
    }

    public ArrayList<Type> getTypeReturns(){
        return this.typeReturn;
    }

    public ArrayList<Cmd> getCmds(){
        return this.cmds;
    }
    
    //@Override
    public String toString(){
        String saida = id.toString();
        saida += " (";
        if(typeParams.size()>0){
            saida+=idsParams.get(0).toString() + "::" + typeParams.get(0).toString();
        }
        for(int i=1;i<typeParams.size();i++){
            saida+=", " + idsParams.get(i).toString() + "::" + typeParams.get(i).toString();
        }
        saida+=')';
        if(typeReturn.size()>0){
            saida+=":" + typeReturn.get(0).toString();
        }
        for(int i=1;i<typeReturn.size();i++){
            saida+=", " + typeReturn.get(i).toString();
        }
        saida+="{\n";
        for(int i=0;i<this.cmds.size();i++){
            saida+=cmds.get(i).toString();
        }
        saida+="}\n";
        return saida;
    }
    
    public void accept(Visitor v){v.visit(this);}
}
