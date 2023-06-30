package SaAssingment.example.demo.Test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MathExpressionEvaluator {
    public static void main(String[] args) {
        int maxRequests = 50;
        ExpressionEvaluator evaluator = new ExpressionEvaluator(maxRequests);
        List<String> expressions = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expressions (one per line, 'end' to finish):");

        while (true) {
            String expression = scanner.nextLine().trim();
            if (expression.equals("end")) {
                break;
            }
            expressions.add(expression);
        }

        System.out.println("Evaluating expressions...");

        for (String expression : expressions) {
            String result = evaluator.evaluateExpression(expression);
            System.out.println(expression + " => " + result);
        }

        scanner.close();
    }
}
