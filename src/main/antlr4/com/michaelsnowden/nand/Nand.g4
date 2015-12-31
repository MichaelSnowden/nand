grammar Nand;

line: WhiteSpace? (printDependencies | printWithLabels | print | help | quit | eval | assignment) WhiteSpace?;
help: 'help';
printDependencies: 'graph' WhiteSpace op;
printWithLabels: 'recursive meta' WhiteSpace op;
print: 'meta' WhiteSpace op;
quit: 'quit' | 'exit';
eval: op;
assignment: lhs=String WhiteSpace? '=' WhiteSpace? rhs=op;
op: '(' left=op (WhiteSpace right=op)? ')' | Bool | String;
Bool: '0' | '1';
String: ('a'..'z' | 'A'..'Z')+;
WhiteSpace: (' ' | '\t')+;