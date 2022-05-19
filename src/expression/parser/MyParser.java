package expression.parser;

import expression.GenericTripleExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface MyParser {
    GenericTripleExpression parse(String expression);
}