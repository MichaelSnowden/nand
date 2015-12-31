package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
class BoundExpression implements Expression {
    private String symbol;

    public BoundExpression(String variable) {
        this.symbol = variable;
    }

    @Override
    public Boolean evaluate(Map<String, Expression> map) {
        return map.get(symbol).evaluate(map);
    }
}
