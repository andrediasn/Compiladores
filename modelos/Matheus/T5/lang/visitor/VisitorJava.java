/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/
package lang.visitor;

import lang.ast.*;
import lang.type.*;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class VisitorJava extends Visitor {

  private STGroup groupTemplate;
  private ST type, stmt, expr;
  private List<ST> funcs, params, datas, decls;
  private String fileName;
	private FileWriter file;
	
	private HashMap<String, SType> localEnv;
	private ArrayList<HashMap<String, SType>> env;
	private ArrayList<STypeFunc> functions;

  public VisitorJava(String fileName, ArrayList<HashMap<String, SType>> env, ArrayList<STypeFunc> functions) throws IOException {
    groupTemplate = new STGroupFile("./template/java.stg");
    this.fileName = fileName;
		file = new FileWriter("codigoJava/" + this.fileName + ".java");
    this.env = env;
    System.out.print(env);
		this.functions = functions;
  }


  public void visit(And and){
		ST aux = groupTemplate.getInstanceOf("and_op");
		and.getLeft().accept(this);
		aux.add("left_expr", expr);
		and.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}


  public void visit(Assign assign){
   	stmt = groupTemplate.getInstanceOf("assign_op");
		assign.getValue().accept(this);
		stmt.add("value", expr);
		assign.getExpression().accept(this);
		stmt.add("exp", expr);
  }


  public void visit(CallCmd callCmd){
		ST aux = groupTemplate.getInstanceOf("call_cmd");
		aux.add("name", callCmd.getName());
		for (Exp expression : callCmd.getExpressions()) {
			expression.accept(this);
			aux.add("args", expr);
		}

		if (callCmd.getReturnable() != null) {
			for (Lvalue lValue : callCmd.getReturnable()) {
				lValue.accept(this);
				SType sType = localEnv.get(((Lvalue) lValue).getId());
				processSType(sType);
				aux.addAggr("exp.{lValue, type}", expr, type);
			}
		}
		stmt = aux;
	}
	

  public void visit(CallExp callExp){
		ST aux = groupTemplate.getInstanceOf("call_expr");
		aux.add("name", callExp.getName());

		if (callExp.getExpressions() != null) {
			for (Exp expression : callExp.getExpressions()) {
				expression.accept(this);
				aux.add("args", expr);
			}
		}

		STypeFunc sTypeFunc = functions.get(0);
		functions.remove(0);
		IntegerT integerT = (IntegerT) callExp.getReturnable();

		int index = integerT.getValue();
		processSType(sTypeFunc.getReturnable()[index]);
		aux.add("type", type);
		callExp.getReturnable().accept(this);
		aux.add("expr", expr);
		expr = aux;
	}


  public void visit(Caracter caract){
		expr = groupTemplate.getInstanceOf("caracter_value");
		char aux = caract.getValue();
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
			expr.add("value", caract.getValue());
		}
	}


  public void visit(CmdArray cmdArray){
		ST aux = groupTemplate.getInstanceOf("cmd_array");
		ArrayList<ST> cmdArrayList = new ArrayList<ST>();

		if (cmdArray.getArray() != null) {
			for (Cmd cmd : cmdArray.getArray()) {
				cmd.accept(this);
				cmdArrayList.add(stmt);
			}
		}

		aux.add("cmds", cmdArrayList);
		stmt = aux;
	}


	public void visit(Data data){
		ST aux = groupTemplate.getInstanceOf("data");
		aux.add("name", data.getId());
		decls = new ArrayList<ST>();
		for (Decl declaration : data.getTypes()) {
			declaration.accept(this);
		}

		aux.add("decl_exps", decls);
		datas.add(aux);
	}
	

  public void visit(Decl decl){
		ST aux = groupTemplate.getInstanceOf("decl_op");
		decl.getType().accept(this);
		aux.add("type", type);
		aux.add("name", decl.getId());
		decls.add(aux);
	}


  public void visit(Div div){
		ST aux = groupTemplate.getInstanceOf("div_op");
		div.getLeft().accept(this);
		aux.add("left_expr", expr);
		div.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
  }
    
	
  public void visit(DoubleT doub){
		expr = groupTemplate.getInstanceOf("double_value");
		expr.add("value", doub.getValue());
	}


  public void visit(Eq eq) {
		ST aux = groupTemplate.getInstanceOf("eq_op");
		eq.getLeft().accept(this);
		aux.add("left_expr", expr);
		eq.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}


  public void visit(False fal){
		expr = groupTemplate.getInstanceOf("boolean_value");
		expr.add("value", false);
	}


  public void visit(Func func) {
		ST aux = groupTemplate.getInstanceOf("func_op");
		aux.add("name", func.getID());

		if (func.getTypeReturn() != null) {
			aux.add("returnable", "has returnable");
		}

		Set<String> keysSet = localEnv.keySet();
		ArrayList<ST> keys = new ArrayList<ST>();

		params = new ArrayList<ST>();
		if (func.getParams() != null) {
			for (Params auxParam : func.getParams()) {
				ST key = groupTemplate.getInstanceOf("key");
				key.add("value", auxParam.getID());
				keys.add(key);
				keysSet.remove(auxParam.getID());
				auxParam.accept(this);
			}
		}

		aux.add("key", keys);
		aux.add("params", params);

		for (String keyAux : keysSet) {
			ST funcDecl = groupTemplate.getInstanceOf("func_decl");
			funcDecl.add("name", keyAux);
			SType sType = localEnv.get(keyAux);
			processSType(sType);
			String dValue = getSTypeValue(sType);
			funcDecl.addAggr("type.{tp, default}", type, dValue);
			aux.add("decl", funcDecl);
		}

		ArrayList<ST> stAux = new ArrayList<ST>();
		if (func.getBody() != null) {
			for (Cmd cmd : func.getBody()) {
				cmd.accept(this);
				stAux.add(stmt);
			}
		}

		aux.add("stmt", stAux);
		funcs.add(aux);
	}
    
	
	public void visit(If i) {
		ST aux = groupTemplate.getInstanceOf("if_op");
		
		i.getExpression().accept(this);
		aux.add("exp", expr);
		i.getThe().accept(this);
		aux.add("the", stmt);

		SuperNode node = i.getEls();
		if (node != null) {
			node.accept(this);
			aux.add("els", stmt);
		}

		stmt = aux;
	}


  public void visit(IntegerT integer){
		expr = groupTemplate.getInstanceOf("integer_value");
		expr.add("value", integer.getValue());
  }
    

  public void visit(Iterate ite) {
		ST aux = groupTemplate.getInstanceOf("iterate_op");
		ite.getExpression().accept(this);
		aux.add("exp", expr);
		aux.add("unique", "_"+ite.getLine()+"_"+ite.getColumn());
		ite.getBody().accept(this);
		aux.add("cmd", stmt);
		stmt = aux;
	}


  public  void visit(Less less) {
		ST aux = groupTemplate.getInstanceOf("less_op");
		less.getLeft().accept(this);
		aux.add("left_expr", expr);
		less.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}


  public void visit(Lvalue lValue) {
		ST aux = groupTemplate.getInstanceOf("lvalue");
		aux.add("name", lValue.getId());

		if (lValue.getSelectors() != null) {
			for (Selector lValueSelector : lValue.getSelectors()) {
				lValueSelector.accept(this);
				aux.add("value", expr);
			}
		}
		expr = aux;
	}


  public void visit(Minus minus) {
		ST aux = groupTemplate.getInstanceOf("minus_op");
		minus.getLeft().accept(this);
		aux.add("left_expr", expr);
		minus.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}


  public void visit(ModuleT module) {
		ST aux = groupTemplate.getInstanceOf("module_op");
		module.getLeft().accept(this);
		aux.add("left_expr", expr);
		module.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}


  public void visit(Mult mult) {
		ST aux = groupTemplate.getInstanceOf("mult_op");
		mult.getLeft().accept(this);
		aux.add("left_expr", expr);
		mult.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}


  public void visit(Neg neg) {
		ST aux = groupTemplate.getInstanceOf("neg_op");
		neg.getExpression().accept(this);
		aux.add("value", expr);
		expr = aux;
	}


  public void visit(Neq neq) {
		ST aux = groupTemplate.getInstanceOf("neq_op");
		neq.getLeft().accept(this);
		aux.add("left_expr", expr);
		neq.getRight().accept(this);
		aux.add("right_expr", expr);
		expr = aux;
	}

  public void visit(New ne) {
		ST aux;
		int numBracket = ne.getType().getBraces();
		if(ne.getExpression() != null){
			aux = groupTemplate.getInstanceOf("new_array_exp_op" );

			for(int i =0; i < numBracket; i++){
				aux.add("bracket","[]");
			}

			ne.getType().getBtype().accept(this);
			aux.add("type", type);
			ne.getExpression().accept(this);
			aux.add("exp", expr);
		}
		else {
			if (numBracket > 0){
				aux = groupTemplate.getInstanceOf("new_array_op");

				for(int i =0; i < numBracket-1; i++){
					aux.add("bracket","[]");
				}

				ne.getType().getBtype().accept(this);
				aux.add("type", type);
			}
			else {
				if(ne.getType().getBtype() instanceof TypeIDType ){
					aux = groupTemplate.getInstanceOf("new_type_op");
					ne.getType().getBtype().accept(this);
					aux.add("type", type);
				}
				else{
					aux = groupTemplate.getInstanceOf("new_op");
					String value = getTypeValue(ne.getType().getBtype());
					aux.add("value", value);
				}
			}
		}
	}


  public void visit(Not not) {
		ST aux = groupTemplate.getInstanceOf("not_op");
		not.getExpression().accept(this);
		aux.add("value", expr);
		expr = aux;
	}


  public void visit(Null nu) {
		expr = groupTemplate.getInstanceOf("null_value");
	}


  public void visit(Params params_p) {
		ST aux = groupTemplate.getInstanceOf("params_op");
		params_p.getType().accept(this);
		aux.add("type", type);
		aux.add("name", params_p.getID());
		params.add(aux);
	}


  public void visit(Plus plus) {
    ST aux = groupTemplate.getInstanceOf("plus_op");
    plus.getLeft().accept(this);
    aux.add("left_expr", expr);
    plus.getRight().accept(this);
    aux.add("right_expr", expr);
    expr = aux;
  }


  public void visit(Print pri) {
		stmt = groupTemplate.getInstanceOf("print_op");
		pri.getExpression().accept(this);
		stmt.add("value", expr);
	}


  public void visit(Prog prog) {
		ST aux = groupTemplate.getInstanceOf("prog");
		aux.add("name", fileName);
		
		datas = new ArrayList<ST>();
		if(prog.getDatas() != null){
			for (Data dat : prog.getDatas()){
				dat.accept(this);
			}
		}
		aux.add("datas", datas);
		
		funcs = new ArrayList<ST>();
		if(prog.getFuncs() != null){
			int i = 0;
			for (Func fun : prog.getFuncs()) {
				localEnv = env.get(i);
				fun.accept(this);
				i++;
			}
		}
		aux.add("funcs", funcs);
		
		System.out.println(aux.render());
		try {
        makeFile(aux);
    } catch (IOException e) {
        throw new RuntimeException("Erro durante a geração de codigo!!");
    }
	}


  public void visit(Read read) {
		ST aux = groupTemplate.getInstanceOf("read_op");
    read.getValue().accept(this);
    aux.add("value", expr);
    stmt = aux;
	}


  public void visit(Return ret) {
		stmt = groupTemplate.getInstanceOf("return_op");
		ArrayList<ST> returnList = new ArrayList<ST>();

		for (Exp expression : ret.getExpressions()) {
			ST aux = groupTemplate.getInstanceOf("return_aux");
			expression.accept(this);
			aux.add("value", expr);
			returnList.add(aux);
		}
		stmt.add("return_aux", returnList);
	}


  public void visit(SelectorArray selector) {
		ST aux = groupTemplate.getInstanceOf("array_selector_access");
		selector.getIndex().accept(this);
		aux.add("exp", expr);
		expr = aux;
	}


  public void visit(SelectorData selector) {
		ST aux = groupTemplate.getInstanceOf("data_selector_access");
    aux.add("exp", selector.getIndex());
    expr = aux;
	}


  public void visit(True tr){
		expr = groupTemplate.getInstanceOf("boolean_value");
		expr.add("value", true);
	}


  public void visit(Type types) {
		ST aux = groupTemplate.getInstanceOf("type_value");
		types.getBtype().accept(this);
		aux.add("type", type);

		String bracesString = "";
		int bracesCount = types.getBraces();

		while (bracesCount < 0) {
			bracesString += "[]";
			bracesCount--;
		}

		aux.add("braces", bracesString);
		type = aux;
	}


  public void visit(TypeBool typeBool) {
		type = groupTemplate.getInstanceOf("boolean_type");
  }
    

  public void visit(TypeChar typeChar) {
		type = groupTemplate.getInstanceOf("char_type");
	}


  public void visit(TypeFloat typeFloat){
		type = groupTemplate.getInstanceOf("float_type");
  }
    
  public void visit(TypeIDType typeId) {
		type = groupTemplate.getInstanceOf("id_data_type");
		type.add("value", typeId.getIdType());
	}


  public void visit(TypeInt typeInt) {
		type = groupTemplate.getInstanceOf("int_type");
	}

  //--------------------------------------------
  //-------------FUNÇÕES AUXILIARES-------------
  //--------------------------------------------

    private void makeFile(ST aux) throws IOException {
      file.write(aux.render());
      file.close();
    }


	private void processSType(SType sType) { 
		if (sType instanceof STypeInt){
			type = groupTemplate.getInstanceOf("int_type");
		}
		else if (sType instanceof STypeFloat){
			type = groupTemplate.getInstanceOf("float_type");
		}
		else if(sType instanceof STypeChar) {
			type = groupTemplate.getInstanceOf("char_type");
		}
		else if (sType instanceof STypeBool){
			type = groupTemplate.getInstanceOf("boolean_type");
		}
		else if(sType instanceof STypeData) {
			ST aux = groupTemplate.getInstanceOf("id_data_type");
			aux.add("value", ((STypeData) sType).getId());
			type = aux; 
		}
		else if (sType instanceof STypeArray) {
			ST aux = groupTemplate.getInstanceOf("array_type");
			processSType(((STypeArray) sType).getArg());
			aux.add("type", type);
			type = aux;
		}
	}


	private String getTypeValue(BType bType) {
    if(bType instanceof TypeInt) {
      return "0";
    }
		else if(bType instanceof TypeFloat) {
      return "0f";
    }
		else if(bType instanceof TypeChar) {
      return "' '";
    } 
		else if(bType instanceof TypeBool) {
      return "false";
    }
		else {
      return "";
    }
  }


	private String getSTypeValue(SType sType) {
    if(sType instanceof STypeInt) {
      return "0";
    }else if(sType instanceof STypeFloat) {
      return "0f";
    }
		else if(sType instanceof STypeChar) {
      return "' '";
    }
		else if(sType instanceof STypeBool) {
      return "false";
    }
		else if(sType instanceof STypeData) {
      return "null";
    } 
		else if(sType instanceof STypeArray) {
      return "null";
    } 
		else {
      return "";
    }
  }

}
