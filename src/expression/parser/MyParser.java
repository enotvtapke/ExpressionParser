package expression.parser;

import expression.GenericTripleExpression;

public interface MyParser {
    GenericTripleExpression parse(String expression);
}