/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.visitors;

import lang.ast.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import java.io.IOException;
import lang.tipos.*;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class JavaVisitor extends Visitor {

	private STGroup groupTemplate;
	private ST type, stmt, expr;
	private List<ST> funcs, params, datas, decls;
	private  ArrayList<STyFunc> funcoes;
	private String fileName;
	private HashMap<String, STipo> localEnv;
	private ArrayList<HashMap<String, STipo>> env;
	private FileWriter file;
	public JavaVisitor(String fileName, ArrayList<HashMap<String, STipo>> env, ArrayList<STyFunc> f) throws IOException{
		groupTemplate = new STGroupFile("./template/java.stg");
		this.fileName = fileName;
		file = new FileWriter("codigoGeradoJava/" + this.fileName + ".java");
		this.env = env;
		System.out.print(env);
		this.funcoes = f;
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
				localEnv = env.get(i);
				f.accept(this);
				i++;
			}
		}
		template.add("funcs", funcs);

		System.out.println(template.render());
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

	public void visit(Mul e) {
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

	public void visit(Mod e) {
		ST aux = groupTemplate.getInstanceOf("mod_expr");
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
		e.getExpr().accept(this);
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

	public void visit(NInt e) {
		expr = groupTemplate.getInstanceOf("int_expr");
		expr.add("value", e.getValue());
	}

	public void visit(NFloat e) {
		expr = groupTemplate.getInstanceOf("float_expr");
		expr.add("value", e.getValue());
	}

	public void visit(Attr e) {
		stmt = groupTemplate.getInstanceOf("attr");
		e.getID().accept(this);
		stmt.add("var", expr);
		e.getExp().accept(this);
		stmt.add("expr", expr);
	}

	public void visit(If e) { 
		ST aux = groupTemplate.getInstanceOf("if");

		e.getTeste().accept(this);
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
		e.getExpr().accept(this);
		stmt.add("expr", expr);
	}

	private String getSTipoDefault(STipo t) {
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
        } else if(t instanceof STyArray) {
            return "null";
        } else {
            return "";
        }
    }

	public void visit(Func f) { 
		ST fun = groupTemplate.getInstanceOf("func");
		fun.add("name", f.getID());
		if(f.getTipo()!=null)
		{
			fun.add("retorno", "tem");
		}
		Set<String> keys = localEnv.keySet();
		ArrayList<ST> chaves = new ArrayList<ST>();
		params = new ArrayList<ST>();
		if (f.getParams() != null) {
			for (Param p : f.getParams()) {
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
			STipo t = localEnv.get(key);
			processSType(t); 
			String defaultVal = getSTipoDefault(t);
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
		for (Expr ex : e.getExpr()) {
			ST aux = groupTemplate.getInstanceOf("aux");
			ex.accept(this); 
			aux.add("expr", expr);
			auxs.add(aux);
		}
		stmt.add("aux", auxs);
	}

	public void visit(Param e) {
		ST param = groupTemplate.getInstanceOf("param");
		e.getTipo().accept(this);
		param.add("type", type);
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
	private void processSType(STipo t) { 
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
		} else if (t instanceof STyArray) {
			ST aux = groupTemplate.getInstanceOf("array_type");
			processSType(((STyArray) t).getArg());
			aux.add("type", type);
			type = aux;
		}
	}

	@Override
	public void visit(Data d) {
		ST aux = groupTemplate.getInstanceOf("data");
        aux.add("name", d.getId());
        decls = new ArrayList<ST>();
        for(Decl dec : d.getDecls()){
            dec.accept(this);
        }
        aux.add("decl", decls);
        datas.add(aux);
	}

	@Override
	public void visit(Decl e) {
		ST dec = groupTemplate.getInstanceOf("decl");
        e.getTipos().accept(this);
        dec.add("type", type);
        dec.add("name", e.getId());
        decls.add(dec);
	}

	@Override
	public void visit(TyChar t) {
		type = groupTemplate.getInstanceOf("char_type");

	}

	@Override
	public void visit(TyID t) {
		type = groupTemplate.getInstanceOf("id_type");
		type.add("value", t.getIdTipo());
	}

	public void visit(Call e) { 
		ST aux = groupTemplate.getInstanceOf("call");
		aux.add("name", e.getName());
        for(Expr exp : e.getArgs()) {
            exp.accept(this);
            aux.add("args", expr);
		}
		if(e.getRet()!=null)
		{
			for(Lvalue lv : e.getRet()) {
				lv.accept(this);
				STipo t = localEnv.get(((Lvalue) lv).getId());
				processSType(t);
				aux.addAggr("exp.{lv, type}", expr, type);
			}
		}
        stmt = aux;
	}

	@Override
	public void visit(CallExpr e) { 
		ST aux = groupTemplate.getInstanceOf("call_expr");
		aux.add("name", e.getName());
		if(e.getArgs()!=null)
		{
			for(Expr exp : e.getArgs()) {
				exp.accept(this);
				aux.add("args", expr);
			}
		}
		STyFunc auxi = funcoes.get(0);
		funcoes.remove(0);
		NInt n = (NInt)e.getRet();
		int index = n.getValue();
		processSType(auxi.getRetorno()[index]);
		aux.add("type",type);
		e.getRet().accept(this);
		aux.add("expr", expr);
		expr = aux;
	}

	@Override
	public void visit(CmdList e) {
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
        e.getTeste().accept(this);
        aux.add("exp", expr);
		aux.add("linhaColuna", e.getLine()+"_"+e.getColumn());
        e.getBody().accept(this);
        aux.add("stmt", stmt);
        stmt = aux;
	}

	@Override
	public void visit(LChar e) {
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
	public void visit(Lvalue e) {
		ST aux = groupTemplate.getInstanceOf("lvalue");
		aux.add("name", e.getId());
		if(e.getSeletores() != null)
		{
			for( Seletor lv : e.getSeletores()) {
				lv.accept(this);
				aux.add("array", expr);
			}
		}
        expr = aux;
	}

	@Override
	public void visit(DataSeletor e) {
		ST aux = groupTemplate.getInstanceOf("data_access");
        aux.add("expr", e.getId());
        expr = aux;
	}

	@Override
	public void visit(ArraySeletor e) {
		ST aux = groupTemplate.getInstanceOf("array_access");
        e.getIndice().accept(this);
        aux.add("expr", expr);
        expr = aux;
	}

	@Override
	public void visit(Neg e) {
		ST aux = groupTemplate.getInstanceOf("neg_expr");
		e.getExpr().accept(this);
		aux.add("expr", expr);
		expr = aux;

	}

	@Override
	public void visit(NEq e) {
		ST aux = groupTemplate.getInstanceOf("not_equals_expr");
		e.getLeft().accept(this);
		aux.add("left_expr", expr);
		e.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;

	}
	
	private String getTypeDefaultVal(Btype t) {
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
		int n = e.getTipo().getColchetes();
        e.getTipo().getBtype().accept(this);
        if(e.getExp() != null){
            aux = groupTemplate.getInstanceOf("new_expr_array");
            if(n > 0){
                for(int i = 0; i < n; i++){
                    aux.add("squares", "[]");
                }
            }
            aux.add("type", type);

            e.getExp().accept(this);
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
				if(e.getTipo().getBtype() instanceof TyID)
				{
					aux = groupTemplate.getInstanceOf("new_expr_type");
            		aux.add("type", type);
				}
				else
				{
					aux = groupTemplate.getInstanceOf("new_padrao");
					String padrao = getTypeDefaultVal(e.getTipo().getBtype());
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
        e.getVar().accept(this);
        aux.add("var", expr);
        stmt = aux;
	}

	@Override
	public void visit(Tipo e) {
		ST aux = groupTemplate.getInstanceOf("tipo");
		e.getBtype().accept(this);
		aux.add("type", type);
		int n = e.getColchetes();
		String x = "";
		for(int i = 0; i < n; i++)
		{
			x = x + "[]";
		}
		aux.add("colchetes", x);
		type = aux;
	}

}
