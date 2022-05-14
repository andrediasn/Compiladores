all:
	find . -name "LexicalText.java" -delete
	java -jar jflex-full-1.8.2.jar LexicalGenerator.jflex
	javac -cp . Token.java TOKEN_TYPE.java LexicalText.java Main.java
clean:
	find . -name "*.class" -delete
