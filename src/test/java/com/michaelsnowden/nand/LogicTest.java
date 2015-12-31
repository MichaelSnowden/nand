package com.michaelsnowden.nand;

import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * @author michael.snowden
 */
public class LogicTest {
    @Test
    public void testNot() throws Exception {
        Expression expression = new ExpressionFactory().getExpression(getFile("not.nand"));
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
        Expression expression = new ExpressionFactory().getExpression(getFile("and.nand"));
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
        Expression expression = new ExpressionFactory().getExpression(getFile("or.nand"));
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
        Expression expression = new ExpressionFactory().getExpression(getFile("nor.nand"));
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
        Expression expression = new ExpressionFactory().getExpression(getFile("xor.nand"));
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
        Expression expression = new ExpressionFactory().getExpression(getFile("xnor.nand"));
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

    private InputStream getFile(String file) {
        return getClass().getClassLoader().getResourceAsStream(file);
    }
}
