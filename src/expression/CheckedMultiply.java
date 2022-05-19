package expression;

import expression.generic.types.OperationType;

public class CheckedMultiply<E> extends AbstractBinaryOperation<E> {
    protected static final String operationLabel = "*";
    protected final OperationType<E> type;

    public CheckedMultiply(AbstractExpression<E> firstOperand, AbstractExpression<E> secondOperand, OperationType<E> type) {
        super(firstOperand, secondOperand);
        this.type = type;
    }

    @Override
    public String getOperationLabel() {
        return operationLabel;
    }

    @Override
    protected E applyOperation(E first, E second) {
        return type.multiply(first, second);
    }
}