compile: genparser
	javac -cp .:antlr-4.10.1-complete.jar:ST-4.3.1.jar ast/*.java parser/*.java visitors/*.java langUtil/*.java LangCompiler.java -d .
	clear

genparser: parser/lang.g4
	java -jar antlr-4.10.1-complete.jar parser/lang.g4 -visitor
	
run: 
	java -cp .:antlr-4.10.1-complete.jar:ST-4.3.1.jar lang.LangCompiler ${cmd}
	
clean: 
	rm -rf lang
	rm -R parser/langParser.java
	rm -R parser/langLexer*
	rm -R parser/lang.interp
	rm -R parser/lang.tokens
	rm -R parser/langListener.java
	rm -R parser/langBaseListener.java
	rm -R parser/langBaseVisitor.java
	rm -R parser/langVisitor.java

teste:
	node code_js/teste0.js
	node code_js/teste1.js
	node code_js/teste1eMeio.js
	node code_js/teste2.js
	node code_js/teste3.js
	node code_js/teste4.js
	node code_js/teste5.js
	node code_js/teste6.js
	node code_js/teste8.js
	node code_js/teste10.js
	node code_js/teste11.js
	node code_js/teste12.js

all: compile run clean teste