package ui.button;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ui.panel.SingleCalc;
import ui.music.PlayMusic;
import ui.expressionTextField.ExpressionTextField;
import expressionCalculator.InfixExpressionCalculator;

public class EqualButton extends JButton implements ChangeText {
	
	private static final long serialVersionUID = -4121805660128989463L;
	private static int BUTTON_CODE = 6;
	private String eText;
	private boolean correctFlag;
	
	public EqualButton(SingleCalc calc) {
		super();
		this.eText = "=";
		this.setBackground(Color.black);
		this.setBorderPainted(false);
		this.addActionListener(new ClickListener(calc));
		java.net.URL url = getClass().getResource("/rsc/imgs/btn_equal.png");
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(94, 83, Image.SCALE_SMOOTH));
		this.setIcon(icon);
	}
	
	class ClickListener implements ActionListener {
		private SingleCalc calc;
		public ClickListener(SingleCalc calc) {
			this.calc = calc;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			changeText(calc);
			try {
				if (correctFlag) {
					PlayMusic.playCorrectMusic();
				} else {
					PlayMusic.playWrongMusic();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void changeText(SingleCalc panel) {
		// TODO Auto-generated method stub
		ExpressionTextField eprText = panel.getExpressionTextField();
		boolean flag = eprText.isAvailable(eText, BUTTON_CODE);
		correctFlag = flag;
		if (flag) {
			String text = eprText.getText();
			String expression = text.substring(0, text.length() - 1);
			try {
				double answer = InfixExpressionCalculator.calculate(expression);
				String result;// = Double.toString(answer);
				if (Math.floor(answer) == Math.ceil(answer)) {
					result = Integer.toString((int) answer);
				} else {
					result = Double.toString(answer);
					if (result.contains(".") && result.length() > 10) {
						result = result.substring(0, 11) + "..";
					}
				}
				panel.getResultLabel().setText(result);
				eprText.setText(text + result);
			} catch (UnsupportedOperationException e) {
				// TODO: handle exception
				String msg = e.getMessage();
				if (msg.equals(InfixExpressionCalculator.OVERFLOW)) {
					panel.getResultLabel().setText(ExpressionTextField.OVERFLOW);
				} else if (msg.equals(InfixExpressionCalculator.DIVIDE_BY_ZERO)) {
					panel.getResultLabel().setText(ExpressionTextField.DIVIDE_BY_ZERO);
				} else if (msg.equals(InfixExpressionCalculator.MODULE_BY_ZERO)) {
					panel.getResultLabel().setText(ExpressionTextField.MODULE_BY_ZERO);
				}
				eprText.setText(expression);
				correctFlag = false;
			}
		}
		if (correctFlag) {
			String text = eprText.getText();
			int len = text.length(), cnt = 0;
			for (int i = len - 1; i >= 0; i--) {
				if (text.charAt(i) == '=') {
					cnt++;
					if (cnt > 1) {
						text = text.substring(i + 1, len);
						break;
					}
				}
			}
			eprText.setText(text);
		}
	}
}
