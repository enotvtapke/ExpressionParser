package expression.generic.types;

import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;

public class IntegerType implements OperationType<Integer> {
    public Integer add(Integer first, Integer second) {
        int r = first + second;
        // Overflow if both arguments have the opposite sign of the result
        if (((first ^ r) & (second ^ r)) < 0) {
            throw new OverflowException("integer overflow");
        }
        return r;
    }

    public Integer subtract(Integer first, Integer second) {
        int r = first - second;
        // Overflow if the arguments have different signs and
        // the sign of the result is different from the sign of x
        if (((first ^ second) & (first ^ r)) < 0) {
            throw new OverflowException("integer overflow");
        }
        return r;
    }

    public Integer multiply(Integer x, Integer y) {
        int r = x * y;
        int ax = x < 0 ? -x : x;
        int ay = y < 0 ? -y : y;
        if (((ax | ay) >>> 15 != 0)) {
            // Some bits greater than 2^15 that might cause overflow
            // Check the result using the divide operator
            // and check for the special case of Long.MIN_VALUE * -1
            if (((y != 0) && (r / y != x)) ||
                    (x == Integer.MIN_VALUE && y == -1)) {
                throw new OverflowException("integer overflow");
            }
        }
        return r;
    }

    public Integer divide(Integer first, Integer second) {
        if (second == 0) {
            throw new DBZException("dividing by zero");
        }
        if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException("integer overflow");
        }
        return first / second;
    }

    public Integer negate(Integer first) {
        if (first == Integer.MIN_VALUE) {
            throw new OverflowException("integer overflow");
        }
        return -first;
    }

    public Integer parse(String s) {
        return Integer.parseInt(s);
    }

    public Integer parse(int x) {
        return x;
    }
}
