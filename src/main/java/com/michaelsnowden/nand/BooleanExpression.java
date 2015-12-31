package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
class BooleanExpression implements Expression {
    private final Boolean bool;

    public BooleanExpression(Boolean bool) {
        this.bool = bool;
    }

    @Override
    public Boolean evaluate(Map<String, Expression> map) {
        return bool;
    }
}
