package expression;

public abstract class AbstractUnaryOperation<E> implements AbstractExpression<E> {
    private final AbstractExpression<E> operand;

    protected AbstractUnaryOperation(AbstractExpression<E> operand) {
        this.operand = operand;
    }

    abstract String getOperationLabel();

    abstract E applyOperation(E operand);

    @Override
    public E evaluate(int x) {
        return applyOperation(operand.evaluate(x));
    }

    @Override
    public E evaluate(int x, int y, int z) {
        return applyOperation(operand.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return this.getOperationLabel() + " " + operand;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            AbstractUnaryOperation that = (AbstractUnaryOperation) obj;
            return this.operand.equals(that.operand);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode() * operand.hashCode();
    }
}
