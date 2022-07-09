all: clean
	javac -cp .:antlr-4.9.2-complete.jar:ST-4.3.1.jar ast/*.java parser/*.java type/*.java visitor/*.java LangCompiler.java -d .

clean:
	rm -rf lang

execute:
	java -classpath .:antlr-4.9.2-complete.jar:ST-4.3.1.jar lang.LangCompiler -bjc
