package expression;

public abstract class AbstractBinaryOperation<E> implements AbstractExpression<E> {
    private final AbstractExpression<E> firstOperand;
    private final AbstractExpression<E> secondOperand;

    protected AbstractBinaryOperation(AbstractExpression<E> firstOperand, AbstractExpression<E> secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    abstract String getOperationLabel();

    abstract E applyOperation(E first, E second);

    @Override
    public E evaluate(int x) {
        return applyOperation(firstOperand.evaluate(x), secondOperand.evaluate(x));
    }

    @Override
    public E evaluate(int x, int y, int z) {
        return applyOperation(firstOperand.evaluate(x, y, z), secondOperand.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + firstOperand + ' ' + this.getOperationLabel() + ' ' + secondOperand + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass()) {
            AbstractBinaryOperation that = (AbstractBinaryOperation) obj;
            return this.firstOperand.equals(that.firstOperand) && this.secondOperand.equals(that.secondOperand);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.getClass().hashCode() * 73 + firstOperand.hashCode() * 31 + secondOperand.hashCode()) * 31;
    }
}
