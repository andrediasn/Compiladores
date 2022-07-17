/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.visitor;

import java.util.*;
import lang.ast.*;
import lang.type.*;

public class VisitorSemantic extends Visitor {

  // Inicialização dos tipos semánticos criados
  private STypeInt typeInt;
  private STypeFloat typeFloat;
  private STypeBool typeBool;
  private STypeChar typeChar;
  private STypeNull typeNull;
  private STypeVar typeVar;

  private Stack<SType> stk;

  private STypeFunc tempFunc;
  private HashMap<String, SType> env;
  private HashMap<String, ArrayList<STypeFunc>> funcs;
  private HashMap<String, STypeData> typesData;

  public VisitorSemantic() {
    typeInt = STypeInt.newSTypeInt();
    typeFloat = STypeFloat.newSTypeFloat();
    typeBool = STypeBool.newSTypeBool();
    typeChar = STypeChar.newSTypeChar();
    typeNull = STypeNull.newSTypeNull();
    typeVar = STypeVar.newSTypeVar();

    stk = new Stack<SType>();

    tempFunc = null;
    env = new HashMap<String, SType>();
    funcs = new HashMap<String, ArrayList<STypeFunc>>();
    typesData = new HashMap<String, STypeData>();
  }

  @Override
  public void visit(And and) {
    and.getLeft().accept(this);
    and.getRight().accept(this);
    SType left, right;
    right = stk.pop();
    left = stk.pop();
    if (left.match(typeBool) && right.match(typeBool)) stk.push(
      typeBool
    ); else throw new RuntimeException(
      and.getLine() +
      ", " +
      and.getColumn() +
      ": Operacao And nao compativel com os tipos usados!"
    );
  }

  @Override
  public void visit(Assign assign) {
    assign.getValue().accept(this);
    SType auxValue = stk.pop();
    assign.getExpression().accept(this);
    SType auxExpression = stk.pop();

    if (!auxExpression.match(typeVar)) {
      if (auxValue.match(typeVar)) {
        if (auxExpression instanceof STypeNull) {
          throw new RuntimeException(
            assign.getLine() +
            ", " +
            assign.getColumn() +
            ": Expressao nula para variavel!"
          );
        } else {
          env.put(assign.getValue().getId(), auxExpression);
        }
      } else {
        if (!auxValue.match(auxExpression)) {
          throw new RuntimeException(
            assign.getLine() +
            ", " +
            assign.getColumn() +
            ": Variavel nao é do tipo da expressao!"
          );
        }
      }
    } else {
      throw new RuntimeException(
        assign.getLine() +
        ", " +
        assign.getColumn() +
        ": Variavel da expressao nao foi declarada!"
      );
    }
  }

  @Override
  public void visit(CallCmd callCmd) {
    if (funcs.containsKey(callCmd.getName())) {
      ArrayList<STypeFunc> funcNames = funcs.get(callCmd.getName());
      Exp[] parExpressions = callCmd.getExpressions();
      SType[] parameters = null;

      if (parExpressions != null) {
        parameters = new SType[parExpressions.length];
        int i = 0;
        for (Exp expression : parExpressions) {
          expression.accept(this);
          parameters[i] = stk.pop();
          i++;
        }
      }
      STypeFunc aux = selectFunc(funcNames, parameters);
      if (aux != null) {
        if (callCmd.getReturnable() != null) {
          if (aux.getReturnable() != null) {
            SType[] returnables = new SType[callCmd.getReturnable().length];
            int i = 0;
            for (Lvalue lValue : callCmd.getReturnable()) {
              lValue.accept(this);
              returnables[i] = stk.pop();
              i++;
            }
            if (compareTypes(returnables, aux.getReturnable())) {
              i = 0;
              for (Lvalue lValue : callCmd.getReturnable()) {
                if (returnables[i].match(typeVar)) {
                  if (aux.getReturnable()[i] instanceof STypeNull) {
                    throw new RuntimeException(
                      callCmd.getLine() +
                      ", " +
                      callCmd.getColumn() +
                      ": Retorno inválido (Null)!"
                    );
                  } else {
                    env.put(lValue.getId(), aux.getReturnable()[i]);
                  }
                }
                i++;
              }
            } else {
              throw new RuntimeException(
                callCmd.getLine() +
                ", " +
                callCmd.getColumn() +
                ": Tipos ou numero de variaveis invalidas!"
              );
            }
          } else {
            throw new RuntimeException(
              callCmd.getLine() +
              ", " +
              callCmd.getColumn() +
              ": Nao possui retorno!"
            );
          }
        }
      } else {
        throw new RuntimeException(
          callCmd.getLine() +
          ", " +
          callCmd.getColumn() +
          ": Parametros invalidos!"
        );
      }
    } else {
      throw new RuntimeException(
        callCmd.getLine() +
        ", " +
        callCmd.getColumn() +
        ": Funcao nao declarada!"
      );
    }
  }

  @Override
  public void visit(CallExp callExp) {
    if (funcs.containsKey(callExp.getName())) {
      ArrayList<STypeFunc> lista = funcs.get(callExp.getName());
      Exp[] par = callExp.getExpressions();
      SType[] param = null;
      if (par != null) {
        param = new SType[par.length];
        int i = 0;
        for (Exp p : par) {
          p.accept(this);
          param[i] = stk.pop();
          i++;
        }
      }
      STypeFunc auxFunc = selectFunc(lista, param);
      if (auxFunc != null) {
        callExp.getReturnable().accept(this);
        if (stk.pop().match(typeInt)) {
          if (auxFunc.getReturnable() != null) {
            if (callExp.getReturnable() instanceof IntegerT) {
              IntegerT inter = (IntegerT) callExp.getReturnable();
              int aux = inter.getValue();
              if (auxFunc.getReturnable().length > aux && aux >= 0) {
                stk.push(auxFunc.getReturnable()[aux]);
              } else {
                throw new RuntimeException(
                  callExp.getLine() +
                  ", " +
                  callExp.getColumn() +
                  ": Acesso a um retorno inexistente!"
                );
              }
            } else {
              throw new RuntimeException(
                callExp.getLine() +
                ", " +
                callExp.getColumn() +
                ": So aceita numeros inteiros no acesso ao retorno!"
              );
            }
          } else {
            throw new RuntimeException(
              callExp.getLine() +
              ", " +
              callExp.getColumn() +
              ": Funcao nao tem retorno!"
            );
          }
        } else {
          throw new RuntimeException(
            callExp.getLine() +
            ", " +
            callExp.getColumn() +
            ": Retorno precisa ser do tipo INT!"
          );
        }
      } else {
        throw new RuntimeException(
          callExp.getLine() +
          ", " +
          callExp.getColumn() +
          ": Funcao com tipos ou numero diferente de parametros!"
        );
      }
    } else {
      throw new RuntimeException(
        callExp.getLine() +
        ", " +
        callExp.getColumn() +
        ": Funcao nao declarada!"
      );
    }
  }

  @Override
  public void visit(Caracter caract) {
    stk.push(typeChar);
  }

  @Override
  public void visit(CmdArray cmdArray) {
    Cmd[] array = cmdArray.getArray();
    if (array != null) {
      for (Cmd c : array) {
        c.accept(this);
      }
    }
  }

  @Override
  public void visit(Data data) {}

  @Override
  public void visit(Decl decl) {}

  @Override
  public void visit(Div div) {
    div.getLeft().accept(this);
    div.getRight().accept(this);

    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (left.match(typeInt) && right.match(typeInt)) {
      stk.push(typeInt);
    } else if (left.match(typeFloat) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeInt) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeFloat) && right.match(typeInt)) {
      stk.push(typeFloat);
    } else {
      throw new RuntimeException(
        div.getLine() +
        ", " +
        div.getColumn() +
        ": Operacao de divisao invalida para os tipos usados!"
      );
    }
  }

  @Override
  public void visit(DoubleT doub) {
    stk.push(typeFloat);
  }

  @Override
  public void visit(Eq eq) {
    eq.getLeft().accept(this);
    eq.getRight().accept(this);

    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (right.match(left)) stk.push(typeBool); else throw new RuntimeException(
      eq.getLine() +
      ", " +
      eq.getColumn() +
      ": Operacao de igualdade invalida para os tipos usados!"
    );
  }

  @Override
  public void visit(False fal) {
    stk.push(typeBool);
  }

  @Override
  public void visit(Func func) {
    if (func.getParams() != null) {
      int i = 0;
      for (Params prm : func.getParams()) {
        if (env.containsKey(prm.getID())) {
          throw new RuntimeException(
            func.getLine() +
            ", " +
            func.getColumn() +
            ": Parametros com identificadores iguais"
          );
        } else {
          env.put(prm.getID(), tempFunc.getParameter()[i]);
        }
        i++;
      }
    }

    if (func.getTypeReturn() != null) {
      if (!consultReturn(func.getBody())) {
        throw new RuntimeException(
          func.getLine() +
          ", " +
          func.getColumn() +
          ": Funcao nao retorna o que foi declarado como retorno"
        );
      }
    }

    if (func.getBody() != null) {
      for (Cmd c : func.getBody()) {
        c.accept(this);
      }
    }
  }

  @Override
  public void visit(If i) {
    i.getExpression().accept(this);

    if (stk.pop().match(typeBool)) {
      i.getThe().accept(this);
      if (i.getEls() != null) {
        i.getEls().accept(this);
      }
    } else {
      throw new RuntimeException(
        i.getLine() + ", " + i.getColumn() + ": Operacao de If invalida"
      );
    }
  }

  @Override
  public void visit(IntegerT integer) {
    stk.push(typeInt);
  }

  @Override
  public void visit(Iterate ite) {
    ite.getExpression().accept(this);

    if (stk.pop().match(typeInt)) {
      ite.getBody().accept(this);
    } else {
      throw new RuntimeException(
        ite.getLine() +
        ", " +
        ite.getColumn() +
        ": O numero de execucao do interete deve ser do tipo INT"
      );
    }
  }

  @Override
  public void visit(Less less) {
    less.getLeft().accept(this);
    less.getRight().accept(this);
    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (
      (left.match(typeInt) || left.match(typeFloat)) &&
      (right.match(typeInt) || right.match(typeFloat))
    ) {
      stk.push(typeBool);
    } else {
      throw new RuntimeException(
        less.getLine() +
        ", " +
        less.getColumn() +
        ": Operacao de Less invalida para os tipos usados!"
      );
    }
  }

  @Override
  public void visit(Lvalue lValue) {
    if (env.containsKey(lValue.getId())) {
      stk.push(env.get(lValue.getId()));

      if (!lValue.getSelectors().isEmpty()) {
        for (Selector sltr : lValue.getSelectors()) {
          sltr.accept(this);
        }
      }
    } else {
      if (lValue.getSelectors().isEmpty()) {
        stk.push(typeVar);
      } else {
        throw new RuntimeException(
          lValue.getLine() +
          ", " +
          lValue.getColumn() +
          ": variavel array ou data nao declarado"
        );
      }
    }
  }

  @Override
  public void visit(Minus minus) {
    minus.getLeft().accept(this);
    minus.getRight().accept(this);

    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (left.match(typeInt) && right.match(typeInt)) {
      stk.push(typeInt);
    } else if (left.match(typeFloat) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeInt) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeFloat) && right.match(typeInt)) {
      stk.push(typeFloat);
    } else {
      throw new RuntimeException(
        minus.getLine() +
        ", " +
        minus.getColumn() +
        ": Operacao de subtracao invalida para os tipos usados!"
      );
    }
  }

  @Override
  public void visit(ModuleT module) {
    module.getLeft().accept(this);
    module.getRight().accept(this);

    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (left.match(typeInt) && right.match(typeInt)) {
      stk.push(typeInt);
    } else {
      throw new RuntimeException(
        module.getLine() +
        ", " +
        module.getColumn() +
        ": Operacao de mod invalida para os tipos usados!"
      );
    }
  }

  @Override
  public void visit(Mult mult) {
    mult.getLeft().accept(this);
    mult.getRight().accept(this);

    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (left.match(typeInt) && right.match(typeInt)) {
      stk.push(typeInt);
    } else if (left.match(typeFloat) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeInt) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeFloat) && right.match(typeInt)) {
      stk.push(typeFloat);
    } else {
      throw new RuntimeException(
        mult.getLine() +
        ", " +
        mult.getColumn() +
        ": Operaca de multiplicacao invalida para os tipos usados!"
      );
    }
  }

  @Override
  public void visit(Neg neg) {
    neg.getExpression().accept(this);
    SType type = stk.pop();

    if (type.match(typeInt)) {
      stk.push(typeInt);
    } else if (type.match(typeFloat)) {
      stk.push(typeFloat);
    } else {
      throw new RuntimeException(
        neg.getLine() + ", " + neg.getColumn() + ": Negacao invalida!"
      );
    }
  }

  @Override
  public void visit(Neq neq) {
    neq.getLeft().accept(this);
    neq.getRight().accept(this);

    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (left.match(right)) stk.push(typeBool); else throw new RuntimeException(
      neq.getLine() +
      ", " +
      neq.getColumn() +
      ": Operacao de NEQ invalida para os tipos usados!"
    );
  }

  @Override
  public void visit(New ne) {
    BType btype = ne.getType().getBtype();
    btype.accept(this);
    int braces = ne.getType().getBraces();
    int i;

    SType v = stk.pop();
    STypeArray vaux;

    if (ne.getExpression() != null) {
      ne.getExpression().accept(this);

      if (stk.pop().match(typeInt)) {
        braces++;
        for (i = 0; i < braces; i++) {
          vaux = new STypeArray(v);
          v = vaux;
        }

        stk.push(v);
      } else {
        throw new RuntimeException(
          ne.getLine() +
          ", " +
          ne.getColumn() +
          ": Vetor deve ter tamanho do tipo INT"
        );
      }
    } else {
      for (i = 0; i < braces; i++) {
        vaux = new STypeArray(v);
        v = vaux;
      }
      stk.push(v);
    }
  }

  @Override
  public void visit(Not not) {
    not.getExpression().accept(this);
    SType type = stk.pop();

    if (type.match(typeBool)) {
      stk.push(typeBool);
    } else {
      throw new RuntimeException(
        not.getLine() + ", " + not.getColumn() + ": Operacao NOT invalida!"
      );
    }
  }

  @Override
  public void visit(Null nu) {
    stk.push(typeNull);
  }

  @Override
  public void visit(Params params) {}

  @Override
  public void visit(Plus plus) {
    plus.getLeft().accept(this);
    plus.getRight().accept(this);

    SType left, right;
    right = stk.pop();
    left = stk.pop();

    if (left.match(typeInt) && right.match(typeInt)) {
      stk.push(typeInt);
    } else if (left.match(typeFloat) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeInt) && right.match(typeFloat)) {
      stk.push(typeFloat);
    } else if (left.match(typeFloat) && right.match(typeInt)) {
      stk.push(typeFloat);
    } else {
      throw new RuntimeException(
        plus.getLine() +
        ", " +
        plus.getColumn() +
        ": Operacao de adicao invalida para os tipos usados!"
      );
    }
  }

  @Override
  public void visit(Print pri) {
    try {
      pri.getExpression().accept(this);
      stk.pop();
    } catch (Exception e) {
      throw new RuntimeException(
        pri.getLine() + ", " + pri.getColumn() + ": PRINT nao foi possivel!"
      );
    }
  }

  @Override
  public void visit(Prog prog) {
    Func mainFuncs = null;
    ArrayList<STypeFunc> listFunc = null;

    if (prog.getDatas() != null) {
      for (Data d : prog.getDatas()) {
        STypeData dt = new STypeData(d.getId());

        for (Decl decl : d.getTypes()) {
          decl.getType().accept(this);

          if (dt.elem.containsKey(decl.getId())) {
            throw new RuntimeException(
              decl.getLine() +
              ", " +
              decl.getColumn() +
              ": Nome de elemento de data repetido!"
            );
          }
          dt.elem.put(decl.getId(), stk.pop());
        }
        if (typesData.containsKey(d.getId())) {
          throw new RuntimeException(
            d.getLine() + ", " + d.getColumn() + ": Data repetido!"
          );
        }
        typesData.put(d.getId(), dt);
      }
    }
    if (prog.getFuncs() != null) {
      for (Func f : prog.getFuncs()) {
        SType[] fnc = null;

        if (f.getID().equals("main")) {
          mainFuncs = f;
          if (mainFuncs.getParams() != null) {
            throw new RuntimeException(
              mainFuncs.getLine() +
              ", " +
              mainFuncs.getColumn() +
              ": Funcao main nao pode ter parametros!"
            );
          }
        }

        if (f.getParams() != null) {
          int tam = f.getParams().length;
          fnc = new SType[tam];
          for (int i = 0; i < tam; i++) {
            f.getParams()[i].getType().accept(this);
            fnc[i] = stk.pop();
          }
        }

        SType[] ret = null;
        SType auxValor = null;

        if (f.getTypeReturn() != null) {
          ret = new SType[f.getTypeReturn().length];
          int i = 0;

          for (Type ty : f.getTypeReturn()) {
            ty.accept(this);
            auxValor = stk.pop();
            ret[i] = auxValor;
            i++;
          }
        }
        STypeFunc aux = new STypeFunc(f.getID(), fnc, ret);
        if (funcs.containsKey(f.getID())) {
          listFunc = funcs.get(f.getID());
          if (paramsFunc(listFunc, fnc)) {
            throw new RuntimeException(
              f.getLine() + ", " + f.getColumn() + ": Funcao repetida!"
            );
          } else {
            listFunc.add(aux);
            funcs.put(f.getID(), listFunc);
          }
        } else {
          listFunc = new ArrayList<STypeFunc>();
          listFunc.add(aux);
          funcs.put(f.getID(), listFunc);
        }
      }

      for (Func fn : prog.getFuncs()) {
        env.clear();
        listFunc = funcs.get(fn.getID());
        SType[] params = null;

        if (fn.getParams() != null) {
          params = new SType[fn.getParams().length];

          for (int i = 0; i < fn.getParams().length; i++) {
            fn.getParams()[i].getType().accept(this);
            params[i] = stk.pop();
          }
        }
        tempFunc = selectFunc(listFunc, params);
        fn.accept(this);
      }
      if (mainFuncs == null) {
        throw new RuntimeException(
          prog.getLine() + ", " + prog.getColumn() + ": Nao existe funcao main!"
        );
      }
    }
  }

  @Override
  public void visit(Read read) {
    read.getValue().accept(this);
    SType tpe = stk.pop();

    if (!(tpe.match(typeInt))) {
      if (tpe.match(typeVar)) {
        env.put(read.getValue().getId(), typeInt);
      } else {
        throw new RuntimeException(
          read.getLine() +
          ", " +
          read.getColumn() +
          " Comando READ nao aceita o tipo usado!"
        );
      }
    }
  }

  @Override
  public void visit(Return ret) {
    boolean eqReturnables = true;

    if (tempFunc.getReturnable() != null) {
      if (ret.getExpressions().length == tempFunc.getReturnable().length) {
        Exp exp;
        Exp[] returnablesExp = new Exp[ret.getExpressions().length];
        returnablesExp = ret.getExpressions();

        SType[] typeAux = new SType[ret.getExpressions().length];

        for (int i = 0; i < returnablesExp.length; i++) {
          exp = returnablesExp[i];
          exp.accept(this);

          typeAux[i] = stk.pop();
        }

        for (int j = 0; j < typeAux.length; j++) {
          SType type1 = typeAux[j];
          SType type2 = tempFunc.getReturnable()[j];
          while (type1 instanceof STypeArray && type2 instanceof STypeArray) {
            STypeArray array1 = (STypeArray) type1;
            STypeArray array2 = (STypeArray) type2;
            type1 = array1.getArg();
            type2 = array2.getArg();
          }
          eqReturnables = (type1.match(type2)) ? true : false;
        }
        if (!eqReturnables) {
          throw new RuntimeException(
            ret.getLine() +
            ", " +
            ret.getColumn() +
            ": Tipos diferentes de retornos!"
          );
        }
      } else {
        throw new RuntimeException(
          ret.getLine() +
          ", " +
          ret.getColumn() +
          ": Numero diferentes de retornos!"
        );
      }
    } else {
      throw new RuntimeException(
        ret.getLine() + ", " + ret.getColumn() + ": Funcao nao possui retorno!"
      );
    }
  }

  @Override
  public void visit(SelectorArray selector) {
    SType selectorAux = stk.pop();
    selector.getIndex().accept(this);

    if (stk.pop().match(typeInt)) {
      if (selectorAux instanceof STypeArray) {
        STypeArray arrayAux = (STypeArray) selectorAux;
        selectorAux = arrayAux.getArg();
        stk.push(selectorAux);
      } else {
        throw new RuntimeException(
          selector.getLine() +
          ", " +
          selector.getColumn() +
          ": Acesso inválido em array!"
        );
      }
    } else {
      throw new RuntimeException(
        selector.getLine() +
        ", " +
        selector.getColumn() +
        ": Para acessar um array a expressão deve ser do tipo INT!"
      );
    }
  }

  @Override
  public void visit(SelectorData selector) {
    SType selectorAux = stk.pop();

    if (selectorAux instanceof STypeData) {
      STypeData dataAux = (STypeData) selectorAux;

      if (dataAux.elem.containsKey(selector.getIndex())) {
        selectorAux = dataAux.elem.get(selector.getIndex());
        stk.push(selectorAux);
      } else {
        throw new RuntimeException(
          selector.getLine() +
          ", " +
          selector.getColumn() +
          ": Acesso inválido de elemento!"
        );
      }
    } else {
      throw new RuntimeException(
        selector.getLine() +
        ", " +
        selector.getColumn() +
        ": Acesso invalido em data!"
      );
    }
  }

  @Override
  public void visit(True tr) {
    stk.push(typeBool);
  }

  @Override
  public void visit(Type type) {
    BType btype = type.getBtype();
    btype.accept(this);
    int braces = type.getBraces();

    SType v = stk.pop();
    STypeArray vaux;

    for (int i = 0; i < braces; i++) {
      vaux = new STypeArray(v);
      v = vaux;
    }
    stk.push(v);
  }

  @Override
  public void visit(TypeBool type) {
    stk.push(typeBool);
  }

  @Override
  public void visit(TypeChar type) {
    stk.push(typeChar);
  }

  @Override
  public void visit(TypeFloat type) {
    stk.push(typeFloat);
  }

  @Override
  public void visit(TypeInt type) {
    stk.push(typeInt);
  }

  @Override
  public void visit(TypeIDType type) {
    if (typesData.containsKey(type.getIdType())) {
      stk.push(typesData.get(type.getIdType()));
    } else {
      throw new RuntimeException(
        type.getLine() + ", " + type.getColumn() + ": Data inexistente!"
      );
    }
  }

  //--------------------------------------------
  //-------------FUNÇÕES AUXILIARES-------------
  //--------------------------------------------

  private boolean consultReturn(Cmd[] cmdList) {
    boolean flagResults = false;
    CmdArray cmdArray = null;
    If auxIf = null;

    if (cmdList != null) {
      for (Cmd c : cmdList) {
        if (c instanceof Return) {
          flagResults = true;
        }
        if (c instanceof CmdArray) {
          cmdArray = (CmdArray) c;
          flagResults = consultReturn(cmdArray.getArray());
        }
      }

      if (!flagResults) {
        for (Cmd c : cmdList) {
          if (c instanceof If) {
            auxIf = (If) c;
            if (auxIf.getEls() != null) {
              flagResults = consultIfReturn(auxIf);
            }
          }
        }
      }
    }
    return flagResults;
  }

  private boolean consultIfReturn(If commandIf) {
    boolean left, right;
    CmdArray cmdArray = null;
    If commandsIf = null;

    left = false;
    right = false;

    if (commandIf.getThe() instanceof Return) {
      left = true;
    } else {
      if (commandIf.getThe() instanceof CmdArray) {
        cmdArray = (CmdArray) commandIf.getThe();
        left = consultReturn(cmdArray.getArray());
      } else {
        if (commandIf.getThe() instanceof If) {
          commandsIf = (If) commandIf.getThe();
          if (commandsIf.getEls() != null) {
            left = consultIfReturn(commandsIf);
          }
        }
      }
    }
    if (commandIf.getEls() instanceof Return) {
      right = true;
    } else {
      if (commandIf.getEls() instanceof CmdArray) {
        cmdArray = (CmdArray) commandIf.getEls();
        right = consultReturn(cmdArray.getArray());
      } else {
        if (commandIf.getEls() instanceof If) {
          commandsIf = (If) commandIf.getEls();

          if (commandsIf.getEls() != null) {
            right = consultIfReturn(commandsIf);
          }
        }
      }
    }
    return left && right;
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

  private boolean compareTypes(SType[] typeAux, SType[] typeParam) {
    boolean equals;
    SType type1, type2;
    STypeArray typeArray1, typeArray2;

    equals = false;
    if (typeAux.length == typeParam.length) {
      equals = true;

      for (int j = 0; j < typeAux.length; j++) {
        type1 = typeAux[j];
        type2 = typeParam[j];

        while (type1 instanceof STypeArray && type2 instanceof STypeArray) {
          typeArray1 = (STypeArray) type1;
          typeArray2 = (STypeArray) type2;
          type1 = typeArray1.getArg();
          type2 = typeArray2.getArg();
        }

        if (!(type1.match(typeVar) || type2.match(typeVar))) {
          if (!type1.match(type2)) {
            equals = false;
          }
        }
      }
    }
    return equals;
  }

  private boolean paramsFunc(ArrayList<STypeFunc> listFunc, SType[] param) {
    boolean equals = false;
    SType type1, type2;
    STypeArray typeArray1, typeArray2;

    for (int i = 0; i < listFunc.size(); i++) {
      SType[] listSType = listFunc.get(i).getParameter();
      if (listSType == null && param == null) {
        equals = true;
        return equals;
      }
      if (listSType.length == param.length) {
        equals = true;

        for (int j = 0; j < listSType.length; j++) {
          type1 = listSType[j];
          type2 = param[j];
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
        return true;
      }
    }
    return false;
  }
}
