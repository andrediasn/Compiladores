/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class Decl extends Node {
    
    private ID id;
    private Type type;
    
    public Decl (int line, int colunm, ID id, Type t){
        super(line,colunm);
        this.id = id;
        this.type=t;
    }

    public ID getId(){
        return this.id;
    }

    public Type getType(){
        return this.type;
    }

    //@Override
    public String toString(){
        return id.toString() + "::" + type.toString() + ";\n";
    }
    
    public void accept(Visitor v){v.visit(this);}
}
