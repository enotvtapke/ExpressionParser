package expression.generic.types;

public interface OperationType<E> {
    E add(E first, E second);
    E subtract(E first, E second);
    E divide(E first, E second);
    E multiply(E first, E second);
    E negate(E first);
    E parse(String s);
    E parse(int s);
}
