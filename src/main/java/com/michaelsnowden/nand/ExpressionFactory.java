package com.michaelsnowden.nand;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author michael.snowden
 */
class ExpressionFactory {
    public Expression createExpression(NandParser.OpContext ctx) {
        if (ctx.Bool() != null) {
            return new BooleanExpression(Boolean.parseBoolean(ctx.Bool().getText()));
        }
        if (ctx.String() != null) {
            return new BoundExpression(ctx.String().getText());
        }
        if (ctx.right == null) {
            return createExpression(ctx.left);
        }
        return new NandExpression(createExpression(ctx.left), createExpression(ctx.right));
    }

    public Expression createExpression(InputStream inputStream) throws IOException {
        NandLexer lexer = new NandLexer(new ANTLRInputStream(inputStream));
        NandParser parser = new NandParser(new CommonTokenStream(lexer));
        final Expression[] expressions = new Expression[1];
        parser.addParseListener(new NandBaseListener() {
            @Override
            public void exitProg(NandParser.ProgContext ctx) {
                expressions[0] = createExpression(ctx.op());
            }
        });
        parser.prog();

        return expressions[0];
    }
}
