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
    private Map<String, Expression> map;
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
                delegate.handleOutput(expression.evaluate(map) ? "1" : "0");
                delegate.doNext(runtime);
            }

            @Override
            public void exitAssignment(NandParser.AssignmentContext ctx) {
                super.exitAssignment(ctx);
                Expression expression = expressionFactory.createExpression(ctx.rhs);
                String c = ctx.lhs.getText();
                map.put(c, expression);
                delegate.handleAssignment(c);
                delegate.doNext(runtime);
            }

            @Override
            public void exitQuit(NandParser.QuitContext ctx) {
                super.exitQuit(ctx);
                delegate.handleQuit();
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
