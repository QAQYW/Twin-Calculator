package ui.expressionTextField;


import javax.swing.JTextField;

public class ExpressionTextField extends JTextField {
	
	private static final long serialVersionUID = 8192688215039466020L;
	public static String NOT_A_NUMBER = "NAN";// （expression的后缀）不是数字
	public static String OVERFLOW = "OVERFLOW";// 溢出
	public static String DIVIDE_BY_ZERO = "DIV#0";// 被0除
	public static String MODULE_BY_ZERO = "MOD#0";// 被0取模
	
	private static boolean negativeNumberFlag = false;// 当前数字是否负数
	private static boolean numberBorderFlag = false;// 当前数字（负数）是否被括号包围
	
	public static int MAX_BITS = 9;// 最大位长
	
	public ExpressionTextField() {
		super();
	}
	
	public boolean isAvailable(String eText, int btnCode) {
		switch (btnCode) {
		// 0 is AC button 
		case 1:// digit
			return isDigitAvailable(eText);
		case 2:// dot
			return isDotAvailable();
		case 3:// operator
			return isOperatorAvailable(eText);
		case 4:// signal
			return isSignalAvailable();
		case 5:// delete
			return isDeleteAvailable();
		case 6:// equal
			return isEqualAvailable();
		default:
			break;
		}
		return false;
	}
	
	public static boolean isNumber(char ch) {
		if (Character.isDigit(ch))
			return true;
		if (ch == '.')
			return true;
		if (ch == '(') {
			numberBorderFlag = true;
			return true;
		}
		if (ch == ')') {
			negativeNumberFlag = true;
			return true;
		}
		if (ch == '-' && negativeNumberFlag)
			return true;
		return false;
	}
	
	private static int countDigit(String number) {
		int len = number.length();
		int count = 0;
		for (int i = 0; i < len; i++)
			if (Character.isDigit(number.charAt(i)))
				count++;
		return count;
	}
	
	public String getLastNumber() {
		return getLastNumber(getText());
	}
	
	public static String getLastNumber(String epr) {
		negativeNumberFlag = false;
		numberBorderFlag = false;
		int pos = epr.length();
		if (!isNumber(epr.charAt(pos - 1)) || epr.charAt(pos - 1) == '-')
			return NOT_A_NUMBER;
		while (pos - 1 >= 0 && !numberBorderFlag) {
			if (pos - 1 == 0)
				negativeNumberFlag = true;
			if (isNumber(epr.charAt(pos - 1)))
				pos--;
			else break;
		}
		return epr.substring(pos);
	}
	
	public static boolean isPositive(String number) {
		return !number.contains("-");
	}
	
	public static String getAbs(String number) {
		if (number.charAt(0) == '(')
			number = number.substring(1, number.length() - 1);
		if (number.charAt(0) == '-')
			number = number.substring(1);
		return number;
	}
	
	private boolean isDigitAvailable(String eText) {
		if (getText().length() == 0) {
			setText(eText);
			return true;
		}
		String number = getLastNumber();
		if (number.equals(NOT_A_NUMBER)) {
			setText(getText() + eText);
			return true;
		}
		if (number.equals("0")) {
			if (eText.equals("0"))
				return false;
			String text = getText();
			int len = text.length();
			text = text.substring(0, len - 1) + eText;
			setText(text);
			return true;
		}
		if (number.equals("(-0)")) {
			if (eText.equals("0"))
				return false;
			String text = getText();
			int len = text.length();
			text = text.substring(0, len - 4) + "(-" + eText + ")";
			setText(text);
			return true;
		}
		int bits = countDigit(number);
		if (bits < MAX_BITS) {
			if (isPositive(number)) {
				setText(getText() + eText);
			} else {
				String text = getText();
				int len = text.length();
				text = text.substring(0, len - number.length());
				number = getAbs(number);
				text = text + "(-" + number + eText + ")";
				setText(text);
			}
			return true;
		}
		return false;
	}
	
	private boolean isDotAvailable() {
		if (getText().length() == 0) {
			setText(".");
			return true;
		}
		String number = getLastNumber();
		if (number.equals(NOT_A_NUMBER)) {
			setText(getText() + ".");
			return true;
		}
		if (number.contains("."))
			return false;
		int bits = countDigit(number);
		if (bits < MAX_BITS) {
			if (isPositive(number)) {
				setText(getText() + ".");
			} else {
				String text = getText();
				int len = text.length();
				text = text.substring(0, len - 1) + ".)";
				setText(text);
			}
			return true;
		}
		return false;
	}
	
	private boolean isOperatorAvailable(String eText) {
		if (getText().length() == 0) {
			if (eText.equals("-")) {
				setText("-");
				return true;
			}
			return false;
		}
		if (getText().equals("-")) {
			if (eText.equals("-"))
				return false;
			setText("");
			return true;
		}
		String number = getLastNumber();
		if (number.equals(NOT_A_NUMBER)) {
			String text = getText();
			int len = text.length();
			text = text.substring(0, len - 1) + eText;
			setText(text);
			return true;
		}
		setText(getText() + eText);
		return true;
	}
	
	private boolean isSignalAvailable() {
		if (getText().length() == 0) {
			return false;
		}
		String number = getLastNumber();
		if (number.equals(NOT_A_NUMBER))
			return false;
		if (isPositive(number)) {
			// 正数 变 负数
			// 还需要判断是否等式只有这一个数字，以判断要不要加括号
			String text = getText();
			int len = text.length();
			text = text.substring(0, len - number.length());
			if (text.length() == 0)
				text = text + "-" + number;
			else
				text = text + "(-" + number + ")";
			setText(text);
			return true;
		} else {
			// 负数 变 正数
			String text = getText();
			int len = text.length();
			text = text.substring(0, len - number.length());
			text = text + getAbs(number);
			setText(text);
			return true;
		}
	}
	
	private boolean isDeleteAvailable() {
		if (getText().length() == 0)
			return false;
		String number = getLastNumber();
		if (number.equals(NOT_A_NUMBER)) {
			String text = getText();
			text = text.substring(0, text.length() - 1);
			setText(text);
			return true;
		}
		// is a number
		if (isPositive(number)) {
			String text = getText();
			text = text.substring(0, text.length() - 1);
			setText(text);
			return true;
		}
		if (number.charAt(0) == '(') {
			// format: (-xx.xxxx)
			String absNumber = getAbs(number);
			String newNumber = "";
			int len = number.length();
			int absLen = absNumber.length();
			if (len == 4)
				newNumber = "";
			else
				newNumber = "(-" + absNumber.substring(0, absLen - 1) + ")";
			String text = getText();
			text = text.substring(0, text.length() - len) + newNumber;
			setText(text);
			return true;
		}
		// else:
		// format: -xx.xxxx
		String absNumber = getAbs(number);
		String newNumber = "";
		int len = number.length();
		int absLen = absNumber.length();
		if (len == 2)
			newNumber = "";
		else
			newNumber = "-" + absNumber.substring(0, absLen - 1);
		String text = getText();
		text = text.substring(0, text.length() - len) + newNumber;
		setText(text);
		return true;
	}
	
	private boolean isEqualAvailable() {
		if (getText().length() == 0) {
			return false;
		}
		String text = getText();
		if (text.contains("=")) {
			int len = text.length();
			int pos = len - 1;
			while (text.charAt(pos) != '=')
				pos--;
			text = text.substring(pos + 1, len);
		}
		String number = getLastNumber(text);
		if (number.equals(NOT_A_NUMBER)) {
			text = text.substring(0, text.length() - 1) + "=";
			setText(text);
			return true;
		}
		setText(text + "=");
//		System.out.println(getText());
		return true;
	}
}
