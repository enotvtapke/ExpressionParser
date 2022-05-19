package expression.generic.types;

import expression.exceptions.DBZException;

import java.math.BigInteger;

public class BigIntegerType implements OperationType<BigInteger> {
    public BigInteger add(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    public BigInteger subtract(BigInteger first, BigInteger second) {
        return first.subtract(second);
    }

    public BigInteger multiply(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    public BigInteger divide(BigInteger first, BigInteger second) {
        if (second.equals(BigInteger.ZERO)) {
            throw new DBZException("dividing by zero");
        }
        return first.divide(second);
    }

    public BigInteger negate(BigInteger first) {
        return first.negate();
    }

    public BigInteger parse(String s) {
        return new BigInteger(s);
    }

    public BigInteger parse(int x) {
        return BigInteger.valueOf(x);
    }
}
