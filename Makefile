run: Playfair.class
	java Playfair $(ARGS)

Playfair.class: Playfair.java
	javac Playfair.java

clean:
	rm*.class