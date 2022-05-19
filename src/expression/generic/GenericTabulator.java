package expression.generic;

import expression.AbstractExpression;
import expression.exceptions.EvaluateException;
import expression.exceptions.ParseException;
import expression.parser.ExpressionParser;
import expression.generic.types.*;

public class GenericTabulator implements Tabulator {
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Object[][][] res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

        ExpressionParser<?> parser =
                switch (mode) {
                    case "i" -> new ExpressionParser<>(new IntegerType());
                    case "d" -> new ExpressionParser<>(new DoubleType());
                    case "bi" -> new ExpressionParser<>(new BigIntegerType());
                    case "u" -> new ExpressionParser<>(new UnsignedType());
                    case "l" -> new ExpressionParser<>(new LongType());
                    case "s" -> new ExpressionParser<>(new ShortType());
                    default -> throw new EvaluateException("Invalid mode");
                };
        AbstractExpression<?> parsed = parser.parse(expression);

        for (int i = 0; i <= x2 - x1; i++) {
            for (int j = 0; j <= y2 - y1; j++) {
                for (int k = 0; k <= z2 - z1; k++) {
                    try {
                        res[i][j][k] = parsed.evaluate(i + x1, j + y1, k + z1);
                    } catch (ParseException | EvaluateException e) {
                        res[i][j][k] = null;
                    }
                }
            }
        }
        return res;
    }
}
