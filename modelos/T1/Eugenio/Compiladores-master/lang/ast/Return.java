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

public class Return extends Cmd{
    private ArrayList<Exp> exps;

    public Return (int line, int colunm){
        super(line,colunm);
        this.exps=new ArrayList<Exp>();
    }

    public void addReturn(Exp exp){
        this.exps.add(0,exp);
    }

    public ArrayList<Exp> getExps(){
        return this.exps;
    }
    
    //@Override
    public String toString(){
        String saida = "return ";
        saida+= exps.get(0).toString();
        for(int i=1;i<exps.size();i++){
            saida+= ", " + exps.get(i).toString();
        }
        saida+=";\n";
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
