package com.michaelsnowden.nand;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author michael.snowden
 */
public class REPL {
    public static void main(String[] args) throws IOException {
        ExpressionFactory factory = new ExpressionFactory();
        Map<Character, Boolean> map = new HashMap<>();
        NandBaseListener listener = new NandBaseListener() {
            @Override
            public void exitLine(NandParser.LineContext ctx) {
                super.exitLine(ctx);
                NandParser.AssignmentContext assignment = ctx.assignment();
                if (assignment != null) {
                    boolean value;
                    if (assignment.rhsBool != null) {
                        value = !assignment.rhsBool.getText().equals("0");
                    } else {
                        value = map.get(assignment.rhsVar.getText().charAt(0));
                    }
                    map.put(assignment.lhs.getText().charAt(0), value);
                } else {
                    Expression expression = factory.createExpression(ctx.op());
                    System.out.println(expression.evaluate(map));
                }
            }
        };
        Scanner scanner = new Scanner(System.in);
        String nextLine;
        do {
            System.out.print("nand> ");
            nextLine = scanner.nextLine().trim();
            if (nextLine.equals("help")) {
                System.out.println("Welcome to NAND!");
                System.out.println("NAND is a really simple DSL where you can only assign variables and NAND things");
                System.out.println("NAND expressions follow a very simple syntax");
                System.out.println("<line> = <exp> | <assignment>");
                System.out.println("<exp> = '(' <exp> <exp>? ')' | <bool> | <character> ");
                System.out.println("<assignment> = <character> <ws> '=' <ws> (<character> | <bool>)");
                System.out.println("<bool> = 0 | 1");
                System.out.println("<character> = 'a' | 'b' | .. | 'z'");
                System.out.println("<ws> = ' '+");
                System.out.println("The semantics are also simple.");
                System.out.println("If an expression (exp) is encountered, evaluate it and print the result");
                System.out.println("If an assignment is encountered, assign that variable");
                System.out.println("So, enter an expression or an assignment");
                System.out.println("An expression could be something like the following");
                System.out.println("    0");
                System.out.println("    1");
                System.out.println("    (0)");
                System.out.println("    (1)");
                System.out.println("    (01");
                System.out.println("    (0(11))");
                System.out.println("An assignment looks something like this");
                System.out.println("    a = 0");
                System.out.println("    b = 1");
                System.out.println("After assigning variables, you can use them in your expressions");
                System.out.println("    (aa)");
                System.out.println("    (a(ba))");
                System.out.println("Variables can only be a single character.");
                System.out.println("Have fun!");
            } else {
                NandLexer lexer = new NandLexer(new ANTLRInputStream(new ByteArrayInputStream(nextLine.getBytes())));
                NandParser parser = new NandParser(new CommonTokenStream(lexer));
                parser.addParseListener(listener);
                parser.line();
            }
        } while (!nextLine.equals("q"));
    }
}
