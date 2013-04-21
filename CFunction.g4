grammar CFunction;


function : retType name args ;

args :	 '(' arg (',' arg)* ')' ;

arg 	: 	'double' name 	
	|	'double' '*' name 
	|	'int' name 	;

retType	 :	'double' 	
	|	'double' '*' 	
	;

name : ID;

ID   : LETTER WORD*;

fragment
LETTER : 'a'..'z'|'A'..'Z';

fragment
DIGIT  : '0'..'9';

fragment
WORD   : LETTER | DIGIT | '_';

WS  : (' '|'\r'|'\t'|'\n')+ -> skip;
