package expression;

import expression.generic.types.OperationType;

public class CheckedSubtract<E> extends AbstractBinaryOperation<E> {
    private static final String operationLabel = "-";
    private final OperationType<E> type;

    public CheckedSubtract(AbstractExpression<E> firstOperand, AbstractExpression<E> secondOperand, OperationType<E> type) {
        super(firstOperand, secondOperand);
        this.type = type;
    }

    @Override
    public String getOperationLabel() {
        return operationLabel;
    }

    @Override
    protected E applyOperation(E first, E second) {
        return type.subtract(first, second);
    }
}
