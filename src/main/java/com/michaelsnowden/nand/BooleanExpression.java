package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
class BooleanExpression extends Expression {
    private final Boolean bool;

    public BooleanExpression(Boolean bool) {
        this.bool = bool;
    }

    @Override
    public Boolean evaluate(Map<String, Expression> map) {
        return bool;
    }

    @Override
    public String toString() {
        return String.valueOf(bool);
    }
}
