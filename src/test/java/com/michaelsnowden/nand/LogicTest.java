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
        Expression expression = new ExpressionFactory().createExpression(getFile("not.nand"));
        Map<String, Expression> map = new HashMap<>();

        boolean evaluate;
        map.put("a", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);
        map.put("a", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);
    }

    @Test
    public void testAnd() throws Exception {
        Expression expression = new ExpressionFactory().createExpression(getFile("and.nand"));
        Map<String, Expression> map = new HashMap<>();

        boolean evaluate;
        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);
    }

    @Test
    public void testOr() throws Exception {
        Expression expression = new ExpressionFactory().createExpression(getFile("or.nand"));
        Map<String, Expression> map = new HashMap<>();

        boolean evaluate;
        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);
    }

    @Test
    public void testNor() throws Exception {
        Expression expression = new ExpressionFactory().createExpression(getFile("nor.nand"));
        Map<String, Expression> map = new HashMap<>();

        boolean evaluate;
        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);
    }

    @Test
    public void testXor() throws Exception {
        Expression expression = new ExpressionFactory().createExpression(getFile("xor.nand"));
        Map<String, Expression> map = new HashMap<>();

        boolean evaluate;
        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);
    }

    @Test
    public void testXnor() throws Exception {
        Expression expression = new ExpressionFactory().createExpression(getFile("xnor.nand"));
        Map<String, Expression> map = new HashMap<>();

        boolean evaluate;
        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);

        map.put("a", new BooleanExpression(false));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(false));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, false);

        map.put("a", new BooleanExpression(true));
        map.put("b", new BooleanExpression(true));
        evaluate = expression.evaluate(map);
        assertEquals(evaluate, true);
    }

    private InputStream getFile(String file) {
        return getClass().getClassLoader().getResourceAsStream(file);
    }
}
