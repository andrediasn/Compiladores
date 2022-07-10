/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.visitors;

import lang.ast.*;

import java.util.*;

public class VisitorInterpretador extends Visitor {
    private Stack<HashMap<String, Object>> env;
    private HashMap<String, Func> funcs;
    private Stack<Object> operands;
    private ArrayList<Object> retornos;
    private HashMap<String, Data> tipos;

    private boolean retMode, debug;

    public VisitorInterpretador() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Func>();
        operands = new Stack<Object>();
        retornos = new ArrayList<Object>();
        tipos = new HashMap<String, Data>();
        retMode = false;
        debug = false;
    }

    public VisitorInterpretador(boolean debug) {
        this();
        this.debug = debug;
    }

    public void visit(Program p) {
        Func mainFuncs = null;
        if (p.getDatas() != null) {
            for (Data d : p.getDatas()) {
                tipos.put(d.getId(), d);
            }
        }
        for (Func f : p.getFuncs()) {
            funcs.put(f.getID(), f);
            if (f.getID().equals("main")) {
                mainFuncs = f;
            }
        }
        if (mainFuncs == null) {
            throw new RuntimeException("Não há uma função chamada main ! abortando ! ");
        }
        mainFuncs.accept(this);
    }

    public void visit(Add e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            Object esqClass = esq.getClass();
            Object dirClass = dir.getClass();
            if (esqClass == Integer.class) {
                if (dirClass == Integer.class) {
                    operands.push((Integer) esq + (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Integer) esq + (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else if (esqClass == Float.class) {
                if (dirClass == Integer.class) {
                    operands.push((Float) esq + (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Float) esq + (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else {
                throw new RuntimeException("Tipos de dado inválidos.");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Sub e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            Object esqClass = esq.getClass();
            Object dirClass = dir.getClass();
            if (esqClass == Integer.class) {
                if (dirClass == Integer.class) {
                    operands.push((Integer) esq - (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Integer) esq - (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else if (esqClass == Float.class) {
                if (dirClass == Integer.class) {
                    operands.push((Float) esq - (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Float) esq - (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else {
                throw new RuntimeException("Tipos de dado inválidos.");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Mul e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            Object esqClass = esq.getClass();
            Object dirClass = dir.getClass();
            if (esqClass == Integer.class) {
                if (dirClass == Integer.class) {
                    operands.push((Integer) esq * (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Integer) esq * (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else if (esqClass == Float.class) {
                if (dirClass == Integer.class) {
                    operands.push((Float) esq * (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Float) esq * (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else {
                throw new RuntimeException("Tipos de dado inválidos.");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Div e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            Object esqClass = esq.getClass();
            Object dirClass = dir.getClass();
            if (esqClass == Integer.class) {
                if (dirClass == Integer.class) {
                    operands.push((Integer) esq / (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Integer) esq / (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else if (esqClass == Float.class) {
                if (dirClass == Integer.class) {
                    operands.push((Float) esq / (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Float) esq / (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else {
                throw new RuntimeException("Tipos de dado inválidos.");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Mod e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            Object esqClass = esq.getClass();
            Object dirClass = dir.getClass();
            if (esqClass == Integer.class) {
                if (dirClass == Integer.class) {
                    operands.push((Integer) esq % (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Integer) esq % (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else if (esqClass == Float.class) {
                if (dirClass == Integer.class) {
                    operands.push((Float) esq % (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Float) esq % (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else {
                throw new RuntimeException("Tipos de dado inválidos.");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(And e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push(Boolean.valueOf((Boolean) esq && (Boolean) dir));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Lt e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            Object esqClass = esq.getClass();
            Object dirClass = dir.getClass();
            if (esqClass == Integer.class) {
                if (dirClass == Integer.class) {
                    operands.push((Integer) esq < (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Integer) esq < (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else if (esqClass == Float.class) {
                if (dirClass == Integer.class) {
                    operands.push((Float) esq < (Integer) dir);
                } else if (dirClass == Float.class) {
                    operands.push((Float) esq < (Float) dir);
                } else {
                    throw new RuntimeException("Tipos de dado inválidos.");
                }
            } else {
                throw new RuntimeException("Tipos de dado inválidos.");
            }
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

    public void visit(Not e) {
        try {
            e.getExpr().accept(this);
            operands.push(!(Boolean) operands.pop());
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

    public void visit(False e) {
        try {
            operands.push(Boolean.valueOf(false));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(NInt e) {
        try {
            operands.push(Integer.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(NFloat e) {
        try {
            operands.push(Float.valueOf(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Call e) {
        try {
            Func f = funcs.get(e.getName());
            if (f != null) {
                for (Expr exp : e.getArgs()) {
                    exp.accept(this);
                }
                f.accept(this);
                if (e.getRet() != null) {
                    Lvalue[] s = e.getRet();
                    for (int i = 0; i < s.length; i++) {
                        Object val = retornos.get(i);
                        Lvalue var = s[i];
                        Object obj = env.peek().get(var.getId());
                        ArrayList<Seletor> seletores = var.getSeletores();
                        if (!seletores.isEmpty()) {

                            for (int k = 0; k < seletores.size() - 1; k++) {
                                seletores.get(k).accept(this);
                                Object select = operands.pop();
                                if (seletores.get(k) instanceof DataSeletor) {
                                    obj = ((HashMap<String, Object>) obj).get(select);
                                } else {
                                    obj = ((ArrayList) obj).get((Integer) select);
                                }
                            }
                            seletores.get(seletores.size() - 1).accept(this);
                            Object select = operands.pop();

                            if (seletores.get(seletores.size() - 1) instanceof DataSeletor) {
                                ((HashMap<String, Object>) obj).put((String) select, val);
                            } else {
                                ((ArrayList) obj).set((Integer) select, val);
                            }
                        } else {
                            env.peek().put(var.getId(), val);
                        }
                    }
                }
                retornos.clear();
            } else {
                throw new RuntimeException(
                        " (" + e.getLine() + ", " + e.getColumn() + ") Função não definida " + e.getName());
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Attr e) {
        try {
            Lvalue var = e.getID();
            e.getExp().accept(this);
            Object val = operands.pop();
            Object obj = null;
            if (env.peek().containsKey(var.getId())) {
                obj = env.peek().get(var.getId());
            }
            ArrayList<Seletor> seletores = var.getSeletores();
            if (!seletores.isEmpty()) {
                for (int k = 0; k < seletores.size() - 1; k++) {
                    seletores.get(k).accept(this);
                    Object select = operands.pop();
                    if (seletores.get(k) instanceof DataSeletor) {
                        obj = ((HashMap<String, Object>) obj).get(select);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) select);
                    }
                }
                seletores.get(seletores.size() - 1).accept(this);
                Object select = operands.pop();

                if (seletores.get(seletores.size() - 1) instanceof DataSeletor) {
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

    public void visit(If e) {
        try {
            e.getTeste().accept(this);
            if ((Boolean) operands.pop()) {
                e.getThen().accept(this);
            } else if (e.getElse() != null) {
                e.getElse().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Print e) {
        try {
            e.getExpr().accept(this);
            System.out.println(operands.pop());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Func f) {
        HashMap<String, Object> localEnv = new HashMap<String, Object>();
        if (f.getParams() != null) {
            for (int i = f.getParams().length - 1; i >= 0; i--) {
                localEnv.put(f.getParams()[i].getID(), operands.pop());
            }
        }
        env.push(localEnv);
        for (Cmd c : f.getBody()) {
            if (retMode) {
                break;
            }
            c.accept(this);
        }
        if (debug && f.getID().equals("main")) {

            Object[] x = env.peek().keySet().toArray();
            System.out.println("-------------- Memoria ----------------");
            for (int i = 0; i < x.length; i++) {
                System.out.println(((String) x[i]) + " : " + env.peek().get(x[i]).toString());
            }
        }
        env.pop();
        retMode = false;
    }

    public void visit(Data d) {

    }

    public void visit(Return e) {
        ArrayList<Object> aux = new ArrayList<Object>();
        for (Expr expr : e.getExpr()) {
            expr.accept(this);
            aux.add(operands.pop());
        }
        for (int i = 0; i < aux.size(); i++) {
            retornos.add(aux.get(i));
        }
        retMode = true;
    }

    public void visit(Param e) {
    }

    public void visit(TyInt t) {
    }

    public void visit(TyFloat t) {
    }

    public void visit(TyBool t) {
    }

    public void visit(TyChar t) {
    }

    public void visit(TyID t) {
    }

    public void visit(ArraySeletor e) {
        try {
            Expr ex = e.getIndice();
            ex.accept(this);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(CallExpr e) {
        try {
            Func f = funcs.get(e.getName());
            if (f != null) {
                for (Expr exp : e.getArgs()) {
                    exp.accept(this);
                }
                f.accept(this);

                e.getRet().accept(this);
                Integer pos = (Integer) operands.pop();
                Object result = retornos.get(pos);
                operands.push(result);
                retornos.clear();
            } else {
                throw new RuntimeException(
                        " (" + e.getLine() + ", " + e.getColumn() + ") Função não existe " + e.getName());
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }

    }

    public void visit(CmdList e) {
        if (retMode) {
            return;
        }
        try {
            for (Cmd c : e.getList()) {
                c.accept(this);
                if (retMode) {
                    return;
                }
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Iterate e) {
        try {
            e.getTeste().accept(this);
            Object obj = operands.pop();
            Object objClass = obj.getClass();
            if (objClass == Integer.class) {
                for (int i = 0; i < (Integer) obj; i++) {
                    e.getBody().accept(this);
                    e.getTeste().accept(this);
                }
            } else {
                throw new RuntimeException("Tipos de dado da avaliacao diferente de inteiro.");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(LChar e) {
        try {
            operands.push(e.getValue());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Lvalue e) {
        try {
            if (env.peek().containsKey(e.getId())) {
                Object obj = env.peek().get(e.getId());
                if (e.getSeletores().size() != 0) {
                    for (Seletor lv : e.getSeletores()) {
                        lv.accept(this);
                        if (lv instanceof DataSeletor) {
                            obj = ((HashMap<String, Object>) obj).get((String) operands.pop());
                        } else if (lv instanceof ArraySeletor) {
                            obj = ((ArrayList) obj).get((Integer) operands.pop());
                        }
                    }

                }
                operands.push(obj);
            } else {
                throw new RuntimeException(
                        " (" + e.getLine() + ", " + e.getColumn() + ") variável não declarada " + e.getId());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Neg e) {
        try {
            e.getExpr().accept(this);
            Number exp;
            exp = (Number) operands.pop();
            if (exp.getClass() == Integer.class) {
                operands.push(-exp.intValue());
            } else if (exp.getClass() == Float.class) {
                operands.push(-exp.floatValue());
            } else {
                throw new RuntimeException("Tipos de dado inválidos.");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(NEq e) {
        try {
            e.getLeft().accept(this);
            e.getRight().accept(this);

            operands.push(!operands.pop().equals(operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(New e) {
        try {
            Tipo t = e.getTipo();
            Btype b = t.getBtype();

            Integer tam = 0;
            ArrayList<Object> val = null;
            ArrayList<Object> aux = null;
            boolean idTipo = false;
            HashMap<String, Object> au = null;

            if (b instanceof TyID) {
                idTipo = true;
                TyID a = (TyID) b;
                au = new HashMap<>();
                Data d = tipos.get(a.getIdTipo());
                for (Decl decl : d.getDecls()) {
                    au.put(decl.getId(), null);
                }
            }

            if (e.getExp() != null) {
                e.getExp().accept(this);
                tam = (Integer) operands.pop();

                if (idTipo) {
                    val = new ArrayList<Object>(tam);
                    for (int i = 0; i < tam; i++) {
                        val.add(au);
                    }
                } else {
                    val = new ArrayList<Object>(tam);
                    for (int i = 0; i < tam; i++) {
                        val.add(null);
                    }
                }
                aux = null;
                for (int i = 0; i < t.getColchetes(); i++) {
                    aux = new ArrayList<Object>();
                    aux.add(val);
                    val = aux;
                }
                operands.push(val);

            } else {
                if (t.getColchetes() != 0) {
                    val = new ArrayList<Object>();
                    if (idTipo) {
                        val.add(au);
                    } else {
                        val.add(null);
                    }
                    aux = null;
                    for (int i = 0; i < t.getColchetes() - 1; i++) {
                        aux = new ArrayList<Object>();
                        aux.add(val);
                        val = aux;
                    }
                    operands.push(val);
                } else {
                    if (idTipo) {
                        operands.push(au);
                    } else {
                        operands.push(null);
                    }
                }
            }
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

    public void visit(Read e) {
        try {
            Scanner scan = new Scanner(System.in);
            Object Input = scan.nextLine();

            Lvalue v = e.getVar();
            if (!v.getSeletores().isEmpty()) {
                Object obj = (Object) env.peek().get(v.getId());

                for (int k = 0; k < v.getSeletores().size() - 1; k++) {
                    v.getSeletores().get(k).accept(this);
                    Object select = operands.pop();
                    if (v.getSeletores().get(k) instanceof DataSeletor) {
                        obj = ((HashMap<String, Object>) obj).get(select);
                    } else {
                        obj = ((ArrayList) obj).get((Integer) select);
                    }
                }
                v.getSeletores().get(v.getSeletores().size() - 1).accept(this);
                Object select = operands.pop();

                if (v.getSeletores().get(v.getSeletores().size() - 1) instanceof DataSeletor) {
                    ((HashMap<String, Object>) obj).put((String) select, Input);
                } else {
                    ((ArrayList) obj).set((Integer) select, Input);
                }

            } else {
                env.peek().put(e.getVar().getId(), Input);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }

    }

    public void visit(Tipo e) {

    }

    public void visit(DataSeletor e) {
        try {
            operands.push(e.getId());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    @Override
    public void visit(Decl e) {

    }

}
