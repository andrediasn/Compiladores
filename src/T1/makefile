all:
	find . -name "Lexical.java" -delete
	java -jar jflex-full-1.8.2.jar GenerateLexical.jflex
	javac -cp . Main.java
clean:
	rm *.class
