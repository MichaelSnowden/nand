package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
abstract class Expression {
    public abstract Boolean evaluate(Map<String, Expression> map);
    public abstract String toString(Map<String, Expression> map);
    public abstract String toStringWithLabels(Map<String, Expression> map);
}
