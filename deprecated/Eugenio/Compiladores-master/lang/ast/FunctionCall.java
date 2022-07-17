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

public class FunctionCall extends Cmd{
    private ID id;
    private ArrayList<Exp> exps;
    private ArrayList<Lvalue> lvalues;
    
    public FunctionCall (int line, int colunm, ID id){
        super(line,colunm);
        this.id=id;
        this.exps=new ArrayList<Exp>();
        this.lvalues=new ArrayList<Lvalue>();
    }

    public void addExp(Exp exp){
        this.exps.add(0,exp);
    }

    public void addLvalue(Lvalue lvalue){
        this.lvalues.add(0,lvalue);
    }
    
    public ID getId(){
        return this.id;
    }

    public ArrayList<Exp> getExps(){
        return this.exps;
    }
    
    public ArrayList<Lvalue> getLvalues(){
        return this.lvalues;
    }

    //@Override
    public String toString(){
        String saida= id.toString() + "(";
        if(!exps.isEmpty()){
            saida+= exps.get(0).toString();
        }
        for(int i=1;i<exps.size();i++){
            saida+= ", " + exps.get(i).toString();
        }
        saida+=")";
        if(!lvalues.isEmpty()){
            saida+="< " + lvalues.get(0).toString();
        }
        for(int i=1;i<lvalues.size();i++){
            saida+= ", " + lvalues.get(i).toString();
        }
        if(!lvalues.isEmpty()){
            saida+=" > ";
        }
        saida+=";\n";
        return saida;
    }

    public void accept(Visitor v){v.visit(this);}
}
