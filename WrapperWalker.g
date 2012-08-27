tree grammar WrapperWalker;

options {
  tokenVocab = CFunction;
  ASTLabelType = CommonTree;
  output = template;
}

function  : 	^(FUNCTION (n=name) ^(RETTYPE (retType)) 
                ^(INARGS ((s+=scalar)* ((ar+=array)+ l=len)? ) ) )
	->function(name={$n.st},    
      scalars={$s}, arrays={$ar}, len={$l.st} );

// args      :	 (a+=arg)* -> { $a };

scalar 	  : ^(SCALAR name) -> {$name.st};

array     : ^(ARRAY name) -> {$name.st};

len       : ^(ARRAYLEN name) -> {$name.st};

retType	  :SCALAR 
	        |ARRAY 
	        ;

name	    : ID -> {%{$ID.text}};

