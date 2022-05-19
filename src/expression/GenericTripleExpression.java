package expression;

import expression.generic.ToMiniString;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface GenericTripleExpression<E> extends ToMiniString {
    E evaluate(int x, int y, int z);
}