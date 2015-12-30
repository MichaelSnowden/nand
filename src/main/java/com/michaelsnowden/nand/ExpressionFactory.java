package com.michaelsnowden.nand;

/**
 * @author michael.snowden
 */
class ExpressionFactory {
    public Expression createExpression(NandParser.OpContext ctx) {
        if (ctx.Bool() != null) {
            return new BooleanExpression(Boolean.parseBoolean(ctx.Bool().getText()));
        }
        if (ctx.Character() != null) {
            return new VariableExpression(ctx.Character().getText().charAt(0));
        }
        if (ctx.right == null) {
            return createExpression(ctx.left);
        }
        return new NandExpression(createExpression(ctx.left), createExpression(ctx.right));
    }
}
