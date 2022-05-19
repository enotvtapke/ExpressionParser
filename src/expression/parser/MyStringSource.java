package expression.parser;

import expression.exceptions.ParseException;

public class MyStringSource implements MyCharSource {
    private final String data;
    private int pos;

    public MyStringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public ParseException error(final String message) {
        return new ParseException(pos + ": " + message);
    }
}
