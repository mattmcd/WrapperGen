all: CFunction.g WrapperWalker.g 
	mkdir -p generated
	mkdir -p classes
	java org.antlr.Tool -o generated CFunction.g
	java org.antlr.Tool -o generated WrapperWalker.g
	javac -cp $$CLASSPATH:generated -d classes Main.java generated/*.java

run:
	java -cp $$CLASSPATH:classes Main test2.txt

test01:
	java -cp $$CLASSPATH:classes Main test.txt

test02:
	java -cp $$CLASSPATH:classes Main test2.txt

test03: 
	java -cp $$CLASSPATH:classes Main test3.txt

appdir:
	mkdir -p WrapperGen
	mkdir -p WrapperGen/src/META-INF
	mkdir -p WrapperGen/war/WEB-INF/classes
	mkdir -p WrapperGen/war/WEB-INF/lib
