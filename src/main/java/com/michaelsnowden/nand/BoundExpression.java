package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
class BoundExpression extends Expression {
    private String symbol;

    public BoundExpression(String variable) {
        this.symbol = variable;
    }

    @Override
    public Boolean evaluate(Map<String, Expression> map) {
        return map.get(symbol).evaluate(map);
    }

    @Override
    public String toString(Map<String, Expression> map) {
        return map.get(symbol).toString(map);
    }

    @Override
    public String toStringWithLabels(Map<String, Expression> map) {
        return symbol + "(" + map.get(symbol).toStringWithLabels(map) + ")";
    }

    @Override
    public void putDependencies(Map<String, Expression> map, Map<String, Integer> dependencies) {
        Integer oldValue = dependencies.getOrDefault(symbol, 0);
        dependencies.put(symbol, oldValue + 1);
        map.get(symbol).putDependencies(map, dependencies);
    }
}
