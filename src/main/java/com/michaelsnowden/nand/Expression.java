package com.michaelsnowden.nand;

import java.util.Map;

/**
 * @author michael.snowden
 */
interface Expression {
    Boolean evaluate(Map<Character, Boolean> map);
}
