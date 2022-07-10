/*
Alunos:

Daniel Souza Ferreira               201665519B
Matheus de Oliveira Carvalho        201665568C 
*/

package lang.parser;

import lang.ast.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main implements ParseAdaptor{
    public SuperNode parseFile(String path) throws Exception 
    {
				CharStream stream = CharStreams.fromFileName(path); 
        langLexer lex = new langLexer(stream); 
        CommonTokenStream tokens = new CommonTokenStream(lex); 
        langParser parser = new langParser(tokens);
        try 
        {
            lex.removeErrorListeners();
            lex.addErrorListener(new BaseErrorListener()  {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new RuntimeException(e.getCause());
                }
            });

					ParseTree tree = parser.prog(); 
					if(parser.getNumberOfSyntaxErrors()==0) 
					{
						ASTVisitorConstruct vis = new ASTVisitorConstruct();
						return tree.accept(vis);
					}
					return null;
				}
				
        catch(RuntimeException e) { return null;}       
    }
}
