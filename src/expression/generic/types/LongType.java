package expression.generic.types;

import expression.exceptions.DBZException;

public class LongType implements OperationType<Long> {
    public Long add(Long first, Long second) {
        return first + second;
    }

    public Long subtract(Long first, Long second) {
        return first - second;
    }

    public Long multiply(Long x, Long y) {
        return x * y;
    }

    public Long divide(Long first, Long second) {
        if (second == 0) {
            throw new DBZException("dividing by zero");
        }
        return first / second;
    }

    public Long negate(Long first) {
        return -first;
    }

    public Long parse(String s) {
        return Long.parseLong(s);
    }

    public Long parse(int x) {
        return (long) x;
    }
}
