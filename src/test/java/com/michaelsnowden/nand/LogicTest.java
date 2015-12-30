package com.michaelsnowden.nand;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * @author michael.snowden
 */
public class LogicTest {
    @Test
    public void testNot() throws Exception {
        Expression expression = getExpression("not.nand");
        Map<Character, Boolean> map = new HashMap<>();

        boolean evaluate;
        map.put('a', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, !false);
        map.put('a', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, !true);
    }

    @Test
    public void testAnd() throws Exception {
        Expression expression = getExpression("and.nand");
        Map<Character, Boolean> map = new HashMap<>();

        boolean evaluate;
        map.put('a', false);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', false);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', true);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', true);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);
    }

    @Test
    public void testOr() throws Exception {
        Expression expression = getExpression("or.nand");
        Map<Character, Boolean> map = new HashMap<>();

        boolean evaluate;
        map.put('a', false);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', false);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put('a', true);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put('a', true);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);
    }

    @Test
    public void testNor() throws Exception {
        Expression expression = getExpression("nor.nand");
        Map<Character, Boolean> map = new HashMap<>();

        boolean evaluate;
        map.put('a', false);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put('a', false);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', true);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', true);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);
    }

    @Test
    public void testXor() throws Exception {
        Expression expression = getExpression("xor.nand");
        Map<Character, Boolean> map = new HashMap<>();

        boolean evaluate;
        map.put('a', false);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', false);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put('a', true);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put('a', true);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);
    }

    @Test
    public void testXnor() throws Exception {
        Expression expression = getExpression("xnor.nand");
        Map<Character, Boolean> map = new HashMap<>();

        boolean evaluate;
        map.put('a', false);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put('a', false);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', true);
        map.put('b', false);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put('a', true);
        map.put('b', true);
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);
    }

    private Expression getExpression(String file) throws IOException {
        NandLexer lexer = new NandLexer(new ANTLRInputStream(getClass().getClassLoader().getResourceAsStream(file)));
        NandParser parser = new NandParser(new CommonTokenStream(lexer));
        ExpressionFactory factory = new ExpressionFactory();
        final Expression[] expressions = new Expression[1];
        parser.addParseListener(new NandBaseListener() {
            @Override
            public void exitProg(NandParser.ProgContext ctx) {
                super.exitProg(ctx);
                expressions[0] = factory.createExpression(ctx.op());
            }
        });
        parser.prog();

        return expressions[0];
    }
}
