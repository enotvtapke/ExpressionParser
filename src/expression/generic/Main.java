package expression.generic;

import expression.exceptions.EvaluateException;
import expression.exceptions.ParseException;
import expression.generic.types.DoubleType;
import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpressionParser<Double> parser = new ExpressionParser<>(new DoubleType());
        String expression = sc.next();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        try {
            System.out.println(parser.parse(expression).evaluate(x, y, z));
        } catch (ParseException | EvaluateException e) {
            System.out.println(e.getMessage());
        }
    }
}
