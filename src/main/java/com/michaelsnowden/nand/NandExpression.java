package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
class NandExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public NandExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Map<Character, Boolean> map) {
        return !(left.evaluate(map) && right.evaluate(map));
    }
}
