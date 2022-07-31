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
import lang.type.*;

public class InterpretVisitor extends Visitor{

  	private HashMap<String, SType> env;
    private HashMap<String, ArrayList<STypeFunc>> funcs;
	private Stack<Object> operands;
    private boolean retMode, debug;
	private HashMap<String, Data> types;
    private ArrayList<Object> returns;
	
	private Stack<SType> stck;
	
	private STypeInt  typeInt;
	private STypeFunc tempFunc;
	private STypeVar typeVar;
	private STypeNull typeNull;
	private STypeBool typeBool;
	private STypeFloat typeFloat;

    public InterpretVisitor() {
        env = new HashMap<String, SType>();
        //env.push(new HashMap<String, SType>());
        funcs = new HashMap<String, ArrayList<STypeFunc>>();
        operands = new Stack<Object>();
        retMode = false;
        debug = false;
		types = new HashMap<String, Data>();
        returns = new ArrayList<Object>();
		
		typeInt = STypeInt.newSTypeInt();
		stck = new Stack<SType>();
		typeVar = STypeVar.newSTypeVar();
		tempFunc = null;
		typeNull = STypeNull.newSTypeNull();
		typeBool = STypeBool.newSTypeBool();
		typeFloat = STypeFloat.newSTypeFloat();
    }

    public InterpretVisitor(boolean debug) {
        this();
        this.debug = debug;
    }

    public void visit(Program p) {
        Func main = null;
        if (p.getDatas() != null) {
            for (Data data : p.getDatas()) {
                types.put(data.getId(), data);
            }
        }
        for (Func func : p.getFuncs()) {
            funcs.put(func.getID(), funcs.get(func.getID()));
            if (func.getID().equals("main")) {
                main = func;
            }
        }
        if (main == null) {
            throw new RuntimeException("There is no main function! Aborted!");
        }
        main.accept(this);
    }

    public void visit(Data e) { }

    @Override
    public void visit(Decl e) { }

    public void visit(AccessData e) {
        try {
            operands.push(e.getIndex());
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

    public void visit(CallCmd e) {
        try {
            if (funcs.get(e.getName()) != null) {
				ArrayList<STypeFunc> funcNames = funcs.get(e.getName());
				SType[] param = null;
				if(e.getExpressions() != null){
				   param = new SType[e.getExpressions().length];	
				   int i = 0;
				   for (Expr exp : e.getExpressions()) {
						exp.accept(this);
						param[i] = stck.pop();
						i++;
					}
				}
                STypeFunc aux = selectFunc(funcNames, param); 
                //funcs.get(e.getName()).accept(this);
				if(aux != null){
					if (e.getReturn() != null){
						if (aux.getReturnable() != null) {
							SType[] Cmdreturn = new SType[e.getReturn().length];
							int i = 0;
							for (LValue s : e.getReturn()){
								s.accept(this);
								Cmdreturn[i] = stck.pop();
								i++;
							}
							if (compareTypes(Cmdreturn, aux.getReturnable())){
								i = 0;
								for (LValue s : e.getReturn()){
									if (Cmdreturn[i].match(typeVar)){
										if (aux.getReturnable()[i] instanceof STypeNull){
										    throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Invalid return!"); 	
										} else {
											env.put(s.getId(), aux.getReturnable()[i]);
										}
										
									}
									i++;
								}								
							} else {
								throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Invalid variables!!");	
							}
						} else {
							throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Has no return!");	
						}
					}
				} else {
					throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Invalid parameters!");
				}
                /*if (e.getReturn() != null) {
                    LValue[] s = e.getReturn();
                    for (int i = 0; i < s.length; i++) {
                        LValue lvalue = s[i];
                        Object obj = env.peek().get(lvalue.getId());
                        if (!lvalue.getAccess().isEmpty()) {
                            for (int k = 0; k < lvalue.getAccess().size() - 1; k++) {
                                lvalue.getAccess().get(k).accept(this);
                                Object select = operands.pop();
                                if (lvalue.getAccess().get(k) instanceof AccessData) {
                                    obj = ((HashMap<String, Object>) obj).get(select);
                                } else {
                                    obj = ((ArrayList) obj).get((Integer) select);
                                }
                            }
                            lvalue.getAccess().get(lvalue.getAccess().size() - 1).accept(this);
                            Object select = operands.pop();
                            if (lvalue.getAccess().get(lvalue.getAccess().size() - 1) instanceof AccessData) {
                                ((HashMap<String, Object>) obj).put((String) select, returns.get(i));
                            } else {
                                ((ArrayList) obj).set((Integer) select, returns.get(i));
                            }
                        } else {
                            env.peek().put(lvalue.getId(), returns.get(i));
                        }
                    }
                }
                returns.clear();*/
            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") Undefined function " + e.getName());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(CallExpr e) {
        try {
            if (funcs.containsKey(e.getName())) {
				ArrayList<STypeFunc> lista = funcs.get(e.getName());
				SType[] param = null;
				if(e.getExpressions() != null){
					param = new SType[e.getExpressions().length];
					int i = 0;
					for (Expr exp : e.getExpressions()) {
						exp.accept(this);
						param[i] = stck.pop();
						i++;
					}
				}
				STypeFunc auxFunc = selectFunc(lista, param);
				if (auxFunc != null){
					e.getReturn().accept(this);
					if (stck.pop().match(typeInt)){
						if (auxFunc.getReturnable() != null){
							if (e.getReturn() instanceof Int){
								Int inter = (Int) e.getReturn();
								int j = inter.getValue();
								if (auxFunc.getReturnable().length > j && j >= 0){
									stck.push(auxFunc.getReturnable()[j]);
								} else {
									throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Non-existent return!");
								}
							} else {
								throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Only whole numbers will be accepted!");
							}
						} else {
							throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": No return function!");
						}
					} else {
						throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Expected INT type!");
					}
				} else {
					throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Function with different parameters!");
				}
                /*funcs.get(e.getName()).accept(this);
                e.getReturn().accept(this);
                operands.push(returns.get((Integer) operands.pop()));
                returns.clear();*/
            } else {
                throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") Undefined function " + e.getName());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

	public void visit(Attr e) {
        try {
            LValue lvalue = e.getValue();
            lvalue.accept(this);
			SType auxLvalue = stck.pop();
			e.getExpression().accept(this);
			SType auxExpression = stck.pop();
			if (!auxExpression.match(typeVar)){
				if (auxLvalue.match(typeVar)){
					if (auxExpression instanceof STypeNull) {
						throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Invalid expression!");
					} else {
						env.put(e.getValue().getId(), auxExpression);
					}
				} else {
					if (!auxLvalue.match(auxExpression)){
					   throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Invalid type!");
					}
				}
			} else {
				throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Undefined variable!");				
			}
            /*Object val = operands.pop();
            Object obj = null;
            if (env.peek().containsKey(lvalue.getId())) {
                obj = env.peek().get(lvalue.getId());
            }
            if (!lvalue.getAccess().isEmpty()) {
                for (int k = 0; k < lvalue.getAccess().size() - 1; k++) {
                    lvalue.getAccess().get(k).accept(this);
                    Object select = operands.pop();
                    if (lvalue.getAccess().get(k) instanceof AccessData) {
                        obj = ((HashMap<String, Object>) obj).get(select);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) select);
                    }
                }
                lvalue.getAccess().get(lvalue.getAccess().size() - 1).accept(this);
                Object select = operands.pop();
                if (lvalue.getAccess().get(lvalue.getAccess().size() - 1) instanceof AccessData) {
                    ((HashMap<String, Object>) obj).put((String) select, val);
                } else {
                    ((ArrayList) obj).set((Integer) select, val);
                }
            } else {
                //env.peek().put(lvalue.getId(), val);
            }*/
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(Func e) {
        //HashMap<String, SType> lv = new HashMap<String, SType>();
        
		if (e.getParam() != null) {
            for (int i = e.getParam().length - 1; i >= 0; i--) {
				if (env.containsKey(e.getParam()[i].getID())){
					throw new RuntimeException(e.getLine() + ", " + e.getColumn() +  ": Parameters with the same identifiers!");
				} else {
					env.put(e.getParam()[i].getID(), tempFunc.getParameter()[i]);
				}          
            }
        }
        //env.push(lv);
		if (e.getBody() != null){
			for (Cmd cmd : e.getBody()) {
				if (retMode) {
					break;
				}
				cmd.accept(this);
			}
		}
		
		if (e.getTypeReturn() != null){
			if (!consultReturn(e.getBody())){
				throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Invalid return!");
			}
		}

        /*if (debug && e.getID().equals("main")) {
            Object[] obj = env.peek().keySet().toArray();
            System.out.println("-------------- Memoria ----------------");

            for (int i = 0; i < obj.length; i++) {
                System.out.print(((String) obj[i]) + " : " + env.peek().get(obj[i]).toString());
            }
        }
        env.pop();
        retMode = false;*/
    }

    public void visit(Param e) { }

    public void visit(Print e) {
        try {
            e.getExpression().accept(this);
            System.out.print(operands.pop());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Read e) {
        try {
            Scanner scan = new Scanner(System.in);
            Object Input = scan.nextLine();
            if (!e.getValue().getAccess().isEmpty()) {
                //Object obj = (Object) env.peek().get(e.getValue().getId());
                for (int k = 0; k < e.getValue().getAccess().size() - 1; k++) {
                    e.getValue().getAccess().get(k).accept(this);
                    Object s = operands.pop();
                    if (e.getValue().getAccess().get(k) instanceof AccessData) {
                       // obj = ((HashMap<String, Object>) obj).get(s);
                    } else {
                       // obj = ((ArrayList) obj).get((Integer) s);
                    }
                }
                e.getValue().getAccess().get(e.getValue().getAccess().size() - 1).accept(this);
                Object s = operands.pop();
                if (e.getValue().getAccess().get(e.getValue().getAccess().size() - 1) instanceof AccessData) {
                    //((HashMap<String, Object>) obj).put((String) s, Input);
                } else {
                    //((ArrayList) obj).set((Integer) s, Input);
                }
            } else {
                //env.peek().put(e.getValue().getId(), Input);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Return e) {
        ArrayList<Object> aux = new ArrayList<Object>();
        for (Expr exp : e.getExpressions()) {
            exp.accept(this);
            aux.add(operands.pop());
        }
        for (int i = 0; i < aux.size(); i++) {
            returns.add(aux.get(i));
        }
        retMode = true;
    }
    
    public void visit(LValue e) {
        try {
            if (env.containsKey(e.getId())) {
                //Object obj = env.peek().get(e.getId());
				stck.push(env.get(e.getId())); 
                if (e.getAccess().size() != 0) {
                    for (Access lv : e.getAccess()) {
                        lv.accept(this);
                        /*if (lv instanceof AccessData) {
                            obj = ((HashMap<String, Object>) obj).get((String) operands.pop());
                        } else if (lv instanceof Array) {
                            obj = ((ArrayList) obj).get((Integer) operands.pop());
                        }*/
                    }
                }
                //operands.push(obj);
            } else {
				if (e.getAccess().size() == 0){
					stck.push(typeVar);
				} else {
					throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") not declared" + e.getId());	
				}               
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Int e) {
        try {
            stck.push(typeInt);
			//operands.push(Integer.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(FloatV e) {
        try {
            operands.push(Float.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Char e) {
        try {
            operands.push(e.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Iterate e) {
        try {
            e.getExpression().accept(this);
			if (stck.pop().match(typeInt)){
				e.getBody().accept(this);
			}
            /*Object obj = operands.pop();
            Object objClass = obj.getClass();
            if (objClass == Integer.class) {

                for (int i = 0; i < (Integer) obj; i++) {
                    e.getBody().accept(this);
                    e.getExpression().accept(this);
                }
            }*/ else {
                throw new RuntimeException("Data differ");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(New e) {
        try {
            BType bType = e.getType().getBtype();
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
                for (Decl declaration : data.getTypes()) {
                    a.put(declaration.getId(), null);
                }
            }
            if (e.getExpression() != null) {
                e.getExpression().accept(this);
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
                for (int i = 0; i < e.getType().getBraces(); i++) {
                    aux = new ArrayList<Object>();
                    aux.add(val);
                    val = aux;
                }
                operands.push(val);
            } else {
                if (e.getType().getBraces() != 0) {
                    val = new ArrayList<Object>();
                    if (idType) {
                        val.add(a);
                    } else {
                        val.add(null);
                    }
                    aux = null;
                    for (int i = 0; i < e.getType().getBraces() - 1; i++) {
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
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Array e) {
        try {
            e.getIndex().accept(this);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(CModule e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            SType right = stck.pop();
            SType left = stck.pop();
			if (left.match(typeInt) && right.match(typeInt)){
				stck.push(typeInt);
			}
            /*if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left % (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left % (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left % (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left % (Float) right);
			}*/ else {
				throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Div e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object right = operands.pop();
            Object left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left / (Integer) right);
            } else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left / (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left / (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left / (Float) right);
			} else {
			    throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Mult e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            SType right = stck.pop();
            SType left = stck.pop();
			if (left.match(typeInt) && right.match(typeInt)) {
				stck.push(typeInt);
			} else if (left.match(typeFloat) && right.match(typeFloat)) {
				stck.push(typeFloat);
			} else if (left.match(typeInt) && right.match(typeFloat)) {
				stck.push(typeFloat);
			} else if (left.match(typeFloat) && right.match(typeInt)) {
				stck.push(typeFloat);
			}
            /*if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left * (Integer) right);
            } else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left * (Float) right);
            } else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left * (Integer) right);
            } else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left * (Float) right);
            }*/ else {
                throw new RuntimeException("Invalid");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Add e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object right = operands.pop();
            Object left = operands.pop();
            if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left + (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left + (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left + (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left + (Float) right);
			} else {
				throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Sub e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
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
				throw new RuntimeException("Invalid");
			}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
    public void visit(SMinus e) {
        try {
            e.getExpression().accept(this);
            //Number exp = (Number) operands.pop();
			SType type = stck.pop();
			if (type.match(typeInt)) {
				stck.push(typeInt);
			} else if (type.match(typeFloat)) {
				stck.push(typeFloat);
			}
            /*if (exp.getClass() == Integer.class) {
                operands.push(-exp.intValue());
            } else if (exp.getClass() == Float.class) {
                operands.push(-exp.floatValue());
            }*/ else {
                throw new RuntimeException("Invalid");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Not e) {
        try {
            e.getExpression().accept(this);
            operands.push(!(Boolean) operands.pop());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Neq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
			SType right = stck.pop();
            SType left = stck.pop();
			if (left.match(right)){
				stck.push(typeBool);
			} else {
				throw new RuntimeException(e.getLine() + ", " + e.getColumn() + ": Invalid type!");
			} 
            //operands.push(!operands.pop().equals(operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(And e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object left = operands.pop();
            Object right = operands.pop();
            operands.push(Boolean.valueOf((Boolean) left && (Boolean) right));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Eq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            operands.push(Boolean.valueOf(operands.pop().equals(operands.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
	// Voltar 1
	public void visit(Lt e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            SType right = stck.pop();
            SType left = stck.pop();
            if(left.match(typeInt) && right.match(typeInt)){
				stck.push(typeBool);
			} else if (left.match(typeFloat) && right.match(typeFloat)){
				stck.push(typeBool);
			} else if(left.match(typeFloat) && right.match(typeInt)){
				stck.push(typeBool);
			} else if(left.match(typeInt) && right.match(typeFloat)){
				stck.push(typeBool);
			}
			/*if (left.getClass() == Integer.class && right.getClass() == Integer.class) {
                operands.push((Integer) left < (Integer) right);
			} else if (left.getClass() == Float.class && right.getClass() == Float.class) { 
                operands.push((Float) left < (Float) right);
			} else if (left.getClass() == Float.class && right.getClass() == Integer.class) {
                operands.push((Float) left < (Integer) right);
			} else if (left.getClass() == Integer.class && right.getClass() == Float.class) {
                operands.push((Integer) left < (Float) right);
			}*/ else {
				throw new RuntimeException("Invalid");
			}	
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(If e) {
        try {
            e.getExpression().accept(this);
            if (stck.pop().match(typeBool)) {
                e.getThen().accept(this);
            } else if (e.getElse() != null) {
                e.getElse().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(False e) {
        try {
            operands.push(Boolean.valueOf(false));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(True e) {
        try {
            operands.push(Boolean.valueOf(true));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Null e) {
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
    
	
	private STypeFunc selectFunc(ArrayList<STypeFunc> listFunc, SType[] params) {
		boolean equals = false;
		SType type1, type2;
		STypeArray typeArray1, typeArray2;
		STypeFunc fnc = null;

		for (int i = 0; i < listFunc.size(); i++) {
		  SType[] stypes = listFunc.get(i).getParameter();

		  if (params == null && stypes == null) {
			fnc = listFunc.get(i);
			return fnc;
		  }
		  if (stypes.length == params.length) {
			equals = true;
			for (int j = 0; j < stypes.length; j++) {
			  type1 = stypes[j];
			  type2 = params[j];

			  while (type1 instanceof STypeArray && type2 instanceof STypeArray) {
				typeArray1 = (STypeArray) type1;
				typeArray2 = (STypeArray) type2;
				type1 = typeArray1.getArg();
				type2 = typeArray2.getArg();
			  }
			  if (!type1.match(type2)) {
				equals = false;
			  }
			}
		  }
		  if (equals) {
			fnc = listFunc.get(i);
			return fnc;
		  }
		}
		return fnc;
	}
	
	private boolean consultReturn(Cmd[] cmds) {
		boolean isResults = false;
		StmtList s = null;
		If auxIf = null;

		if (cmds != null) {
		  for (Cmd c : cmds) {
			if (c instanceof Return) {
			  isResults = true;
			}
			if (c instanceof StmtList) {
			  s = (StmtList) c;
			  isResults = consultReturn(s.getList());
			}
		  }

		  if (!isResults) {
			for (Cmd c : cmds) {
			  if (c instanceof If) {
				auxIf = (If) c;
				if (auxIf.getElse() != null) {
				  //flagResults = consultIfReturn(auxIf);
				}
			  }
			}
		  }
		}
		return isResults;
  }
  
  private boolean compareTypes(SType[] aux, SType[] param) {
    boolean equals;
    SType Taux, Tparam;
    STypeArray typeArray1, typeArray2;

    equals = false;
    if (aux.length == param.length) {
      equals = true;

      for (int j = 0; j < aux.length; j++) {
        Taux = aux[j];
        Tparam = param[j];

        while (Taux instanceof STypeArray && Tparam instanceof STypeArray) {
          typeArray1 = (STypeArray) Taux;
          typeArray2 = (STypeArray) Tparam;
          Taux = typeArray1.getArg();
          Tparam = typeArray2.getArg();
        }

        if (!(Taux.match(typeVar) || Tparam.match(typeVar))) {
          if (!Taux.match(Tparam)) {
            equals = false;
          }
        }
      }
    }
    return equals;
  }
	
    public void visit(Type t) { }
    
    public void visit(TyBool t) { }
		
    public void visit(TyChar t) { }

    public void visit(TyFloat t) { }

    public void visit(TyID t) { }

    public void visit(TyInt t) { }
}