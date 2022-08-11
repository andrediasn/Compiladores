/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.visitors;

import lang.ast.*;
import lang.langUtil.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class JavaVisitor extends Visitor {

	private STGroup groupTemplate;
	private ST type, stmt, expr;
	private List<ST> funcs, params, datas, decls,decls2;
	private ArrayList<STyFunc> styfuncs;
	private String fileName;
	private TyEnv<LocalEnv<SType>> env;  
    private ArrayList<TyEnv<LocalEnv<SType>>> envs;
	private FileWriter file;
	public JavaVisitor(String fileName, ArrayList<TyEnv<LocalEnv<SType>>> envs, ArrayList<STyFunc> f) throws IOException{
		groupTemplate = new STGroupFile("./template/js.stg");
		this.fileName = fileName;
		file = new FileWriter("codigoGerado/" + this.fileName + ".js");
		this.envs = envs;
		//System.out.print(envs);
		this.styfuncs = f;
	}

	private void criaArquivo(ST template) throws IOException {
        file.write(template.render()); // newline
        file.close();
    }

	public void visit(Program p) {
		ST template = groupTemplate.getInstanceOf("program");

		template.add("name", fileName);

		datas = new ArrayList<ST>();
		if(p.getDatas()!=null)
		{
			for (Data d : p.getDatas()) {
				d.accept(this);
			}
		}
		template.add("datas", datas);

		funcs = new ArrayList<ST>();
		if(p.getFuncs()!=null)
		{
			int i = 0;
			for (Func f : p.getFuncs()) {
				env = envs.get(i);
				f.accept(this);
				i++;
			}
		}
		template.add("funcs", funcs);

		//System.out.println(template.render());
		try {
            criaArquivo(template);
        } catch (IOException e) {
            throw new RuntimeException("geracao de codigo falhou.");
        }
	}

	public void visit(Add e) {
		ST aux = groupTemplate.getInstanceOf("add_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Sub e) {
		ST aux = groupTemplate.getInstanceOf("sub_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Mult e) {
		ST aux = groupTemplate.getInstanceOf("mul_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Div e) {
		ST aux = groupTemplate.getInstanceOf("div_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(CModule e) {
		ST aux = groupTemplate.getInstanceOf("cmodule_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(And e) {
		ST aux = groupTemplate.getInstanceOf("and_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Lt e) {
		ST aux = groupTemplate.getInstanceOf("lt_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Eq e) {
		ST aux = groupTemplate.getInstanceOf("equals_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

	public void visit(Not e) {
		ST aux = groupTemplate.getInstanceOf("not_expr");
		e.getExpression().accept(this);
		aux.add("expr", expr);
		expr = aux;
	}

	public void visit(True e) {
		expr = groupTemplate.getInstanceOf("boolean_expr");
		expr.add("value", true);
	}

	public void visit(False e) {
		expr = groupTemplate.getInstanceOf("boolean_expr");
		expr.add("value", false);
	}

	public void visit(Int e) {
		expr = groupTemplate.getInstanceOf("int_expr");
		expr.add("value", e.getValue());
	}

	public void visit(FloatV e) {
		expr = groupTemplate.getInstanceOf("float_expr");
		expr.add("value", e.getValue());
	}

	public void visit(Attr e) {
		stmt = groupTemplate.getInstanceOf("attr");
		e.getValue().accept(this);
		stmt.add("var", expr);
		e.getExpression().accept(this);
		stmt.add("expr", expr);
	}

	public void visit(If e) { 
		ST aux = groupTemplate.getInstanceOf("if");
		e.getExpression().accept(this);
		aux.add("expr", expr);
		e.getThen().accept(this);
		aux.add("thn", stmt);
		SuperNode n = e.getElse();
		if (n != null) {
			n.accept(this);
			aux.add("els", stmt);
		}
		stmt = aux;
	}

	public void visit(Print e) {
		stmt = groupTemplate.getInstanceOf("print");
		e.getExpression().accept(this);
		stmt.add("expr", expr);
	}

	private String getSTypeDefault(SType t) {
        if(t instanceof STyInt) {
            return "0";
        }else if(t instanceof STyBool) {
            return "false";
        }else if(t instanceof STyFloat) {
            return "0f";
        }else if(t instanceof STyChar) {
            return "' '";
        }else if(t instanceof STyData) {
            return "null";
        } else if(t instanceof STyArr) {
            return "null";
        } else {
            return "";
        }
    }

	public void visit(Func f) { 
		ST fun = groupTemplate.getInstanceOf("func");
		fun.add("name", f.getID());

		if(f.getType()!=null)
		{
			fun.add("retorno", "tem");
		}
		
		Set<String> keys = env.getKeys();
		ArrayList<ST> chaves = new ArrayList<ST>();
		params = new ArrayList<ST>();
		if (f.getParam() != null) {
			for (Param p : f.getParam()) {
				ST chave = groupTemplate.getInstanceOf("chave");
				chave.add("value", p.getID());
				chaves.add(chave);
				keys.remove(p.getID());
				p.accept(this);
			}
		}
		fun.add("key", chaves);
		fun.add("params", params);

		for (String key : keys) {
			ST decl = groupTemplate.getInstanceOf("decl_func");
			decl.add("name", key);
			SType t = env.get(key).getFuncType();
			processSType(t); 
			String defaultVal = getSTypeDefault(t);
            decl.addAggr("type.{tp, default}", type, defaultVal);
			fun.add("decl", decl);
		}
		ArrayList<ST> stmts = new ArrayList<ST>();
		if (f.getBody() != null)
		{
			for (Cmd c : f.getBody()) {
				c.accept(this);
				stmts.add(stmt);
			}
		}
		fun.add("stmt", stmts);

		funcs.add(fun);
	}

	public void visit(Return e) {
		stmt = groupTemplate.getInstanceOf("return");
		ArrayList<ST> auxs = new ArrayList<ST>();
		for (Expr ex : e.getExpressions()) {
			ST aux = groupTemplate.getInstanceOf("aux");
			ex.accept(this); 
			aux.add("expr", expr);
			auxs.add(aux);
		}
		stmt.add("aux", auxs);
	}

	public void visit(Param e) {
		ST param = groupTemplate.getInstanceOf("param");
		e.getType().accept(this);
		param.add("name", e.getID());
		params.add(param);
	}

	public void visit(TyInt t) {
		type = groupTemplate.getInstanceOf("int_type");
	}

	public void visit(TyFloat t) {
		type = groupTemplate.getInstanceOf("float_type");
	}

	public void visit(TyBool t) {
		type = groupTemplate.getInstanceOf("boolean_type");
	}

	////////////// Métodos ///////////
	private void processSType(SType t) { 
		if (t instanceof STyInt){
			type = groupTemplate.getInstanceOf("int_type");
		}else if (t instanceof STyBool){
			type = groupTemplate.getInstanceOf("boolean_type");
		}else if (t instanceof STyFloat){
			type = groupTemplate.getInstanceOf("float_type");
		}else if(t instanceof STyChar) {
			type = groupTemplate.getInstanceOf("char_type");
		}else if(t instanceof STyData) {
			ST aux = groupTemplate.getInstanceOf("id_type");
			aux.add("value", ((STyData) t).getId());
			type = aux; 
		} else if (t instanceof STyArr) {
			//ST aux = groupTemplate.getInstanceOf("array_type");
			//ST aux = groupTemplate.getInstanceOf("id_type");
			//processSType(((STyArr) t).getArg());
			//aux.add("value", ((STyData) t).getId());
			//type = aux;
		}
	}

	@Override
	public void visit(Data d) {
		ST aux = groupTemplate.getInstanceOf("data");
        aux.add("name", d.getId());
        decls = new ArrayList<ST>();
        for(Decl dec : d.getTypes()){
            dec.accept(this);
        }
		for(int i =0;i < decls.size();i++){
			decls.get(i).add("recivedNull",": null");
			decls.get(i).add("comma",",");

		}
		
		aux.add("decl", decls);	
        datas.add(aux);
	}

	@Override
	public void visit(Decl e) {
		ST dec = groupTemplate.getInstanceOf("decl");
        e.getType().accept(this);
        dec.add("type", type);
        dec.add("name", e.getId());
		dec.add("param","'");
        decls.add(dec);
	}

	@Override
	public void visit(TyChar t) {
		type = groupTemplate.getInstanceOf("char_type");

	}

	@Override
	public void visit(TyID t) {
		type = groupTemplate.getInstanceOf("id_type");
		type.add("value", t.getIdType());
	}

	public void visit(CallCmd e) { 
		ST aux = groupTemplate.getInstanceOf("call_cmd");
		aux.add("name", e.getName());
        for(Expr exp : e.getExpressions()) {
            exp.accept(this);
            aux.add("args", expr);
		}
		if(e.getReturn() != null) {
			int indexRet = 0;
			for(LValue lv : e.getReturn()) {
				lv.accept(this);
				SType t = env.get(((LValue) lv).getId()).getFuncType();
				processSType(t);
				aux.addAggr("exp.{id, type, index}", expr, type, indexRet);
				indexRet++;
			}
		}
        stmt = aux;
	}

	@Override
	public void visit(CallExpr e) { 
		ST aux = groupTemplate.getInstanceOf("call_expr");
		aux.add("name", e.getName());
		if(e.getExpressions()!=null)
		{
			for(Expr exp : e.getExpressions()) {
				exp.accept(this);
				aux.add("args", expr);
			}
		}
		STyFunc auxi = styfuncs.get(0);
		styfuncs.remove(0);
		Int n = (Int)e.getReturn();
		int index = n.getValue();
		processSType(auxi.getRetorno()[index]);
		aux.add("type",type);
		e.getReturn().accept(this);
		aux.add("expr", expr);
		expr = aux;
	}

	@Override
	public void visit(StmtList e) {
		ST aux = groupTemplate.getInstanceOf("stmt_list"); 
		ArrayList<ST> stmts = new ArrayList<ST>();
		if (e.getList() != null)
		{
			for (Cmd c : e.getList()) {
				c.accept(this);
				stmts.add(stmt);
			}
		}
		aux.add("stmts", stmts);
		stmt = aux; 
	}

	@Override
	public void visit(Iterate e) {
		ST aux = groupTemplate.getInstanceOf("iterate");
        e.getExpression().accept(this);
        aux.add("exp", expr);
		aux.add("linhaColuna", e.getLine()+"_"+e.getColumn());
        e.getBody().accept(this);
        aux.add("stmt", stmt);
        stmt = aux;
	}

	@Override
	public void visit(Char e) {
		expr = groupTemplate.getInstanceOf("char_expr");
		char aux = e.getValue();
		if (aux == '\n'){
			expr.add("value", "\\n");
		}else if (aux == ('\t')){
			expr.add("value", "\\t");
		}else if (aux == ('\b')){
			expr.add("value", "\\b");
		}else if (aux == ('\r')){
			expr.add("value", "\\r");
		}else if (aux == ('\\')){
			expr.add("value","\\\\");
		}else if (aux == ('\'')){
			expr.add("value", "\\'");
		}else{
			expr.add("value", e.getValue());
		}
	}

	@Override
	public void visit(LValue e) {
		ST aux = groupTemplate.getInstanceOf("lvalue");
		aux.add("name", e.getId());
		if(e.getAccess() != null){
			for(Access lv : e.getAccess()) {
				lv.accept(this);
				aux.add("array", expr);
			}
		}
        expr = aux;
	}

	@Override
	public void visit(AccessData e) {
		ST aux = groupTemplate.getInstanceOf("data_access");
        aux.add("expr", e.getIndex());
        expr = aux;
	}

	@Override
	public void visit(Array e) {
		ST aux = groupTemplate.getInstanceOf("array_access");
        e.getIndex().accept(this);
        aux.add("expr", expr);
        expr = aux;
	}

	@Override
	public void visit(SMinus e) {
		ST aux = groupTemplate.getInstanceOf("sminus_expr");
		e.getExpression().accept(this);
		aux.add("expr", expr);
		expr = aux;

	}

	@Override
	public void visit(Neq e) {
		ST aux = groupTemplate.getInstanceOf("neq_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;

	}
	
	private String getTypeDefaultVal(BType t) {
        if(t instanceof TyInt) {
            return "0";
        }else if(t instanceof TyBool) {
            return "false";
        }else if(t instanceof TyFloat) {
            return "0f";
        }else if(t instanceof TyChar) {
            return "' '";
        } else {
            return "";
        }
    }

	@Override
	public void visit(New e) {
		ST aux;
		int n = e.getType().getBraces();
        e.getType().getBtype().accept(this);
        if(e.getExpression() != null){
            aux = groupTemplate.getInstanceOf("new_expr_array");
            if(n > 0){
                for(int i = 0; i < n; i++){
                    aux.add("squares", "[]");
                }
            }
            aux.add("type", type);

            e.getExpression().accept(this);
            aux.add("expr", expr);
        } else {
			if(n > 0){
				aux = groupTemplate.getInstanceOf("new_array");
                for(int i = 0; i < n-1; i++){
                    aux.add("squares", "[]");
				}
				aux.add("type", type);
			}
			else
			{
				if(e.getType().getBtype() instanceof TyID)
				{
					aux = groupTemplate.getInstanceOf("new_expr_type");
            		aux.add("type", type);
				}
				else
				{
					aux = groupTemplate.getInstanceOf("new_padrao");
					String padrao = getTypeDefaultVal(e.getType().getBtype());
					aux.add("value", padrao);
				}
			}
        }
        expr = aux;
	}

	@Override
	public void visit(Null e) {
		expr = groupTemplate.getInstanceOf("null_expr");
	}

	@Override
	public void visit(Read e) {
		ST aux = groupTemplate.getInstanceOf("read");
        e.getValue().accept(this);
        aux.add("var", expr);
        stmt = aux;
	}

	@Override
	public void visit(Type e) {
		ST aux = groupTemplate.getInstanceOf("ltype");
		e.getBtype().accept(this);
		aux.add("type", type);
		int n = e.getBraces();
		String x = "";
		for(int i = 0; i < n; i++)
		{
			x = x + "[]";
		}
		aux.add("colchetes", x);
		type = aux;
	}

}
