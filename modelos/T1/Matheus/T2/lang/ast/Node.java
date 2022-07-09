/*
Alunos:
Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.ast;

import org.antlr.v4.runtime.tree.*;

public class Node extends SuperNode{

    protected ParseTree tree;

    public ParseTree getTree()
    {
        return tree;
    }

    public void setTree(ParseTree newTree)
    {
        tree = newTree;
    }

    public int getLine()
    {
        return -1;
    }

    public int getColumn()
    {
        return -1;
    }
}
