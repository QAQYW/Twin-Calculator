package expressionCalculator;

import java.util.Stack;

public class InfixExpressionCalculator {
	
	private static double MAX_ACCURACY = 1e9;
	public static String OVERFLOW = "Data Overflow!";
	public static String DIVIDE_BY_ZERO = "Cannot divide by zero!";
	public static String MODULE_BY_ZERO = "Cannot module by zero!";
	
	public static double calculate(String expression) throws UnsupportedOperationException {
		if (expression.charAt(0) == '-')
			expression = "0" + expression;
		
		Stack<Double> operandStack = new Stack<Double>();// 操作数
		Stack<Character> operactorStack = new Stack<Character>();// 运算符
		
		int eLen = expression.length();
		for (int i = 0; i < eLen; i++) {
			char ch = expression.charAt(i);
//			if (ch == ' ')
//				continue;
			if (isOperator(ch)) {
				while (!operactorStack.isEmpty() && !operactorStack.isEmpty() && hasPrecedence(ch, operactorStack.peek())) {
					operandStack.push(applyOperator(operandStack.pop(), operandStack.pop(), operactorStack.pop()));
				}
				operactorStack.push(ch);
			} else if (ch == '(') {// 负数 被括号包围 格式: (-xx.xxx)
				ch = expression.charAt(++i);
				double sig = 1.0;
				String operand = "";
				while (i < eLen) {
					if (ch == ')')
						break;
					if (ch == '-') {
						sig = -sig;
						ch = expression.charAt(++i);
					} else {
						operand = operand + ch;
						ch = expression.charAt(++i);
					}
				}
				if (operand.charAt(0) == '.')// 形如 .xxx 的小数
					operand = "0" + operand;
				operandStack.push(sig * Double.parseDouble(operand));
			} else if (isNumber(ch)) {// 正数
				String operand = "";
				while (i < eLen && isNumber(expression.charAt(i))) {
					operand = operand + expression.charAt(i);
					i++;
				}
				i--;
				if (operand.charAt(0) == '.')// 形如 .xxx 的小数
					operand = "0" + operand;
				operandStack.push(Double.parseDouble(operand));
			}
		}
		
		while (!operactorStack.isEmpty()) {
			operandStack.push(applyOperator(operandStack.pop(), operandStack.pop(), operactorStack.pop()));
		}
		return operandStack.pop();
	}
	
	private static boolean isOperator(char ch) {
		return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%');
	}
	
	private static boolean isNumber(char ch) {
		if (Character.isDigit(ch))
			return true;
		if (ch == '.')
			return true;
		return false;
	}
	
	private static boolean hasPrecedence(char op1, char op2) {
//		if (op2 == '(' || op2 == ')')
//			return false;
        if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-'))
            return false;
        return true;
	}
	
	private static double applyOperator(double b, double a, char op) {
		if (Math.abs(a) >= MAX_ACCURACY)
			throw new UnsupportedOperationException(OVERFLOW);
		if (Math.abs(b) >= MAX_ACCURACY)
			throw new UnsupportedOperationException(OVERFLOW);
		double answer = 0;
		switch (op) {
		case '+':
			answer = a + b;
			break;
		case '-':
			answer = a - b;
			break;
		case '*':
			answer = a * b;
			break;
		case '/':
			if (b == 0) {
				throw new UnsupportedOperationException(DIVIDE_BY_ZERO);
			}
			answer = a / b;
			break;
		case '%':
			if (b == 0) {
				throw new UnsupportedOperationException(MODULE_BY_ZERO);
			}
			answer = a % b;
			break;
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + op);
		}
		if (answer >= MAX_ACCURACY) {
			throw new UnsupportedOperationException("Data Overflow!");
		}
		return answer;
	}
	
//	public static void main(String[] args) {
//		String expression = "99999999*999999999";
//		double result = InfixExpressionCalculator.calculate(expression);
//		System.out.println(result);
//		String number = "0.5";
//		System.out.println(Double.parseDouble(number));
//		number = ".66";
//		System.out.println(Double.parseDouble(number));
//		number = "100.";
//		System.out.println(Double.parseDouble(number));
//		number = ".";
//		System.out.println(Double.parseDouble(number));
//	}
}
