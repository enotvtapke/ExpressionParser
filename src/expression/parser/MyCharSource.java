package expression.parser;

import expression.exceptions.ParseException;

public interface MyCharSource {
    boolean hasNext();
    char next();
    ParseException error(final String message);
}
