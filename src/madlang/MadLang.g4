grammar MadLang;

// Keywords - must be before IDENT
FN      : 'fn'     ;
IF      : 'if'     ;
ELSE    : 'else'   ;
WHILE   : 'while'  ;
RETURN  : 'return' ;
INT     : 'int'    ;
BOOL : 'bool'      ;
TRUE    : 'true'   ;
FALSE   : 'false'  ;

// SYMBOLS
LEFT_PAREN  : '(' ;
RIGHT_PAREN : ')' ;
LEFT_BRACE  : '{' ;
RIGHT_BRACE : '}' ;
COMMA       : ',' ;
COLON       : ':' ;
SEMICOLON   : ';' ;

// OPERATORS
PLUS          : '+'  ;
MINUS         : '-'  ;
STAR          : '*'  ;
SLASH         : '/'  ;
PERCENT       : '%'  ;
BANG          : '!'  ;
BANG_EQUAL    : '!=' ;
EQUAL         : '='  ;
EQUAL_EQUAL   : '==' ;
LESS          : '<'  ;
LESS_EQUAL    : '<=' ;
GREATER       : '>'  ;
GREATER_EQUAL : '>=' ;
AND           : '&&' ;
OR            : '||' ;

// Literals
INT_LIT : '0' | [1-9][0-9]* ;
IDENT   : [A-Za-z_][A-Za-z0-9_]* ;

// Whitespace and Comments
WS            : [ \t\r\n]+        -> skip ;
LINE_COMMENT  : '//' ~[\n]* '\n'? -> skip ;
BLOCK_COMMENT : '/*' .*? '*/'     -> skip ;


// ----- Parser Rules -----
// Program
program : decl* EOF ;

// Types
type : INT | BOOL ;

// Declarations 
decl          : globalVarDecl | funDecl ;
globalVarDecl : IDENT COLON type (EQUAL expr)? SEMICOLON;  
funDecl       : FN IDENT LEFT_PAREN params? RIGHT_PAREN COLON type block ; 
params        : param (COMMA param)* ;
param         : IDENT COLON type ;

// Statements
stmt : block
     | varDef
     | funDef
     | assignStmt
     | ifStmt
     | whileStmt
     | returnStmt
     | exprStmt
     ;

block      : LEFT_BRACE stmt* RIGHT_BRACE ;
varDef     : IDENT COLON type (EQUAL expr)? SEMICOLON ;
funDef     : funDecl ;
assignStmt : IDENT EQUAL expr SEMICOLON ;
ifStmt     : IF LEFT_PAREN expr RIGHT_PAREN stmt (ELSE stmt)? ;
whileStmt  : WHILE LEFT_PAREN expr RIGHT_PAREN stmt ;
returnStmt : RETURN expr SEMICOLON ;
exprStmt   : expr SEMICOLON ;

// Expressions
expr : orExpr ;
orExpr : andExpr (OR andExpr)* ;
andExpr : eqExpr (AND eqExpr)* ;
eqExpr : relExpr ((EQUAL_EQUAL | BANG_EQUAL) relExpr)* ;
relExpr : addExpr ((LESS | LESS_EQUAL | GREATER | GREATER_EQUAL) addExpr)* ;
addExpr : mulExpr ((PLUS | MINUS) mulExpr)* ;
mulExpr : unaryExpr ((STAR | SLASH | PERCENT) unaryExpr)* ;
unaryExpr : (MINUS | BANG) unaryExpr
          | primary
          ;

primary : INT_LIT
        | TRUE
        | FALSE
        | IDENT (LEFT_PAREN args? RIGHT_PAREN)?
        | LEFT_PAREN expr RIGHT_PAREN
        ;

args : expr (COMMA expr)* ; 