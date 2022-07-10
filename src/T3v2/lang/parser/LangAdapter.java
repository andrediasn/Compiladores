/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/


package lang.parser;


import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.*;

import lang.ast.*;
import lang.parser.ParseAdaptor;
import lang.parser.langLexer;
import lang.parser.langParser;

public class LangAdapter implements ParseAdaptor {

	@Override
	public SuperNode parseFile(String path) {
		// TODO Auto-generated method stub
    	try {
			
    		langLexer lexer;
        	langParser parser;
        
			CharStream stream = CharStreams.fromFileName(path);
        	lexer = new langLexer(stream);
        	CommonTokenStream tokenStream = new CommonTokenStream(lexer) ;
        	parser = new langParser(tokenStream);
        	
        	ParseTree tree = parser.prog();	
						 		
            Node node = new Node(); 

            if(parser.getNumberOfSyntaxErrors()==0) 
            {
                node.setTree(tree);
                return node;
            }
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/* public SuperNode parseFile(String path) throws Exception 
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
    } */
	
}
