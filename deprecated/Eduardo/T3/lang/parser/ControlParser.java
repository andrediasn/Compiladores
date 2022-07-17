/* Eduardo Vieira Marques Pereira do Valle 201665554C
 * Matheus Brinati Altomar 201665564C
 */
package lang.parser;

import lang.ast.SuperNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class ControlParser implements ParseAdaptor{
    public SuperNode parseFile(String path) throws Exception 
    {
        
        // Create a ANTLR CharStream from a file
        CharStream stream = CharStreams.fromFileName(path); 
        // create a lexer that feeds off of stream 
        langLexer lex = new langLexer(stream); 
        // create a buffer of tokens pulled from the lexer 
        CommonTokenStream tokens = new CommonTokenStream(lex); 
        // create a parser that feeds off the tokens buffer 
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
            if(parser.getNumberOfSyntaxErrors()!=0) 
            {
                return null;
            }
            ConstroiASTVisitor vis = new ConstroiASTVisitor();
            return tree.accept(vis);
        }
        catch(RuntimeException e) { System.out.println(e.getMessage()); return null;}
        
    }
}
