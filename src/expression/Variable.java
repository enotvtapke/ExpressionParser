package expression;

import expression.generic.types.OperationType;

public class Variable<E> implements AbstractExpression<E> {
    private final String variableName;
    private final OperationType<E> type;

    public Variable(String variableName, OperationType<E> type) {
        this.variableName = variableName;
        this.type = type;
    }

    @Override
    public E evaluate(int x) {
        return type.parse(x);
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable that = (Variable) obj;
            return this.variableName.equals(that.variableName);
        }
        return false;
    }

    @Override
    public E evaluate(int x, int y, int z) {
        if (variableName.equals("x")) {
            return type.parse(x);
        } else if (variableName.equals("y")) {
            return type.parse(y);
        } else if (variableName.equals("z")) {
            return type.parse(z);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        return variableName.hashCode();
    }
}
