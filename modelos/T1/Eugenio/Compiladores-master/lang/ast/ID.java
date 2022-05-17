/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class ID extends Node {
    private String id;
    public ID (int line, int colunm, String id){
        super(line,colunm);
        this.id=id;
    }

    public String getID(){
        return this.id;
    }
    
    //@Override
    public String toString(){
        return id; 
    }

    public void accept(Visitor v){v.visit(this);}
}
