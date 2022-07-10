compile: genparser
	javac -cp .:antlr-4.10.1-complete.jar ast/*.java parser/*.java LangCompiler.java -d .

genparser: parser/lang.g4
	java -jar antlr-4.10.1-complete.jar parser/lang.g4
	
run: 
	java -cp .:antlr-4.10.1-complete.jar lang.LangCompiler -bs $(ARQ)
	
clean: 
	rm -rf lang
	rm -R parser/langParser.java
	rm -R parser/langLexer*
	rm -R parser/lang.interp
	rm -R parser/lang.tokens