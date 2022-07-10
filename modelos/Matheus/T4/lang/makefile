all: clean
	javac -cp .:antlr-4.9.2-complete.jar ast/*.java parser/*.java type/*.java visitor/*.java LangCompiler.java -d .

clean:
	rm -rf lang

execute:
	java -classpath .:antlr-4.9.2-complete.jar lang.LangCompiler -byt
