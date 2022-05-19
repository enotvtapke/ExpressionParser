package expression;

import expression.generic.types.OperationType;

public class CheckedNegate<E> extends AbstractUnaryOperation<E> {
    protected final String operationLabel = "-";
    private final OperationType<E> type;

    public CheckedNegate(AbstractExpression<E> operand, OperationType<E> type) {
        super(operand);
        this.type = type;
    }

    @Override
    public String getOperationLabel() {
        return operationLabel;
    }

    protected E applyOperation(E first) {
        return type.negate(first);
    }
}
