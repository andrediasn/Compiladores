package ast;

import java.util.HashMap; 
import visitors.Visitor;

public class Program extends Node {
      
    private Func[] fs;
    private Call f;
      
    public Program(Func[] fs, Call f){
	this.fs = fs;
	this.f = f;
    }
      
    public Func[] getFuncs(){ return fs; }

    public Call getMainFunc() {return f;}
      
    public String toString(){
	String s = "";
	for(Func f : fs){
	    s += f.toString();
	}
	return s + "\n\n" + f.toString();
    }
      
    public void accept(Visitor v){ v.visit(this);}

}
