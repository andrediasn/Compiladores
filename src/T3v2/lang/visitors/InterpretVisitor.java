/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/
package lang.visitors;

import lang.ast.*;
import java.util.*;

public class InterpretVisitor extends Visitor{

  	private Stack<HashMap<String, Object>> env;
	private Stack<Object> operands;
    private HashMap<String, Func> funcs;
	private HashMap<String, Data> types;
    private ArrayList<Object> returnables;
    private boolean retMode, debug;

    public InterpretVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        operands = new Stack<Object>();
        funcs = new HashMap<String, Func>();
		types = new HashMap<String, Data>();
        returnables = new ArrayList<Object>();
        retMode = false;
        debug = false;
    }

    public InterpretVisitor(boolean debug) {
        this();
        this.debug = debug;
    }

    public void visit(And and) {
        try {
            and.getLeft().accept(this);
            and.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();
            operands.push(Boolean.valueOf((Boolean) left && (Boolean) right));
        } catch (ValException exception) {
            throw new RuntimeException(" (" + and.getLine() + ", " + and.getColumn() + ") " + exception.getMessage());
        }
    }

	public void visit(Attr attr) {
        try {
            Var var = attr.getValue();
            attr.getExpression().accept(this);
            Object val = operands.pop();
            Object obj = null;

            if (env.peek().containsKey(var.getId())) {
                obj = env.peek().get(var.getId());
            }

            ArrayList<Selector> selectors = var.getSelectors();

            if (!selectors.isEmpty()) {
                for (int k = 0; k < selectors.size() - 1; k++) {
                    selectors.get(k).accept(this);
                    Object select = operands.pop();
                    if (selectors.get(k) instanceof AccessData) {
                        obj = ((HashMap<String, Object>) obj).get(select);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) select);
                    }
                }
                selectors.get(selectors.size() - 1).accept(this);
                Object select = operands.pop();

                if (selectors.get(selectors.size() - 1) instanceof AccessData) {
                    ((HashMap<String, Object>) obj).put((String) select, val);
                } else {
                    ((ArrayList) obj).set((Integer) select, val);
                }
            } else {
                env.peek().put(var.getId(), val);
            }

        } catch (ValException exception) {
            throw new RuntimeException(" (" + attr.getLine() + ", " + attr.getColumn() + ") " + exception.getMessage());
        }
    }

	public void visit(CallCmd callCmd) {
        try {
            Func f = funcs.get(callCmd.getName());
            if (f != null) {
                for (Exp exp : callCmd.getExpressions()) {
                    exp.accept(this);
                }
                f.accept(this);
                if (callCmd.getReturnable() != null) {
                    Var[] s = callCmd.getReturnable();

                    for (int i = 0; i < s.length; i++) {
                        Object val = returnables.get(i);
                        Var var = s[i];
                        Object obj = env.peek().get(var.getId());
                        ArrayList<Selector> selectors = var.getSelectors();

                        if (!selectors.isEmpty()) {
                            for (int k = 0; k < selectors.size() - 1; k++) {
                                selectors.get(k).accept(this);
                                Object select = operands.pop();
                                if (selectors.get(k) instanceof AccessData) {
                                    obj = ((HashMap<String, Object>) obj).get(select);
                                } else {
                                    obj = ((ArrayList) obj).get((Integer) select);
                                }
                            }
                            selectors.get(selectors.size() - 1).accept(this);
                            Object select = operands.pop();
                            if (selectors.get(selectors.size() - 1) instanceof AccessData) {
                                ((HashMap<String, Object>) obj).put((String) select, val);
                            } else {
                                ((ArrayList) obj).set((Integer) select, val);
                            }
                        } else {
                            env.peek().put(var.getId(), val);
                        }
                    }
                }
                returnables.clear();
            } else {
                throw new RuntimeException(" (" + callCmd.getLine() + ", " + callCmd.getColumn() + ") Função não definida " + callCmd.getName());
            }

        } catch (ValException exception) {
            throw new RuntimeException(" (" + callCmd.getLine() + ", " + callCmd.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(CallExp callExp) {
        try {
            Func f = funcs.get(callExp.getName());
            if (f != null) {
                for (Exp exp : callExp.getExpressions()) {
                    exp.accept(this);
                }
                f.accept(this);

                callExp.getReturnable().accept(this);
                Integer pos = (Integer) operands.pop();
                Object result = returnables.get(pos);
                operands.push(result);
                returnables.clear();

            } else {
                throw new RuntimeException(" (" + callExp.getLine() + ", " + callExp.getColumn() + ") Função não existe " + callExp.getName());
            }

        } catch (ValException exception) {
            throw new RuntimeException(" (" + callExp.getLine() + ", " + callExp.getColumn() + ") " + exception.getMessage());
        }

    }

    public void visit(Caracter caract) {
        try {
            operands.push(caract.getValue());
        } catch (ValException exception) {
            throw new RuntimeException(" (" + caract.getLine() + ", " + caract.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(StmtList stmtList) {
        if (retMode) {
            return;
        }
        try {
            for (Cmd cmd : stmtList.getList()) {

                cmd.accept(this);
                if (retMode) {
                    return;
                }
            }
        } catch (ValException exception) {
            throw new RuntimeException(" (" + stmtList.getLine() + ", " + stmtList.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Data data) { }

    @Override
    public void visit(Decl decl) { }
		
  	public void visit(Div div) {
        try {
            div.getLeft().accept(this);
            div.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();
            
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left / (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left / (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left / (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left / (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}

        } catch (ValException exception) {
            throw new RuntimeException(" (" + div.getLine() + ", " + div.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(FloatV doub) {
        try {
            operands.push(Float.valueOf(doub.getValue()));
        } catch (ValException exception) {
            throw new RuntimeException(" (" + doub.getLine() + ", " + doub.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Eq eq) {
        try {
            eq.getLeft().accept(this);
            eq.getRight().accept(this);
            operands.push(Boolean.valueOf(operands.pop().equals(operands.pop())));
        } catch (ValException exception) {
            throw new RuntimeException(" (" + eq.getLine() + ", " + eq.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(False fal) {
        try {
            operands.push(Boolean.valueOf(false));
        } catch (ValException expression) {
            throw new RuntimeException(" (" + fal.getLine() + ", " + fal.getColumn() + ") " + expression.getMessage());
        }
    }
    
    public void visit(Func func) {
        HashMap<String, Object> lv = new HashMap<String, Object>();
        if (func.getParam() != null) {
            for (int i = func.getParam().length - 1; i >= 0; i--) {
                lv.put(func.getParam()[i].getID(), operands.pop());
            }
        }
        env.push(lv);

        for (Cmd cmd : func.getBody()) {
            if (retMode) {
                break;
            }
            cmd.accept(this);
        }

        if (debug && func.getID().equals("main")) {

            Object[] obj = env.peek().keySet().toArray();
            System.out.println("-------------- Memoria ----------------");

            for (int i = 0; i < obj.length; i++) {
                System.out.print(((String) obj[i]) + " : " + env.peek().get(obj[i]).toString());
            }

        }
        env.pop();
        retMode = false;
    }

    public void visit(If i) {
        try {
            i.getExpression().accept(this);
            if ((Boolean) operands.pop()) {
                i.getThe().accept(this);
            } else if (i.getEls() != null) {
                i.getEls().accept(this);
            }
        } catch (ValException exception) {
            throw new RuntimeException(" (" + i.getLine() + ", " + i.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Int integer) {
        try {
            operands.push(Integer.valueOf(integer.getValue()));
        } catch (ValException exception) {
            throw new RuntimeException(" (" + integer.getLine() + ", " + integer.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Iterate ite) {
        try {
            ite.getExpression().accept(this);
            Object obj = operands.pop();
            Object objClass = obj.getClass();
            if (objClass == Integer.class) {

                for (int i = 0; i < (Integer) obj; i++) {
                    ite.getBody().accept(this);
                    ite.getExpression().accept(this);
                }
            } else {
                throw new RuntimeException("Dados se diferenciam");
            }
        } catch (ValException exception) {
            throw new RuntimeException(" (" + ite.getLine() + ", " + ite.getColumn() + ") " + exception.getMessage());
        }
    }
    
	public void visit(Less less) {
        try {
            less.getLeft().accept(this);
            less.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();

            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left < (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left < (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left < (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left < (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
						
        } catch (ValException exception) {
            throw new RuntimeException(" (" + less.getLine() + ", " + less.getColumn() + ") " + exception.getMessage());
        }
    }
    
    public void visit(Var var) {
        try {
            if (env.peek().containsKey(var.getId())) {
                Object obj = env.peek().get(var.getId());
                if (var.getSelectors().size() != 0) {
                    for (Selector lv : var.getSelectors()) {
                        lv.accept(this);
                        if (lv instanceof AccessData) {
                            obj = ((HashMap<String, Object>) obj).get((String) operands.pop());
                        } else if (lv instanceof AccessArray) {
                            obj = ((ArrayList) obj).get((Integer) operands.pop());
                        }
                    }
                }
                operands.push(obj);
            } else {
                throw new RuntimeException(" (" + var.getLine() + ", " + var.getColumn() + ") não declarado " + var.getId());
            }
        } catch (ValException exception) {
            throw new RuntimeException(" (" + var.getLine() + ", " + var.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Minus minus) {
        try {
            minus.getLeft().accept(this);
            minus.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left - (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left - (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left - (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left - (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
        } catch (ValException exception) {
            throw new RuntimeException(" (" + minus.getLine() + ", " + minus.getColumn() + ") " + exception.getMessage());
        }
    }
    
    public void visit(CModule cModule) {
        try {
            cModule.getLeft().accept(this);
            cModule.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left % (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left % (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left % (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left % (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}

        } catch (ValException exception) {
            throw new RuntimeException(" (" + cModule.getLine() + ", " + cModule.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Mult mult) {
        try {
            mult.getLeft().accept(this);
            mult.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left * (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left * (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left * (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left * (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
        } catch (ValException exception) {
            throw new RuntimeException(" (" + mult.getLine() + ", " + mult.getColumn() + ") " + exception.getMessage());
        }
    }
    
    public void visit(SMinus sMinus) {
        try {
            sMinus.getExpression().accept(this);
            Number exp;
            exp = (Number) operands.pop();
            if (exp.getClass() == Integer.class) {
                operands.push(-exp.intValue());
            } else if (exp.getClass() == Float.class) {
                operands.push(-exp.floatValue());
            } else {
                throw new RuntimeException("Inválido");
            }
        } catch (ValException exception) {
            throw new RuntimeException(" (" + sMinus.getLine() + ", " + sMinus.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Neq neq) {
        try {
            neq.getLeft().accept(this);
            neq.getRight().accept(this);
            operands.push(!operands.pop().equals(operands.pop()));
        } catch (ValException exception) {
            throw new RuntimeException(" (" + neq.getLine() + ", " + neq.getColumn() + ") " + exception.getMessage());
        }
    }
    
    public void visit(New ne) {
        try {
            Type type = ne.getType();
            BType bType = type.getBtype();
            Integer t = 0;
            ArrayList<Object> val = null;
            ArrayList<Object> aux = null;
            boolean idType = false;
            HashMap<String, Object> a = null;
            if (bType instanceof TyID) {
                idType = true;
                TyID tyId = (TyID) bType;
                a = new HashMap<>();
                Data data = types.get(tyId.getIdType());
                for (Decl decl : data.getTypes()) {
                    a.put(decl.getId(), null);
                }
            }
            if (ne.getExpression() != null) {
                ne.getExpression().accept(this);
                t = (Integer) operands.pop();
                if (idType) {
                    val = new ArrayList<Object>(t);
                    for (int i = 0; i < t; i++) {
                        val.add(a);
                    }
                } else {
                    val = new ArrayList<Object>(t);
                    for (int i = 0; i < t; i++) {
                        val.add(null);
                    }
                }
                aux = null;
                for (int i = 0; i < type.getBraces(); i++) {
                    aux = new ArrayList<Object>();
                    aux.add(val);
                    val = aux;
                }
                operands.push(val);
            } else {
                if (type.getBraces() != 0) {
                    val = new ArrayList<Object>();
                    if (idType) {
                        val.add(a);
                    } else {
                        val.add(null);
                    }
                    aux = null;
                    for (int i = 0; i < type.getBraces() - 1; i++) {
                        aux = new ArrayList<Object>();
                        aux.add(val);
                        val = aux;
                    }
                    operands.push(val);
                } else {
                    if (idType) {
                        operands.push(a);
                    } else {
                        operands.push(null);
                    }
                }
            }
        } catch (ValException exception) {
            throw new RuntimeException(" (" + ne.getLine() + ", " + ne.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Not not) {
        try {
            not.getExpression().accept(this);
            operands.push(!(Boolean) operands.pop());
        } catch (ValException exception) {
            throw new RuntimeException(" (" + not.getLine() + ", " + not.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Null nu) {
        try {
            operands.push(null);
        } catch (ValException exception) {
            throw new RuntimeException(" (" + nu.getLine() + ", " + nu.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Param param) { }

    public void visit(Plus plus) {
        try {
            plus.getLeft().accept(this);
            plus.getRight().accept(this);
            Object left, right;
            right = operands.pop();
            left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left + (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left + (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left + (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left + (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
        } catch (ValException exception) {
            throw new RuntimeException(" (" + plus.getLine() + ", " + plus.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Print pri) {
        try {
            pri.getExpression().accept(this);
            System.out.print(operands.pop());
        } catch (ValException exception) {
            throw new RuntimeException(" (" + pri.getLine() + ", " + pri.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Program program) {
        Func main = null;
        if (program.getDatas() != null) {
            for (Data data : program.getDatas()) {
                types.put(data.getId(), data);
            }
        }
        for (Func func : program.getFuncs()) {
            funcs.put(func.getID(), func);
            if (func.getID().equals("main")) {
                main = func;
            }
        }
        if (main == null) {
            throw new RuntimeException("Main não existe");
        }
        main.accept(this);
    }

    public void visit(Read read) {
        try {
            Scanner scan = new Scanner(System.in);
            Object Input = scan.nextLine();
            Var var = read.getValue();
            if (!var.getSelectors().isEmpty()) {
                Object obj = (Object) env.peek().get(var.getId());
                for (int k = 0; k < var.getSelectors().size() - 1; k++) {
                    var.getSelectors().get(k).accept(this);
                    Object s = operands.pop();
                    if (var.getSelectors().get(k) instanceof AccessData) {
                        obj = ((HashMap<String, Object>) obj).get(s);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) s);
                    }
                }
                var.getSelectors().get(var.getSelectors().size() - 1).accept(this);
                Object s = operands.pop();
                if (var.getSelectors().get(var.getSelectors().size() - 1) instanceof AccessData) {
                    ((HashMap<String, Object>) obj).put((String) s, Input);
                } else {
                    ((ArrayList) obj).set((Integer) s, Input);
                }
            } else {
                env.peek().put(read.getValue().getId(), Input);
            }
        } catch (ValException exception) {
            throw new RuntimeException(" (" + read.getLine() + ", " + read.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(Return ret) {
        ArrayList<Object> aux = new ArrayList<Object>();
        for (Exp exp : ret.getExpressions()) {
            exp.accept(this);
            aux.add(operands.pop());
        }
        for (int i = 0; i < aux.size(); i++) {
            returnables.add(aux.get(i));
        }
        retMode = true;
    }

    public void visit(AccessArray selector) {
        try {
            Exp exp = selector.getIndex();
            exp.accept(this);
        } catch (ValException exception) {
            throw new RuntimeException(" (" + selector.getLine() + ", " + selector.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(AccessData selector) {
        try {
            operands.push(selector.getIndex());
        } catch (ValException exception) {
            throw new RuntimeException(" (" + selector.getLine() + ", " + selector.getColumn() + ") " + exception.getMessage());
        }
    }

    public void visit(True tr) {
        try {
            operands.push(Boolean.valueOf(true));
        } catch (ValException exception) {
            throw new RuntimeException(" (" + tr.getLine() + ", " + tr.getColumn() + ") " + exception.getMessage());
        }
    }
    
    public void visit(Type t) { }
    
    public void visit(TyBool t) { }
		
    public void visit(TyChar t) { }

    public void visit(TyFloat t) { }

    public void visit(TyID t) { }

    public void visit(TyInt t) { }
}