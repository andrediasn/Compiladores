
import parser.*;
import ast.*;
import visitors.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Teste {

    public static void main(String args[]) throws Exception {
	// Create a ANTLR CharStream from a file
	CharStream stream = CharStreams.fromFileName(args[0]);
	// create a lexer that feeds off of stream
	ScopeLexer lex = new ScopeLexer(stream);
	// create a buffer of tokens pulled from the lexer
	CommonTokenStream tokens = new CommonTokenStream(lex);
	// create a parser that feeds off the tokens buffer
	ScopeParser parser = new ScopeParser(tokens);

	Program tree = parser.prog().p;
	//System.out.println(tree.toString());

	tree.accept(new ScopeVisitor());
    }
}
