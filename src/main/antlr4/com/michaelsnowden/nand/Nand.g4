grammar Nand;

line: printWithLabels | print | quit | eval | assignment;
printWithLabels: 'print' WhiteSpace op WhiteSpace 'with' WhiteSpace 'labels';
print: 'print' WhiteSpace op;
quit: 'quit';
eval: op;
assignment: lhs=String WhiteSpace? '=' WhiteSpace? rhs=op;
op: '(' left=op (WhiteSpace right=op)? ')' | Bool | String;
Bool: '0' | '1';
String: ('a'..'z' | 'A'..'Z')+;
WhiteSpace: ' '+;