package expression.generic.types;

import expression.exceptions.DBZException;

public class UnsignedType implements OperationType<Integer> {
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    public Integer subtract(Integer first, Integer second) {

        return first - second;
    }

    public Integer multiply(Integer x, Integer y) {

        return x * y;
    }

    public Integer divide(Integer first, Integer second) {
        if (second == 0) {
            throw new DBZException("dividing by zero");
        }
        return first / second;
    }

    public Integer negate(Integer first) {
        return -first;
    }

    public Integer parse(String s) {
        return Integer.parseInt(s);
    }

    public Integer parse(int x) {
        return x;
    }
}
