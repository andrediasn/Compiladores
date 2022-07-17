/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import lang.ast.*;
import lang.visitor.types.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class VisitorCompiler extends Visitor {
    private VisitorStatic v;
    private String saida;
    private int numChaves;
    private HashMap<String, Stype> tipo;

    public VisitorCompiler(VisitorStatic v) {
        this.v=v;
        saida="";
        numChaves=0;
    }

    private void addTab(){
        for(int i=0;i<numChaves;i++){
            saida+="\t";
        }
    }

    public void saveFile(String filename) throws IOException {
        FileWriter arq = new FileWriter(filename);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.print(saida);
        arq.close();
    }

    public void visit(Prog p) {
        saida+="import java.util.ArrayList;\nimport java.util.Scanner;\n\n";
        saida+="public class Main{\n";
        numChaves=numChaves+1;
        for(Data d:p.getDatas()){
            d.accept(this);
            saida+="\n";
        }
        
        for(Func f:p.getFuncs()){
            f.accept(this);
            saida+="\n";
        }
        
        numChaves=numChaves-1;
        saida+="}\n";
    }

    public void visit(Func func) {
        addTab();
        ArrayList<Stype> params = new ArrayList<Stype>(); 
        if(func.getID().getID().equals("main")){
            saida+="public static void main(String[] args)";
        }else{
            saida+="public static ArrayList<Object> ";
            func.getID().accept(this);
            saida+="(";
            if(!func.getTypeParams().isEmpty()){
                func.getTypeParams().get(0).accept(this);
                saida+=" ";
                func.getIdsParams().get(0).accept(this);
            }
            for(int i=1;i<func.getTypeParams().size();i++){
                saida+=", ";
                func.getTypeParams().get(i).accept(this);
                saida+=" ";
                func.getIdsParams().get(i).accept(this);
            }
            for(Type type: func.getTypeParams()){
                Stype s=null;
                if(type.getType()!=null){
                    String tipo = type.getType();
                    if(tipo.equals("Bool")){
                        s=StyBool.newStyBool();
                    }else if(tipo.equals("Char")){
                        s=StyChar.newStyChar();
                    }else if(tipo.equals("Int")){
                        s=StyInt.newStyInt();
                    }else if(tipo.equals("Float")){
                        s=StyFloat.newStyFloat();
                    }
                    int j = type.getNumColchetes();
                    //se for vetor
                    while(j>0){
                        s=new StyArr(s);
                        j=j-1;
                    }
                }else{
                    //caso seja um tipo data
                    HashMap<String,StyData> dates = v.getDates();
                    s = dates.get(type.getIDType().getID());
                    int j = type.getNumColchetes();
                    //se for vetor
                    while(j>0){
                        s=new StyArr(s);
                        j=j-1;
                    }
                }
                params.add(s);
            }
            saida+=")";
        }
        saida+="{\n";
        numChaves++;
        HashMap <String, Stype> vars = v.getFuncs().get(new Funcao(func.getID().getID(), params)).getVars();
        this.tipo = vars;
        HashMap<String, Stype> temp = new HashMap<String, Stype>();
        for(ID var:func.getIdsParams()){
            temp.put(var.getID(),vars.remove(var.getID()));
        }
        for(String var:vars.keySet()){
            addTab();
            Stype aux = vars.get(var);
            saida+=aux.toString();
            saida+=" ";
            saida+=var+" = ";
            if(aux.match(StyInt.newStyInt())){
                saida+="0";
            }else if(aux.match(StyFloat.newStyFloat())){
                saida+="0.0";
            }else if(aux.match(StyBool.newStyBool())){
                saida+="false";
            }else if(aux.match(StyChar.newStyChar())){
                saida+="''";
            }else{
                saida+="null";
            }
            saida+=";\n";
        }
        for(String k : temp.keySet()){
            vars.put(k, temp.get(k));
        }
        for(Cmd c : func.getCmds()){
            addTab();
            c.accept(this);
        }
        if(!func.getID().getID().equals("main") && func.getTypeReturns().isEmpty()){
            String var = "$retl"+func.getLine()+"c"+func.getColumn(); 
            addTab();
            saida+="ArrayList<Object> " + var + " = new ArrayList<Object>();\n";
            addTab();
            saida+="return "+var+";\n";
        }
        numChaves--;
        addTab();
        saida+="}\n";
    }

    public void visit(Aexp aexp) {
        aexp.getMexps().get(0).accept(this);
        for(int i=0;i<aexp.getSignals().size();i++){
            saida+=aexp.getSignals().get(i);
            aexp.getMexps().get(i+1).accept(this);
        }
    }

    public void visit(Attr attr) {
        attr.getLvalue().accept(this);
        saida+=" = ";
        attr.getExp().accept(this);
        saida+=";\n";
    }
    
    public void visit(Data data) {
        addTab();
        saida+="public static class ";
        data.getId().accept(this);
        numChaves++;
        saida+="{\n"; 
        for(int i=0;i<data.getDecl().size();i++){
            addTab();
            data.getDecl().get(i).accept(this);
        }
        numChaves--;
        addTab();
        saida+="}\n";
    }

    public void visit(Decl decl) {
        saida+="public ";
        decl.getType().accept(this);
        saida+=" ";
        decl.getId().accept(this);
        saida+=";\n";
    }

    public void visit(Exp exp) {
        exp.getRexps().get(0).accept(this);
        for(int i=1;i<exp.getRexps().size();i++){
            saida+=" && ";
            exp.getRexps().get(i).accept(this);
        }
    }

    public void visit(FunctionCall functioncall) {
        String var = "$fcl"+functioncall.getLine() + "c" +functioncall.getColumn();
        saida+="ArrayList<Object> " + var + " = ";
        functioncall.getId().accept(this);
        saida+=" (";
        if(!functioncall.getExps().isEmpty()){
            functioncall.getExps().get(0).accept(this);
        }
        for(int i=1;i<functioncall.getExps().size();i++){
            saida+=", ";
            functioncall.getExps().get(i).accept(this);
        }
        saida+=");\n";
        for(int i=0;i<functioncall.getLvalues().size();i++){
            addTab();
            functioncall.getLvalues().get(i).accept(this);
            LvalueUnic l = (LvalueUnic)functioncall.getLvalues().get(i);
            Stype t = tipo.get(l.getId().getID());
            for(Lvalue lv : l.getLvalues()){
                if(t instanceof StyArr){
                    t=((StyArr)t).getArg();
                }else if(t instanceof StyData){
                    t=((StyData)t).getCampos().get(((LvalueSelect)lv).getID().getID());
                }
            }
            saida+= " = (" + t.toString() + ")" + var + ".get(" + i + ");\n";
        }
    }

    public void visit(ID id) {
        saida+=id.getID();
    }

    public void visit(If i) {
        saida+="if(";
        i.getCond().accept(this);
        saida+="){\n";
        numChaves++;
        for(Cmd c : i.getThen()){
            addTab();
            c.accept(this);
        }
        numChaves--;
        addTab();
        saida+="}";
        if(i.getElse().isEmpty()){
            saida+="\n";
        }else{
            saida+="else{\n";
            numChaves++;
            for(Cmd c : i.getElse()){
                addTab();
                c.accept(this);
            }
            numChaves--;
            addTab();
            saida+="}\n";
        }
    }
    
    public void visit(Iterate iterate) {
        String var1="$iStopc"+iterate.getLine()+"c"+iterate.getColumn();
        saida+="int " +var1 + " = ";
        iterate.getCond().accept(this);
        saida+=";\n";
        addTab();
        String var="$il"+iterate.getLine()+"c"+iterate.getColumn();
        saida+="for(int ";
        saida+= var;
        saida+="=0;";
        saida+= var;
        saida+="<";
        saida+=var1;
        saida+=";";
        saida+=var;
        saida+="++){\n";
        numChaves++;
        for(Cmd c : iterate.getLoop()){
            addTab();
            c.accept(this);
        }
        numChaves--;
        addTab();
        saida+="}\n";
    }

    public void visit(LvalueArray lvaluearray) {
        saida+="[";
        lvaluearray.getExp().accept(this);
        saida+="]";
    }

    public void visit(LvalueSelect lvalueselect) {
        saida+= ".";
        lvalueselect.getID().accept(this);
    }
    
    public void visit(LvalueUnic lvalueunic) {
        lvalueunic.getId().accept(this);
        for(int i=0;i<lvalueunic.getLvalues().size();i++){
            lvalueunic.getLvalues().get(i).accept(this);
        }
    }

    public void visit(Mexp mexp) {
        mexp.getSexps().get(0).accept(this);
        for(int i=0;i<mexp.getSignals().size();i++){
            saida+=mexp.getSignals().get(i);
            mexp.getSexps().get(i+1).accept(this);
        }
    }

    public void visit(PexpFunction pexpfunction) {
        int pos = Integer.parseInt(pexpfunction.getExp().toString());
        ArrayList<Stype> params = new ArrayList<Stype>();
        for(Exp e : pexpfunction.getExps()){
            params.add(v.getType(e,tipo));
        }
        Stype tipo = v.getFuncs().get(new Funcao(pexpfunction.getId().getID(),params)).getRet().get(pos);
        saida+="("+ tipo.toString() +")";
        pexpfunction.getId().accept(this);
        saida+="(";
        if(!pexpfunction.getExps().isEmpty()){
            pexpfunction.getExps().get(pexpfunction.getExps().size()-1).accept(this);
        }
        for(int i=pexpfunction.getExps().size()-2;i>=0;i--){
            saida+=", ";
            pexpfunction.getExps().get(i).accept(this);
        }
        saida+=").get(";
        pexpfunction.getExp().accept(this);
        saida+=")";
    }
    
    public void visit(PexpLvalue pexplvalue) {
        pexplvalue.getLvalue().accept(this);
    }
    
    
    public void visit(PexpNew pexpnew) {
        saida+="new ";
        Type type = pexpnew.getType();
        if(type.getType()!=null){
            if(type.getType().equals("Int")){
                saida+="int";
            }else if(type.getType().equals("Float")){
                saida+="float";
            }else if(type.getType().equals("Char")){
                saida+="char";
            }else if(type.getType().equals("Bool")){
                saida+="boolean";
            }
        }else{
            type.getIDType().accept(this);
            if(pexpnew.getExp()==null){
                saida+="()";
            }
        }
        if(pexpnew.getExp()!=null){
            saida+="[";
            pexpnew.getExp().accept(this);
            saida+="]";
            for(int i=0;i<type.getNumColchetes();i++){
                saida+="[]";
            }
        }
    }
    
    public void visit(PexpParenteses pexpparenteses) {
        saida+="(";
        pexpparenteses.getExp().accept(this);
        saida+=")";
    }
    
    public void visit(Print print) {
        saida+="System.out.print(";
        print.getExp().accept(this);
        saida+=");\n"; 
    }

    public void visit(Read read) {
        read.getLvalue().accept(this);
        saida+=" = Integer.parseInt((new Scanner(System.in)).next());\n";
    }

    public void visit(Return ret) {
        String var = "$retl"+ret.getLine()+"c"+ret.getColumn(); 
        saida+="ArrayList<Object> " + var + " = new ArrayList<Object>();\n";
        for(Exp e : ret.getExps()){
            addTab();
            saida+=var+".add(";
            e.accept(this);
            saida+=");\n";
        }
        addTab();
        saida+="return "+var+";\n";
    }

    public void visit(Rexp rexp) {
        rexp.getAexpLess1().accept(this);
        if(rexp.getAexpLess2()!=null){
            saida+=" < ";
            rexp.getAexpLess2().accept(this);
        }
        for(int i=0;i<rexp.getSignal().size();i++){
            saida+=" " + rexp.getSignal().get(i)+ " ";
            rexp.getAexp().get(i).accept(this);
        }
    }

    public void visit(Sexp sexp) {
        if(sexp.getOperator()!=null){
            saida+=sexp.getOperator();
        }
        sexp.getSexpValue().accept(this);
    }

    public void visit(SexpValueBool sexpvaluebool) {
        saida+=sexpvaluebool.toString();
    }

    public void visit(SexpValueChar sexpvaluechar) {
        saida+=sexpvaluechar.toString();
    }

    public void visit(SexpValueFloat sexpvaluefloat) {
        saida+=sexpvaluefloat.toString();
    }

    public void visit(SexpValueInt sexpvalueint) {
        saida+=sexpvalueint.toString();
    }

    public void visit(SexpValueNull sexpvaluenull) {
        saida+=sexpvaluenull.toString();
    }

    public void visit(SexpValuePexp sexpvaluepexp) {
        sexpvaluepexp.getValue().accept(this);
    }

    public void visit(Type type) {
        if(type.getType()!=null){
            if(type.getType().equals("Int")){
                saida+="int";
            }else if(type.getType().equals("Float")){
                saida+="double";
            }else if(type.getType().equals("Char")){
                saida+="char";
            }else if(type.getType().equals("Bool")){
                saida+="boolean";
            }
        }else{
            type.getIDType().accept(this);
        }
        for(int i=0;i<type.getNumColchetes();i++){
            saida+="[]";
        }
    }
}