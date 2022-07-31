 
all : compile


parser/Scope.java: 
	java -jar antlr-4.8-complete.jar -no-listener -no-visitor parser/Scope.g4 

compile : parser/Scope.java
	find . -name "*.java" > javalist
	javac -cp .:antlr-4.8-complete.jar @javalist
	rm -f javalist
	
run: compile
	java -cp .:antlr-4.8-complete.jar Teste sample.txt

clean: 
	rm -f parser/ScopeParser.java
	rm -f parser/ScopeLexer.java
	rm -f parser/Scope.tokens
	rm -f parser/Scope.interp
	rm -f parser/ScopeLexer.interp
	rm -f parser/ScopeLexer.tokens
	rm -f ast/*.class
	rm -f parser/*.class
	rm -f *.class
	find . -name "*.class" -delete
	
cleanClass :
	find . -name "*.class" -delete
	
cleanParser: 
	rm -f parser/ScopeParser.java
	rm -f parser/ScopeLexer.java
	rm -f parser/Scope.tokens
	rm -f parser/Scope.interp
	rm -f parser/ScopeLexer.interp
	rm -f parser/ScopeLexer.tokens
