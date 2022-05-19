package expression;

import expression.generic.ToMiniString;

public interface GenericTripleExpression<E> extends ToMiniString {
    E evaluate(int x, int y, int z);
}