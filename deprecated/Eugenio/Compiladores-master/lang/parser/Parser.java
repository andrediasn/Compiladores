/*
Autores:

Nome: André Felipe de Souza Mota
Matrícula: 20166515B

Nome: Eugenio Belizário Ribeiro Faria
Matrícula: 201665507B
*/

package lang.parser;

import java.util.Stack;

import java.util.LinkedList;
import java.io.IOException;
import java.io.FileReader;
import lang.*;
import lang.ast.*;
import lang.lexical.*;

public class Parser implements ParseAdaptor{
    private Lexical lx;
    private Stack<Token> stk;
    private Stack<SuperNode> ast;
    private LinkedList<Token> bck;
    private Token nxt;
    private boolean eof;
    boolean erro;

    public SuperNode parseFile(String fileName) throws IOException {
        lx = new Lexical(new FileReader(fileName));
        stk = new Stack<Token>();
        ast = new Stack<SuperNode>();
        
        //modificar esta variavel para true para printar a linha/coluna onde o erro ocorreu
        erro=false;
        bck = new LinkedList<Token>();
        nxt = lx.nextToken();
        eof = nxt == null;
        return pertence();
    }

    private void readToken() throws IOException {
        if (!eof) {
            stk.push(nxt);
            if (bck.isEmpty()) {
                nxt = lx.nextToken();
                eof = nxt == null;
            } else {
                nxt = bck.remove();
            }
        }
    }

    private void backtrack(int n, int k) throws IOException {
        if (n > 0 && nxt == null) {
            nxt = stk.pop();
            n--;
            eof = false;
        }
        while (n > 0) {
            bck.addFirst(nxt);
            nxt = stk.pop();
            n--;
        }
        if (!bck.isEmpty()) {
            eof = false;
        }
        while (k > 0) {
            ast.pop();
            k--;
        }
    }

    
    private int getCurrLine() {
        return nxt == null ? -1 : nxt.getLine();
    }

    private int getCurrCol() {
        return nxt == null ? -1 : nxt.getColumn();
    }
    
    private void showError(){
        if(erro){
            String saida = "Erro encontrado na linha " + getCurrLine() + " e na coluna " + getCurrCol();
            if(nxt!=null && nxt.getLexeme()!=null){
                saida += " na seguinte entrada: " + (String)nxt.getLexeme(); 
            }
            System.out.println();
            System.out.println(saida);
        }
    }
    
    private boolean match(TOKEN_TYPE t) throws IOException {
        if (nxt != null && nxt.getToken() == t) {
            readToken();
            return true;
        }
        return false;
    }

    private SuperNode pertence() throws IOException {
        Prog list = null;
        if (prog() && eof){
            list = (Prog)ast.peek();
        }
        if(nxt!=null){
            showError();
        }
        return list;
    }

    private boolean prog() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //PROG -> DATA’ FUNC’
        Prog p = new Prog(getCurrLine(), getCurrCol());
        if (data1()){
            while(!ast.empty() && ast.peek() instanceof Data && ast.size()>k){
                p.addData((Data)ast.pop());
            }
            if(func1()){
                while(!ast.empty() && ast.peek() instanceof Func && ast.size()>k){
                    p.addFunc((Func)ast.pop());
                }
                ast.push(p);
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean data1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //DATA’ -> DATA DATA’
        if(data()){
            if(data1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //DATA’ -> ϵ
        return true;
    }

    private boolean func1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //FUNC’ -> FUNC FUNC’
        if(func()){
            if(func1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //FUNC’ -> ϵ
        return true;
    }

    private boolean data() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //DATA -> data ID { DECL’ }
        if(match(TOKEN_TYPE.DATA)){
            Object aux = nxt.getLexeme();
            int l1=getCurrLine();
            int l2 = getCurrCol();
            if(match(TOKEN_TYPE.ID) && Character.isUpperCase( ((String)aux).charAt(0) ) ){
                ID id=new ID(l1,l2,(String)aux);
                if(match(TOKEN_TYPE.LBRACE)){
                    if(decl1()){
                        if(match(TOKEN_TYPE.RBRACE)){
                            Data data = new Data(l, c, id);
                            while(!ast.empty() && ast.peek() instanceof Decl && ast.size()>k){
                                data.addDecl((Decl)ast.pop());
                            }
                            ast.add(data);
                            return true;
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean decl1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //DECL’ -> DECL DECL’
        if(decl()){
            if( decl1() ){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //DECL’ -> ϵ
        return true;
    }

    private boolean decl() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //DECL -> ID :: TYPE ;
        Token aux = nxt;
        int l = getCurrLine();
        int c = getCurrCol();
        if(match(TOKEN_TYPE.ID)){
            ID id = new ID(l, c, (String)aux.getLexeme());
            if(match( TOKEN_TYPE.DCOLON )){
                if(type()){
                    if(match(TOKEN_TYPE.SEMICOLON)){
                        ast.add(new Decl(l,c, id, (Type)ast.pop()));
                        return true;
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean func() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //FUNC -> ID ( PARAMS’ ) TYPES { CMDS }
        Token aux = nxt;
        if(match(TOKEN_TYPE.ID)){
            ID id = new ID(l, c, (String)aux.getLexeme());
            if(match(TOKEN_TYPE.LPARENT)){
                if( params1() ){
                    if( match(TOKEN_TYPE.RPARENT) ){
                        if(types()){
                            if(match(TOKEN_TYPE.LBRACE)){
                                if(cmds()){
                                    if(match(TOKEN_TYPE.RBRACE)){
                                        Func f = new Func(l, c, id);
                                        while(!ast.empty() && ast.peek() instanceof Cmd && ast.size()>k){
                                            f.addCmd((Cmd)ast.pop());
                                        }
                                        while(!ast.empty() && ast.peek() instanceof Type && ast.size()>k){
                                            f.addReturn((Type)ast.pop());
                                        }
                                        while(!ast.empty() && ast.peek() instanceof ID && ast.size()>k){
                                            f.addParam((ID)ast.pop(), (Type)ast.pop());
                                        }
                                        ast.add(f);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean params1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //PARAMS’ -> PARAMS
        if(params()){
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //PARAMS’ -> ϵ
        return true;
    }

    private boolean types() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //TYPES -> : TYPE TYPE’
        if(match(TOKEN_TYPE.COLON)){
            if(type()){
                if(type1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //TYPES -> ϵ
        return true;
    }

    private boolean type1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //TYPE’ -> , TYPE TYPE’
        if(match(TOKEN_TYPE.COMMA)){
            if(type()){
                if(type1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //TYPE’ -> ϵ
        return true;
    }

    private boolean cmds() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //CMDS -> CMD CDMS
        if(cmd()){
            if(cmds()){
                return true;
            }
        }

        //CMDS -> ϵ
        backtrack(stk.size()-n, ast.size()-k);
        return true;
    }

    private boolean params() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //PARAMS -> ID :: TYPE IDS
        Token aux = nxt;
        if(match(TOKEN_TYPE.ID)){
            ID id = new ID(l, c, (String)aux.getLexeme());
            if(match(TOKEN_TYPE.DCOLON)){
                if(type()){
                    ast.add(id);
                    if(ids()){
                        return true;
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean ids() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //IDS -> , ID :: TYPE IDS
        if(match(TOKEN_TYPE.COMMA)){
            Token aux = nxt;
            int l = getCurrLine();
            int c = getCurrCol();
            if(match(TOKEN_TYPE.ID)){
                ID id = new ID(l, c, (String)aux.getLexeme());
                if(match(TOKEN_TYPE.DCOLON)){
                    if(type()){
                        ast.add(id);
                        if(ids()){
                            return true;
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //IDS -> ϵ
        return true;
    }

    private boolean type() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //TYPE -> BTYPE BRACES
        if(btype()){
            if(braces()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean braces() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //BRACES -> [ ] BRACES
        if(match(TOKEN_TYPE.LBRACKET)){
            if(match(TOKEN_TYPE.RBRACKET)){
                ((Type)ast.peek()).addColchete();
                if(braces()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //BRACES -> ϵ
        return true;
    }

    private boolean btype() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //BTYPE -> Int
        if(match(TOKEN_TYPE.INT)){
            ast.add(new Type(l, c, "Int"));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //BTYPE -> Char
        if(match(TOKEN_TYPE.CHAR)){
            ast.add(new Type(l, c, "Char"));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //BTYPE -> Bool
        if(match(TOKEN_TYPE.BOOL)){
            ast.add(new Type(l, c, "Bool"));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //BTYPE -> Float
        if(match(TOKEN_TYPE.FLOAT)){
            ast.add(new Type(l, c, "Float"));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //BTYPE -> ID
        Object aux = nxt.getLexeme();
        if(match(TOKEN_TYPE.ID) && Character.isUpperCase( ((String)aux).charAt(0) )){
            ast.add(new Type(l, c, new ID(l, c, (String)aux)));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean cmd() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //CMD -> { CMDS } 
        if(match(TOKEN_TYPE.LBRACE)){
            if(cmds()){
                if(match(TOKEN_TYPE.RBRACE)){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        int l = getCurrLine();
        int c = getCurrCol();
        //CMD -> if ( EXP ) CMD else CMD
        if(match(TOKEN_TYPE.IF)){
            if(match(TOKEN_TYPE.LPARENT)){
                if(exp()){
                    If i = new If(l, c, (Exp)ast.pop());
                    if(match(TOKEN_TYPE.RPARENT)){
                        if(cmd()){
                            while(!ast.empty() && ast.peek() instanceof Cmd && ast.size()>k){
                                i.addThen((Cmd)ast.pop());
                            }
                            if(match(TOKEN_TYPE.ELSE)){
                                if(cmd()){
                                    while(!ast.empty() && ast.peek() instanceof Cmd && ast.size()>k){
                                        i.addEls((Cmd)ast.pop());
                                    }
                                    ast.add(i);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        
        //CMD -> if ( EXP ) CMD
        if(match(TOKEN_TYPE.IF)){
            if(match(TOKEN_TYPE.LPARENT)){
                if(exp()){
                    If i = new If(l, c, (Exp)ast.pop());
                    if(match(TOKEN_TYPE.RPARENT)){
                        if(cmd()){
                            while(!ast.empty() && ast.peek() instanceof Cmd && ast.size()>k){
                                i.addThen((Cmd)ast.pop());
                            }
                            ast.add(i);
                            return true;
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD ->  iterate ( EXP ) CMD
        if(match(TOKEN_TYPE.ITERATE)){
            if(match(TOKEN_TYPE.LPARENT)){
                if(exp()){
                    Iterate i = new Iterate(l, c, (Exp)ast.pop());
                    if(match(TOKEN_TYPE.RPARENT)){
                        if(cmd()){
                            while(!ast.empty() && ast.peek() instanceof Cmd && ast.size()>k){
                                i.addCmdLoop((Cmd)ast.pop());
                            }
                            ast.add(i);
                            return true;
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD -> read LVALUE ; 
        if(match(TOKEN_TYPE.READ)){
            if(lvalue()){
                if(match(TOKEN_TYPE.SEMICOLON)){
                    ast.add( new Read(l, c, (Lvalue)ast.pop()) );
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD -> print EXP ;
        if(match(TOKEN_TYPE.PRINT)){
            if(exp()){
                if(match(TOKEN_TYPE.SEMICOLON)){
                    ast.add( new Print(l, c, (Exp)ast.pop()) );
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD -> return EXPS ;
        if(match(TOKEN_TYPE.RETURN)){
            if(exps()){
                if(match(TOKEN_TYPE.SEMICOLON)){
                    Return r = new Return(l, c);
                    while(!ast.empty() && ast.peek() instanceof Exp && ast.size()>k){
                        r.addReturn((Exp) ast.pop());
                    }
                    ast.add(r);
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD -> LVALUE = EXP ;
        if(lvalue()){
            if(match(TOKEN_TYPE.ASSIGN)){
                if(exp()){
                    Exp e = (Exp)ast.pop();
                    if(match(TOKEN_TYPE.SEMICOLON)){
                        ast.add( new Attr(l, c, (LvalueUnic)ast.pop(), e) );
                        return true;
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD -> ID ( EXPS )  < LVALUES >  ;
        Token aux = nxt;
        if(match(TOKEN_TYPE.ID)){
            
            if(match(TOKEN_TYPE.LPARENT)){

                if(exps()){
                    if(match(TOKEN_TYPE.RPARENT)){
                        if(match(TOKEN_TYPE.LESS)){
                            if(lvalues()){
                                if(match(TOKEN_TYPE.GREATER)){
                                    if(match(TOKEN_TYPE.SEMICOLON)){
                                        FunctionCall fc = new FunctionCall(l, c, new ID(l, c, (String)aux.getLexeme() ));
                                        while(!ast.empty() && ast.peek() instanceof Lvalue && ast.size()>k){
                                            fc.addLvalue((Lvalue)ast.pop());
                                        }
                                        while(!ast.empty() && ast.peek() instanceof Exp && ast.size()>k){
                                            fc.addExp((Exp)ast.pop());
                                        }
                                        ast.add(fc);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD ->  ID ( EXPS ) ;
        aux=nxt;
        if(match(TOKEN_TYPE.ID)){
            if(match(TOKEN_TYPE.LPARENT)){
                if(exps()){
                    if(match(TOKEN_TYPE.RPARENT)){
                        if(match(TOKEN_TYPE.SEMICOLON)){
                            FunctionCall fc = new FunctionCall(l, c, new ID(l,c,(String)aux.getLexeme()));
                            while(!ast.empty() && ast.peek() instanceof Exp && ast.size()>k){
                                fc.addExp((Exp)ast.pop());
                            }
                            ast.add(fc);
                            return true;
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD -> ID ( )  < LVALUES >  ;
        aux=nxt;
        if(match(TOKEN_TYPE.ID)){
            if(match(TOKEN_TYPE.LPARENT)){
                if(match(TOKEN_TYPE.RPARENT)){
                    if(match(TOKEN_TYPE.LESS)){
                        if(lvalues()){
                            if(match(TOKEN_TYPE.GREATER)){
                                if(match(TOKEN_TYPE.SEMICOLON)){
                                    FunctionCall fc = new FunctionCall(l, c, new ID(l, c, (String)aux.getLexeme()));
                                    while(!ast.empty() && ast.peek() instanceof Lvalue && ast.size()>k){
                                        fc.addLvalue((Lvalue)ast.pop());
                                    }
                                    ast.add(fc);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //CMD -> ID ( ) ;
        aux=nxt;
        if(match(TOKEN_TYPE.ID)){
            if(match(TOKEN_TYPE.LPARENT)){
                if(match(TOKEN_TYPE.RPARENT)){
                    if(match(TOKEN_TYPE.SEMICOLON)){
                        FunctionCall fc = new FunctionCall(l, c, new ID(l, c, (String)aux.getLexeme() ));
                        ast.add(fc);
                        return true;
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean exps() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //EXPS ->  EXP EXP’
        if(exp()){            
            if(exp1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean exp1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //EXP’ -> , EXP EXP’ | ϵ
        if(match(TOKEN_TYPE.COMMA)){
            if(exp()){
                if(exp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //EXP’ -> ϵ
        return true;
    }

    private boolean lvalues() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //LVALUES -> LVALUE LVALUES’
        if(lvalue()){
            if(lvalues1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean lvalues1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //LVALUES’ -> , LVALUE LVALUES’
        if(match(TOKEN_TYPE.COMMA)){
            if(lvalue()){
                if(lvalues1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //LVALUES’ ->  ϵ
        return true;
    }

    private boolean exp() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //EXP -> REXP && EXP
        int l = getCurrLine();
        int c = getCurrCol();
        if(rexp()){
            if(match(TOKEN_TYPE.AND)){
                if(exp()){
                    Exp e;
                    if(!ast.empty() && ast.peek() instanceof Exp && ast.size()>k){
                        e=(Exp)ast.pop();
                    }else{
                        e = new Exp(l, c);
                    }
                    Rexp r=(Rexp)ast.pop();
                    e.addRexp(r);
                    ast.add(e);
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //exp -> REXP
        if(rexp()){
            Rexp r=(Rexp)ast.pop();
            Exp e = new Exp(l, c);
            e.addRexp(r);
            ast.add(e);
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean rexp() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //REXP -> AEXP < AEXP REXP'
        if(aexp()){
            Aexp aexp1 = (Aexp) ast.pop();
            if(match(TOKEN_TYPE.LESS)){
                if(aexp()){
                    Aexp aexp2 = (Aexp) ast.pop();
                    ast.add(new Rexp(l, c, aexp1, aexp2));
                    if(rexp1()){
                        return true;
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //REXP -> AEXP REXP'
        if(aexp()){
            Aexp aexp1 = (Aexp) ast.pop();
            ast.add(new Rexp(l, c, aexp1));
            if(rexp1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean rexp1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //REXP' -> == AEXP REXP'
        if(match(TOKEN_TYPE.EQ)){
            Rexp r = (Rexp)ast.pop(); 
            if(aexp()){
                r.addAexp("==", (Aexp)ast.pop());
                ast.add(r);
                if(rexp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //REXP' -> != AEXP REXP'
        if(match(TOKEN_TYPE.NEQ)){
            Rexp r = (Rexp)ast.pop(); 
            if(aexp()){
                r.addAexp("!=", (Aexp)ast.pop());
                ast.add(r);
                if(rexp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //REXP' -> ϵ
        return true;
    }

    private boolean aexp() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //AEXP -> MEXP AEXP'
        if(mexp()){
            ast.add(new Aexp(l,c,(Mexp)ast.pop()));
            if(aexp1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean aexp1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //AEXP' -> + MEXP AEXP'
        if(match(TOKEN_TYPE.PLUS)){
            Aexp a = (Aexp)ast.pop();
            if(mexp()){
                a.addMexp("+", (Mexp)ast.pop() );
                ast.add(a);
                if(aexp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //AEXP' -> - MEXP AEXP'
        if(match(TOKEN_TYPE.MINUS)){
            Aexp a = (Aexp)ast.pop();
            if(mexp()){
                a.addMexp("-", (Mexp)ast.pop());
                ast.add(a);
                if(aexp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //AEXP' -> ϵ
        return true;
    }

    private boolean mexp() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //MEXP -> SEXP MEXP'
        if(sexp()){
            ast.add( new Mexp(l, c, (SexpValue)ast.pop()));
            if(mexp1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean mexp1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        //MEXP' -> * SEXP MEXP'
        if(match(TOKEN_TYPE.MULT)){
            Mexp m = (Mexp)ast.pop();
            if(sexp()){
                m.addSexp("*", (SexpValue)ast.pop() );
                ast.add(m);
                if(mexp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //MEXP' -> / SEXP MEXP'
        if(match(TOKEN_TYPE.DIV)){
            Mexp m = (Mexp)ast.pop();
            if(sexp()){
                m.addSexp("/", (SexpValue)ast.pop() );
                ast.add(m);
                if(mexp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //MEXP' -> % SEXP MEXP'
        if(match(TOKEN_TYPE.MOD)){
            Mexp m = (Mexp)ast.pop();
            if(sexp()){
                m.addSexp("%",(SexpValue)ast.pop() );
                ast.add(m);
                if(mexp1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //MEXP' -> ϵ
        return true;
    }

    private boolean sexp() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //SEXP -> ! SEXP
        if(match(TOKEN_TYPE.NOT)){
            if(sexp()){
                ast.add(new Sexp(l, c, (SexpValue)ast.pop(), "!"));
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //SEXP ->  - SEXP
        if(match(TOKEN_TYPE.MINUS)){
            if(sexp()){
                ast.add(new Sexp(l, c, (SexpValue)ast.pop(), "-"));
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //SEXP ->  true
        if(match(TOKEN_TYPE.TRUE)){
            ast.add(new SexpValueBool(l, c, true));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //SEXP ->  false 
        if(match(TOKEN_TYPE.FALSE)){
            ast.add(new SexpValueBool(l, c, false));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //SEXP ->  null
        if(match(TOKEN_TYPE.NULL)){
            ast.add(new SexpValueNull(l, c));
            return true;    
        }
        backtrack(stk.size()-n, ast.size()-k);
        
        //SEXP ->  INT
        Token aux = nxt;
        if(match(TOKEN_TYPE.INTEGER)){
            ast.add(new SexpValueInt(l, c,(int)aux.getLexeme()));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //SEXP ->  FLOAT
        aux=nxt;
        if(match(TOKEN_TYPE.DOUBLE)){
            ast.add(new SexpValueFloat(l, c,(float)aux.getLexeme()));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //SEXP ->  CHAR
        aux=nxt;
        if(match(TOKEN_TYPE.CARACTER)){
            ast.add(new SexpValueChar(l, c, (String)aux.getLexeme()));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        //SEXP ->  PEXP
        if(pexp()){
            ast.add(new SexpValuePexp(l, c, (Pexp)ast.pop()));
            return true;
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean pexp() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //PEXP -> ( EXP )
        if(match(TOKEN_TYPE.LPARENT)){
            if(exp()){
                if(match(TOKEN_TYPE.RPARENT)){
                    ast.add(new PexpParenteses(l, c, (Exp) ast.pop()));
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //PEXP -> new TYPE [ EXP ]
        if(match(TOKEN_TYPE.NEW)){
            if(type()){
                if(match(TOKEN_TYPE.LBRACKET)){
                    if(exp()){
                        if(match(TOKEN_TYPE.RBRACKET)){
                            Exp e = (Exp)ast.pop();
                            Type t = (Type)ast.pop();
                            ast.add(new PexpNew(l, c, t, e));
                            return true;
                        }
                    }
                }
            }
        }
    
        backtrack(stk.size()-n, ast.size()-k);
        //PEXP -> new TYPE
        if(match(TOKEN_TYPE.NEW)){
            if(type()){
                ast.add(new PexpNew(l, c, (Type)ast.pop()));
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //PEXP -> ID ( EXPS ) [ EXP ]
        Token aux = nxt;
        if(match(TOKEN_TYPE.ID)){
            ID id = new ID(l, c, (String)aux.getLexeme());
            if(match(TOKEN_TYPE.LPARENT)){
                if(exps()){
                    if(match(TOKEN_TYPE.RPARENT)){
                        if(match(TOKEN_TYPE.LBRACKET)){
                            if(exp()){
                                Exp exp = (Exp)ast.pop();
                                if(match(TOKEN_TYPE.RBRACKET)){
                                    PexpFunction p = new PexpFunction(l, c, id, exp);
                                    while(!ast.empty() && ast.peek() instanceof Exp && ast.size()>k){
                                        p.addExp((Exp)ast.pop());
                                    }
                                    ast.add(p);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //PEXP -> ID ( ) [ EXP ]
        aux = nxt;
        if(match(TOKEN_TYPE.ID)){
            ID id = new ID(l, c, (String)aux.getLexeme());
            if(match(TOKEN_TYPE.LPARENT)){
                if(match(TOKEN_TYPE.RPARENT)){
                    if(match(TOKEN_TYPE.LBRACKET)){
                        if(exp()){
                            if(match(TOKEN_TYPE.RBRACKET)){
                                ast.add(new PexpFunction(l, c, id, (Exp)ast.pop()));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //PEXP -> LVALUE
        if(lvalue()){
            try{
                ast.add(new PexpLvalue(l, c, (Lvalue)ast.pop()));
                return true;
            }catch(Exception e){
                return false;
            }
                
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean lvalue() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int l = getCurrLine();
        int c = getCurrCol();
        //LVALUE -> ID LVALUE'
        Token aux = nxt;
        if(match(TOKEN_TYPE.ID)){
            ast.add(new LvalueUnic(l, c, new ID(l, c, (String)aux.getLexeme()) ));
            if(lvalue1()){
                return true;
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        return false;
    }

    private boolean lvalue1() throws IOException {
        int n = stk.size();
        int k = ast.size();
        int li = getCurrLine();
        int c = getCurrCol();
        //LVALUE' -> [ EXP ] LVALUE'
        if(match(TOKEN_TYPE.LBRACKET)){
            LvalueUnic l = (LvalueUnic)ast.pop();
            if(exp()){
                LvalueArray la = new LvalueArray(li, c, (Exp)ast.pop());
                if(match(TOKEN_TYPE.RBRACKET)){
                    l.addLvalue(la);
                    ast.add(l);
                    if(lvalue1()){
                        return true;
                    }
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //LVALUE' -> . ID LVALUE'
        if(match(TOKEN_TYPE.DOT)){
            LvalueUnic l = (LvalueUnic)ast.pop();
            Token aux = nxt;
            int l1=getCurrLine();
            int c1=getCurrCol();
            if(match(TOKEN_TYPE.ID)){
                l.addLvalue(new LvalueSelect(li,c, new ID(l1, c1, (String)aux.getLexeme())));
                ast.add(l);
                if(lvalue1()){
                    return true;
                }
            }
        }
        backtrack(stk.size()-n, ast.size()-k);
        //LVALUE' -> ϵ
        
        return true;
    }
}
