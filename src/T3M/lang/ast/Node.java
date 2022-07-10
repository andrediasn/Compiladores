/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/

package lang.ast;

import org.antlr.v4.runtime.tree.*;
import lang.visitor.Visitor;

public class Node extends SuperNode{

    protected ParseTree tree;

    public ParseTree getTree() {
        return tree;
    }

    public void setTree(ParseTree newTree) {
        tree = newTree;
    }

    public int getLine() {
        return -1;
    }

    public int getColumn() {
        return -1;
    }

    @Override
    public void accept(Visitor v) {
        // TODO Auto-generated method stub
    }
}
