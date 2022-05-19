package expression;

public class Const<E> implements AbstractExpression<E> {
    private final E number;

    public Const(E number) {
        this.number = number;
    }

    @Override
    public E evaluate(int x) {
        return number;
    }

    @Override
    public E evaluate(int x, int y, int z) {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            Const that = (Const) obj;
            return this.number.equals(that.number);
        }
        return false;
    }

    /*
    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }*/
}
