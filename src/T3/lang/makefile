compile: genparser
	javac -cp .:antlr-4.10.1-complete.jar ast/*.java parser/*.java ASTVisitor/*.java LangCompiler.java

genparser: parser/lang.g4
	java -jar antlr-4.10.1-complete.jar parser/lang.g4 -visitor
	
run: 
	java -cp .:antlr-4.10.1-complete.jar lang.LangCompiler $(COMMAND) $(ARQ)
	
clean: 
	rm -rf lang
	rm -R parser/langParser.java
	rm -R parser/langLexer*
	rm -R parser/lang.interp
	rm -R parser/lang.tokens