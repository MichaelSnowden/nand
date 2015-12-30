grammar Nand;

prog: op EOF;
line: op | assignment;
assignment: lhs=Character WhiteSpace? '=' WhiteSpace? (rhsBool=Bool | rhsVar=Character);
op: '(' left=op (right=op)? ')' | Bool | Character;
Bool: '0' | '1';
Character: 'a'..'z';
WhiteSpace: ' '+;