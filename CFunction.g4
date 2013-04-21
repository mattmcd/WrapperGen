grammar CFunction;


function : retType name args ;

args :	 '(' arg (',' arg)* ')' ;

arg 	
  : 	'double' name 	# SCALAR_ARG
	|	'double' '*' name # ARRAY_ARG
	|	'int' name 	      # LENGTH_ARG
  ; 

retType	 
  :	'double' 	        # SCALAR_RET
	|	'double' '*' 	    # ARRAY_RET
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
