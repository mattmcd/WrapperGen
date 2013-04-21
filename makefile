all: CFunction.g WrapperWalker.g
	mkdir -p generated
	mkdir -p classes
	antlr -o generated CFunction.g
	antlr -o generated WrapperWalker.g
	javac -cp $(CLASSPATH):generated -d classes Main.java WrapperGen.java generated/*.java

v4:	CFunction.g4 CFunctionWalker.java MainV4.java WrapperGenV4.java
	mkdir -p generatedV4
	mkdir -p classesV4
	antlr4 -o generatedV4 CFunction.g4
	javac -cp $(CLASSPATH):generatedv4 -d classesV4 MainV4.java WrapperGenV4.java CFunctionWalker.java generatedV4/*.java
	
run:
	java -cp $(CLASSPATH):classes Main test2.txt

runV4:
	java -cp $(CLASSPATH):classesV4 MainV4 test2.txt

jar: wrappergen.jar 

wrappergen.jar: manifest.txt classes/Main.class
	jar cfm wrappergen.jar manifest.txt -C classes .

deploy: wrappergen.jar PythonWrapper.stg MEXWrapper.stg
	cp wrappergen.jar WrapperGen/war/WEB-INF/lib
	cp antlrworks-1.4.3.jar WrapperGen/war/WEB-INF/lib
	cp PythonWrapper.stg WrapperGen/war/WEB-INF
	cp MEXWrapper.stg WrapperGen/war/WEB-INF

test01:
	java -cp $$CLASSPATH:classes Main test.txt

test02:
	java -cp $$CLASSPATH:classes Main test2.txt

test02mex:
	java -cp $$CLASSPATH:classes Main test2.txt MEXWrapper.stg

test03: 
	java -cp $$CLASSPATH:classes Main test3.txt

appdir:
	mkdir -p WrapperGen
	mkdir -p WrapperGen/src/META-INF
	mkdir -p WrapperGen/war/WEB-INF/classes
	mkdir -p WrapperGen/war/WEB-INF/lib
