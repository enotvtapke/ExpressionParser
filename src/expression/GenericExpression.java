package expression;

import expression.generic.ToMiniString;

public interface GenericExpression<E>extends ToMiniString {
    E evaluate(int x);
}