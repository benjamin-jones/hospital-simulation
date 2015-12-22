default:
	make all
all:
	javac -g src/*.java -d bin/
	make ./bin/
clean:
	rm bin/*.class
