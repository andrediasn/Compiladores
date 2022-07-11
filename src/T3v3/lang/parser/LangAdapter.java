/*

Grupo

Nome: André Dias Nunes
Matrícula: 201665570C

Nome: Guilherme Barbosa
Matrícula: 201435031

*/


package lang.parser;

import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import lang.ast.*;
import lang.parser.ParseAdaptor;
import lang.parser.langLexer;
import lang.parser.langParser;

public class LangAdapter implements ParseAdaptor {

	/* @Override
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
		
	} */

	public SuperNode parseFile(String path) {
        try  {
			langLexer lexer;
			langParser parser;

			CharStream stream = CharStreams.fromFileName(path);
			lexer = new langLexer(stream);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer) ;
			parser = new langParser(tokenStream);
			ParseTree tree = parser.prog();	

			lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener()  {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new RuntimeException(e.getCause());
                }
            });
			
			if( parser.getNumberOfSyntaxErrors()==0 ) {
				ASTVisitorConstruct vis = new ASTVisitorConstruct();
				return tree.accept(vis);
			}
			
		}	
        catch(IOException e) { e.printStackTrace();}     

		return null;  
    }
}