// Define a grammar called SatelliteBalise
grammar SatelliteBalise;

@header {
}

script	: command* ;
command	: (call | assign) ';' ;

call	: ID '.' ID '(' args ')' ;
assign	: ID '=' value ;

args	: | arg (',' arg)* ;
arg		: ID ':' value ',' ;

value	: ENTIER | SYMBOL | call ;
ENTIER	: [0-9]+ ;
SYMBOL	: '#' ID;
ID		: [a-zA-Z][a-zA-Z0-9]* ;
