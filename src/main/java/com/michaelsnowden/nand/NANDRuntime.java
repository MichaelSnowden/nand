package com.michaelsnowden.nand;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author michael.snowden
 */
public class NANDRuntime {
    private final RuntimeDelegate delegate;
    private Map<Character, Boolean> map;
    private ExpressionFactory expressionFactory;
    NandBaseListener listener;

    public NANDRuntime(RuntimeDelegate delegate) {
        this.delegate = delegate;
        this.delegate.init();
        Runtime.getRuntime().addShutdownHook(new Thread(delegate::handleQuit));
        map = new HashMap<>();
        expressionFactory = new ExpressionFactory();
        NANDRuntime runtime = this;
        listener = new NandBaseListener() {
            @Override
            public void exitEval(NandParser.EvalContext ctx) {
                super.exitEval(ctx);
                Expression expression = expressionFactory.createExpression(ctx.op());
                delegate.handleOutput(expression.evaluate(map).toString());
                delegate.doNext(runtime);
            }

            @Override
            public void exitAssignment(NandParser.AssignmentContext ctx) {
                super.exitAssignment(ctx);
                boolean value = expressionFactory.createExpression(ctx.rhs).evaluate(map);
                char c = ctx.lhs.getText().charAt(0);
                map.put(c, value);
                delegate.handleAssignment(c, value);
                delegate.doNext(runtime);
            }


        };
        this.delegate.doNext(this);
    }

    public void processLine(String line) throws IOException {
        processLine(new ByteArrayInputStream(line.getBytes()));
    }

    public void processLine(InputStream line) throws IOException {
        NandLexer lexer = new NandLexer(new ANTLRInputStream(line));
        NandParser parser = new NandParser(new CommonTokenStream(lexer));
        parser.addParseListener(listener);
        parser.line();
    }
}
