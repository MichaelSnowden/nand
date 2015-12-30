package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
class VariableExpression implements Expression {
    private Character character;

    public VariableExpression(Character character) {
        this.character = character;
    }

    @Override
    public Boolean evaluate(Map<Character, Boolean> map) {
        return map.get(character);
    }
}
