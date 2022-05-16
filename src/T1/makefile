all:
	find . -name "Lexical.java" -delete
	java -jar jflex-full-1.8.2.jar GenerateLexical.jflex
	javac -cp . Token.java TokenType.java Lexical.java Main.java
clean:
	find . -name "*.class" -delete
