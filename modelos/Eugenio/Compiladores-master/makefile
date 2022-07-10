all:
	find . -name "*.class" -delete
	javac -cp . lang/Main.java
ifdef interpretar
ifdef compilar
	java lang.Main $(file) $(interpretar) $(compilar)
else
	java lang.Main $(file) $(interpretar) false
endif
else
ifdef compilar
	java lang.Main $(file) false $(compilar)
else
	java lang.Main $(file) false false
endif
endif

lexical:
	find . -name "Lexical.java" -delete
	java -jar lang/lexical/jflex-full-1.8.2.jar lang/lexical/GenerateLexical.jflex

clean:
	find . -name "*.class" -delete
