package expression.generic.types;

public class DoubleType implements OperationType<Double> {
    public Double add(Double first, Double second) {
        return first + second;
    }

    public Double subtract(Double first, Double second) {
        return first - second;
    }

    public Double multiply(Double x, Double y) {
        return x * y;
    }

    public Double divide(Double first, Double second) {
        return first / second;
    }

    public Double negate(Double first) {
        return -first;
    }

    public Double parse(String s) {
        return Double.parseDouble(s);
    }

    public Double parse(int x) {
        return (double) x;
    }
}
