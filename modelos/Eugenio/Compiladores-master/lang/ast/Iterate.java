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

public class Iterate extends Cmd{
    private Exp cond;
    private ArrayList<Cmd> loop;

    public Iterate (int line, int colunm, Exp cond){
        super(line,colunm);
        this.cond = cond;
        this.loop = new ArrayList<Cmd>(); 
    }

    public void addCmdLoop(Cmd loop){
        this.loop.add(0,loop);
    }

    public Exp getCond(){
        return this.cond;
    }

    public ArrayList<Cmd> getLoop(){
        return this.loop;
    }

    //@Override
    public String toString(){
        String saida = "iterate (" + cond.toString() + ") {\n";
        for(int i=0;i<loop.size();i++){
            saida += loop.get(i).toString();
        }
        saida+="}\n";
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
