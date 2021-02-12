// Define a grammar called SatelliteBalise
grammar SatelliteBalise;

@header {
}

script	: command* ;
command	: (call | assign) ';' ;

call	: ID '.' ID '(' args? ')' ;
assign	: ID '=' create ;
create  : 'new ' ID '()' ;

args	: arg (',' arg)* ;
arg		: ID ':' VALUE ;

ID		: [a-zA-Z][a-zA-Z0-9]* ;
VALUE	: [a-zA-Z0-9]+ ;
WS  	: [ \t\r\n]+ -> skip ;