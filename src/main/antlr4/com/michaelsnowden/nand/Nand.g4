grammar Nand;

prog: op EOF;
line: quit | eval | assignment | functionInvocation;
eval: op;
assignment: lhs=Character WhiteSpace? '=' WhiteSpace? rhs=op;
functionDeclaration: name=Character+ WhiteSpace? '=>' WhiteSpace? body=op;
functionInvocation: name=Character+ '(' argument (WhiteSpace argument)* ')';
argument: Character WhiteSpace? ':' WhiteSpace? op;
op: '(' left=op (right=op)? ')' | Bool | Character;
Bool: '0' | '1';
Character: 'a'..'z';
WhiteSpace: ' '+;
quit: 'quit';