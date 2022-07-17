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
	private Stack<Object> ops;
    private HashMap<String, Func> funcs;
	private HashMap<String, Data> types;
    private ArrayList<Object> returns;
    private boolean retMode, debug;

    public InterpretVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        ops = new Stack<Object>();
        funcs = new HashMap<String, Func>();
		types = new HashMap<String, Data>();
        returns = new ArrayList<Object>();
        retMode = false;
        debug = false;
    }

    public InterpretVisitor(boolean e) {
        this();
        this.debug = e;
    }

    public void visit(And e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = ops.pop();
            left = ops.pop();
            ops.push(Boolean.valueOf((Boolean) left && (Boolean) right));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

	public void visit(Attr e) {
        try {
            Var var = e.getValue();
            e.getExpression().accept(this);
            Object val = ops.pop();
            Object obj = null;

            if (env.peek().containsKey(var.getId())) {
                obj = env.peek().get(var.getId());
            }

            ArrayList<Selector> selectors = var.getSelectors();

            if (!selectors.isEmpty()) {
                for (int k = 0; k < selectors.size() - 1; k++) {
                    selectors.get(k).accept(this);
                    Object select = ops.pop();
                    if (selectors.get(k) instanceof AccessData) {
                        obj = ((HashMap<String, Object>) obj).get(select);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) select);
                    }
                }
                selectors.get(selectors.size() - 1).accept(this);
                Object select = ops.pop();

                if (selectors.get(selectors.size() - 1) instanceof AccessData) {
                    ((HashMap<String, Object>) obj).put((String) select, val);
                } else {
                    ((ArrayList) obj).set((Integer) select, val);
                }
            } else {
                env.peek().put(var.getId(), val);
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

	public void visit(CallCmd e) {
        try {
            Func f = funcs.get(e.getName());
            if (f != null) {
                for (Exp exp : e.getExpressions()) {
                    exp.accept(this);
                }
                f.accept(this);
                if (e.getReturnable() != null) {
                    Var[] s = e.getReturnable();

                    for (int i = 0; i < s.length; i++) {
                        Object val = returns.get(i);
                        Var var = s[i];
                        Object obj = env.peek().get(var.getId());
                        ArrayList<Selector> selectors = var.getSelectors();

                        if (!selectors.isEmpty()) {
                            for (int k = 0; k < selectors.size() - 1; k++) {
                                selectors.get(k).accept(this);
                                Object select = ops.pop();
                                if (selectors.get(k) instanceof AccessData) {
                                    obj = ((HashMap<String, Object>) obj).get(select);
                                } else {
                                    obj = ((ArrayList) obj).get((Integer) select);
                                }
                            }
                            selectors.get(selectors.size() - 1).accept(this);
                            Object select = ops.pop();
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
                returns.clear();
            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") Undefined function " + e.getName());
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(CallExp e) {
        try {
            Func f = funcs.get(e.getName());
            if (f != null) {
                for (Exp exp : e.getExpressions()) {
                    exp.accept(this);
                }
                f.accept(this);

                e.getReturnable().accept(this);
                Integer pos = (Integer) ops.pop();
                Object result = returns.get(pos);
                ops.push(result);
                returns.clear();

            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") Função não existe " + e.getName());
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }

    }

    public void visit(Caracter e) {
        try {
            ops.push(e.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(StmtList e) {
        if (retMode) {
            return;
        }
        try {
            for (Cmd cmd : e.getList()) {
                cmd.accept(this);
                if (retMode) {
                    return;
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Data e) { }

    @Override
    public void visit(Decl e) { }
		
  	public void visit(Div e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = ops.pop();
            left = ops.pop();
            
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                ops.push((Integer) left / (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                ops.push((Float) left / (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                ops.push((Float) left / (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                ops.push((Integer) left / (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(FloatV e) {
        try {
            ops.push(Float.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Eq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            ops.push(Boolean.valueOf(ops.pop().equals(ops.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(False e) {
        try {
            ops.push(Boolean.valueOf(false));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(Func e) {
        HashMap<String, Object> lv = new HashMap<String, Object>();
        if (e.getParam() != null) {
            for (int i = e.getParam().length - 1; i >= 0; i--) {
                lv.put(e.getParam()[i].getID(), ops.pop());
            }
        }
        env.push(lv);

        for (Cmd cmd : e.getBody()) {
            if (retMode) {
                break;
            }
            cmd.accept(this);
        }

        if (debug && e.getID().equals("main")) {

            Object[] obj = env.peek().keySet().toArray();
            System.out.println("-------------- Memoria ----------------");

            for (int i = 0; i < obj.length; i++) {
                System.out.print(((String) obj[i]) + " : " + env.peek().get(obj[i]).toString());
            }

        }
        env.pop();
        retMode = false;
    }

    public void visit(If e) {
        try {
            e.getExpression().accept(this);
            if ((Boolean) ops.pop()) {
                e.getThe().accept(this);
            } else if (e.getEls() != null) {
                e.getEls().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Int e) {
        try {
            ops.push(Integer.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Iterate e) {
        try {
            e.getExpression().accept(this);
            Object obj = ops.pop();
            Object objClass = obj.getClass();
            if (objClass == Integer.class) {

                for (int i = 0; i < (Integer) obj; i++) {
                    e.getBody().accept(this);
                    e.getExpression().accept(this);
                }
            } else {
                throw new RuntimeException("Dados se diferenciam");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
	public void visit(Less e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = ops.pop();
            left = ops.pop();

            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                ops.push((Integer) left < (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                ops.push((Float) left < (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                ops.push((Float) left < (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                ops.push((Integer) left < (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
						
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(Var e) {
        try {
            if (env.peek().containsKey(e.getId())) {
                Object obj = env.peek().get(e.getId());
                if (e.getSelectors().size() != 0) {
                    for (Selector lv : e.getSelectors()) {
                        lv.accept(this);
                        if (lv instanceof AccessData) {
                            obj = ((HashMap<String, Object>) obj).get((String) ops.pop());
                        } else if (lv instanceof AccessArray) {
                            obj = ((ArrayList) obj).get((Integer) ops.pop());
                        }
                    }
                }
                ops.push(obj);
            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") não declarado " + e.getId());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Minus e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = ops.pop();
            left = ops.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                ops.push((Integer) left - (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                ops.push((Float) left - (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                ops.push((Float) left - (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                ops.push((Integer) left - (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(CModule e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = ops.pop();
            left = ops.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                ops.push((Integer) left % (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                ops.push((Float) left % (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                ops.push((Float) left % (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                ops.push((Integer) left % (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Mult e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = ops.pop();
            left = ops.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                ops.push((Integer) left * (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                ops.push((Float) left * (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                ops.push((Float) left * (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                ops.push((Integer) left * (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(SMinus e) {
        try {
            e.getExpression().accept(this);
            Number exp;
            exp = (Number) ops.pop();
            if (exp.getClass() == Integer.class) {
                ops.push(-exp.intValue());
            } else if (exp.getClass() == Float.class) {
                ops.push(-exp.floatValue());
            } else {
                throw new RuntimeException("Inválido");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Neq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            ops.push(!ops.pop().equals(ops.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(New e) {
        try {
            Type type = e.getType();
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
            if (e.getExpression() != null) {
                e.getExpression().accept(this);
                t = (Integer) ops.pop();
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
                ops.push(val);
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
                    ops.push(val);
                } else {
                    if (idType) {
                        ops.push(a);
                    } else {
                        ops.push(null);
                    }
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Not e) {
        try {
            e.getExpression().accept(this);
            ops.push(!(Boolean) ops.pop());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Null e) {
        try {
            ops.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Param e) { }

    public void visit(Plus e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left, right;
            right = ops.pop();
            left = ops.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                ops.push((Integer) left + (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                ops.push((Float) left + (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                ops.push((Float) left + (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                ops.push((Integer) left + (Float) right);
			} else {
				throw new RuntimeException("Inválido");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Print e) {
        try {
            e.getExpression().accept(this);
            System.out.print(ops.pop());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Program e) {
        Func main = null;
        if (e.getDatas() != null) {
            for (Data data : e.getDatas()) {
                types.put(data.getId(), data);
            }
        }
        for (Func func : e.getFuncs()) {
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

    public void visit(Read e) {
        try {
            Scanner scan = new Scanner(System.in);
            Object Input = scan.nextLine();
            Var var = e.getValue();
            if (!var.getSelectors().isEmpty()) {
                Object obj = (Object) env.peek().get(var.getId());
                for (int k = 0; k < var.getSelectors().size() - 1; k++) {
                    var.getSelectors().get(k).accept(this);
                    Object s = ops.pop();
                    if (var.getSelectors().get(k) instanceof AccessData) {
                        obj = ((HashMap<String, Object>) obj).get(s);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) s);
                    }
                }
                var.getSelectors().get(var.getSelectors().size() - 1).accept(this);
                Object s = ops.pop();
                if (var.getSelectors().get(var.getSelectors().size() - 1) instanceof AccessData) {
                    ((HashMap<String, Object>) obj).put((String) s, Input);
                } else {
                    ((ArrayList) obj).set((Integer) s, Input);
                }
            } else {
                env.peek().put(e.getValue().getId(), Input);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Return e) {
        ArrayList<Object> aux = new ArrayList<Object>();
        for (Exp exp : e.getExpressions()) {
            exp.accept(this);
            aux.add(ops.pop());
        }
        for (int i = 0; i < aux.size(); i++) {
            returns.add(aux.get(i));
        }
        retMode = true;
    }

    public void visit(AccessArray e) {
        try {
            Exp exp = e.getIndex();
            exp.accept(this);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(AccessData e) {
        try {
            ops.push(e.getIndex());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(True e) {
        try {
            ops.push(Boolean.valueOf(true));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(Type t) { }
    
    public void visit(TyBool t) { }
		
    public void visit(TyChar t) { }

    public void visit(TyFloat t) { }

    public void visit(TyID t) { }

    public void visit(TyInt t) { }
}