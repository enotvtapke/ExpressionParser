package expression.parser;

import expression.*;
import expression.generic.types.OperationType;

enum TokenType {
    NAME,
    NUMBER,
    PLUS,
    MINUS,
    MUL,
    DIV,
    LP,
    RP,
    END,
    AND,
    OR,
    XOR,
    ABS,
    SQRT
}

public class ExpressionParser<E extends Number> extends MyBaseParser implements MyParser {
    private OperationType<E> mode;

    public ExpressionParser(final MyCharSource source) {
        super(source);
        nextChar();
    }

    public ExpressionParser(OperationType<E> mode) {
        this(new MyStringSource(""));
        this.mode = mode;
    }

    public ExpressionParser(final MyCharSource source, OperationType<E> mode) {
        this(source);
        this.mode = mode;
    }

    public TokenType getTokenType() {
        skipWhitespace();
        switch (ch) {
            case (0) :
                return TokenType.END;
            case ('+') :
                return TokenType.PLUS;
            case ('-') :
                return TokenType.MINUS;
            case ('*') :
                return TokenType.MUL;
            case ('/') :
                return TokenType.DIV;
            case ('(') :
                return TokenType.LP;
            case (')') :
                return TokenType.RP;
            case ('&') :
                return TokenType.AND;
            case ('|') :
                return TokenType.OR;
            case ('^') :
                return TokenType.XOR;
            case ('a') :
                expect("abs");
                if (between('a', 'z') || (between('A', 'Z'))) {
                    throw error("Invalid token type");
                }
                return TokenType.ABS;
            case ('s') :
                expect("sqrt");
                if (between('a', 'z') || (between('A', 'Z'))) {
                    throw error("Invalid token type");
                }
                return TokenType.SQRT;
            default:
                if (between('0', '9')) {
                    return TokenType.NUMBER;
                } else if (between('a', 'z') || (between('A', 'Z'))) {
                    return TokenType.NAME;
                }
                throw error("Invalid token type");
        }
    }

    private AbstractExpression<E> parseExpression() {
        final AbstractExpression<E> result = expression(null);
        if (eof()) {
            return result;
        }
        throw error("End of expression expected");
    }

    private AbstractExpression<E> primary() {
        switch (getTokenType()) {
            case NUMBER :
                return parseNumber(false);
            case MINUS :
                nextChar();
                skipWhitespace();
                if (between('0', '9')) {
                    return parseNumber(true);
                }
                return new CheckedNegate<>(primary(), mode);
            case NAME:
                return parseVariable();
            case LP :
                nextChar();
                AbstractExpression<E> exp = expression(null);
                skipWhitespace();
                expect(')');
                return exp;
            default:
                throw error("Primary expected, found: '" + ch + "'");
        }
    }

    private AbstractExpression<E> term(AbstractExpression<E> left) {
        if (left == null) {
            left = primary();
        }
        switch (getTokenType()) {
            case MUL:
                nextChar();
                return term(new CheckedMultiply<>(left, primary(), mode));
            case DIV:
                nextChar();
                return term(new CheckedDivide<>(left, primary(), mode));
            default:
                return left;
        }
    }

    private AbstractExpression<E> expression(AbstractExpression<E> left) {
        if (left == null) {
            left = term(null);
        }

        switch (getTokenType()) {
            case PLUS :
                nextChar();
                return expression(new CheckedAdd<>(left, term(null), mode));
            case MINUS:
                nextChar();
                return expression(new CheckedSubtract<>(left, term(null), mode));
            default:
                return left;
        }
    }

    private AbstractExpression<E> parseVariable() {
        if (test('x')) {
            return new Variable<>("x", mode);
        } else if (test('y')) {
            return new Variable<>("y", mode);
        } else if (test('z')) {
            return new Variable<>("z", mode);
        } else {
            throw error("Wrong variable name");
        }
    }

    private AbstractExpression<E> parseNumber(boolean negative) {
        final StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }
        if (test('-')) {
            sb.append('-');
        }
        skipWhitespace();
        if (between('0', '9')) {
            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }
        } else {
            throw error("Invalid number");
        }
        try {
            /*
            return switch (mode.getClass()) {
                case IntegerType.class -> new Const<>(Integer.parseInt(sb.toString()));
                case IntegerType.class -> new Const<>(Double.parseDouble(sb.toString()));
                case IntegerType.class -> new Const<>(BigInteger.valueOf(Integer.parseInt(sb.toString())));
                case IntegerType.class -> new Const<>(Long.parseLong(sb.toString()));
                case IntegerType.class -> new Const<>(Short.parseShort(sb.toString()));
            };*/
            return new Const<>(mode.parse(sb.toString()));
        } catch (NumberFormatException e) {
            throw error("Invalid number");
        }
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }

    public AbstractExpression<E> parse(final String expression) {
        return new ExpressionParser<E>(new MyStringSource(expression), mode).parseExpression();
    }
}
