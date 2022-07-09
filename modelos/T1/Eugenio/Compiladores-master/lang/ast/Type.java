/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class Type extends Node {
    private String type;
    private int numColchetes;
    private ID typeID;
    
    public Type (int line, int colunm, String type){
        super(line,colunm);
        numColchetes=0;
        this.type=type;
        this.typeID=null;
    }

    public Type (int line, int colunm, ID type){
        super(line,colunm);
        numColchetes=0;
        this.type=null;
        this.typeID=type;
    }

    public void addColchete(){
        this.numColchetes=this.numColchetes+1;
    }
    
    public String getType(){
        return this.type;
    }

    public ID getIDType(){
        return this.typeID;
    }

    public int getNumColchetes(){
        return this.numColchetes;
    }

    //@Override
    public String toString(){
        String saida="";
        if(type!=null){
            saida+=type;
        }else if(typeID!=null){
            saida+=typeID.toString();
        }
        for(int i=0;i<this.numColchetes;i++){
            saida+="[]";
        }
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
