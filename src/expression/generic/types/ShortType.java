package expression.generic.types;

import expression.exceptions.DBZException;

public class ShortType implements OperationType<Short> {
    public Short add(Short first, Short second) {
        return (short) (first + second);
    }

    public Short subtract(Short first, Short second) {
        return (short) (first - second);
    }

    public Short multiply(Short x, Short y) {
        return (short) (x * y);
    }

    public Short divide(Short first, Short second) {
        if (second == 0) {
            throw new DBZException("dividing by zero");
        }
        return (short) (first / second);
    }

    public Short negate(Short first) {
        return (short) (-first);
    }

    public Short parse(String s) {
        return Short.parseShort(s);
    }

    public Short parse(int x) {
        return (short) x;
    }
}
