// Define a grammar called SatelliteBalise
grammar SatelliteBalise;

@header {
}

script	: command* ;
command	: (call | assign) ';' ;

call	: ID '.' ID '(' args ')' ;
assign	: ID '=' create ;
create  : 'new ' ID '(' args ')' ;

args	: | arg (',' arg)* ;
arg		: ID ':' ENTIER ',' ;

ID		: [a-zA-Z][a-zA-Z0-9]* ;
ENTIER	: [0-9]+ ;

// Grammaire prof
//
// script	: command* ;
// command	: (call | assign) ';' ;

// call	: ID '.' ID '(' args ')' ;
// assign	: ID '=' value ;

// args	: | arg (',' arg)* ;
// arg		: ID ':' value ',' ;

// value	: ENTIER | SYMBOL | call ;
// ENTIER	: [0-9]+ ;
// SYMBOL	: '#' ID;
// ID		: [a-zA-Z][a-zA-Z0-9]* ;