package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
class NandExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public NandExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean evaluate(Map<String, Expression> map) {
        return !(left.evaluate(map) && right.evaluate(map));
    }

    @Override
    public String toString(Map<String, Expression> map) {
        return "(" + left.toString(map) + " " + right.toString(map) + ")";
    }

    @Override
    public String toStringWithLabels(Map<String, Expression> map) {
        return "(" + left.toStringWithLabels(map) + " " + right.toStringWithLabels(map) + ")";
    }

    @Override
    public void putDependencies(Map<String, Expression> map, Map<String, Integer> dependencies) {
        left.putDependencies(map, dependencies);
        right.putDependencies(map, dependencies);
    }
}
