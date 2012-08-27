grammar CFunction;

options {
  output = AST;
  ASTLabelType = CommonTree;
}

tokens {
  SCALAR;
  ARRAY;
  ARRAYLEN;
  INARGS;
  FUNCTION;
  RETTYPE;
  UNIT;
}

function : retType name args -> ^(FUNCTION name ^(RETTYPE retType) ^(INARGS args));

args :	 '(' ! arg (','! arg)* ')' !;

arg 	: 	'double' name 	-> ^(SCALAR name)
	|	'double' '*' name -> ^(ARRAY name)
	|	'int' name 	-> ^(ARRAYLEN name);

retType	 :	'double' 	-> SCALAR
	|	'double' '*' 	-> ARRAY
	;

name : ID;

ID   : LETTER WORD*;

fragment
LETTER : 'a'..'z'|'A'..'Z';

fragment
DIGIT  : '0'..'9';

fragment
WORD   : LETTER | DIGIT | '_';

WS  : (' '|'\r'|'\t'|'\n')+ { $channel = HIDDEN; };
