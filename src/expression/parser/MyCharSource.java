package expression.parser;

import expression.exceptions.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface MyCharSource {
    boolean hasNext();
    char next();
    ParseException error(final String message);
}
