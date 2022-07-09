/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.ast;

import lang.visitor.Visitor;

public class SexpValueNull extends SexpValue {
    
    public SexpValueNull (int line, int colunm){
        super(line,colunm);
    }

    //@Override
    public String toString(){
        return "null";
    }

    public void accept(Visitor v){v.visit(this);}
}
