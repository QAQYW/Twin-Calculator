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

public class DeleteButton extends JButton implements ChangeText {
	
	private static final long serialVersionUID = 7699165271205734224L;
	private static int BUTTON_CODE = 5;
	
	public DeleteButton(SingleCalc leftCalc, SingleCalc rightCalc) {
		super();
		this.setBackground(Color.black);
		this.setBorderPainted(false);
		this.addActionListener(new ClickListener(leftCalc, rightCalc, 0));
		java.net.URL url = getClass().getResource("/rsc/imgs/btn_del.png");
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(94, 83, Image.SCALE_SMOOTH));
		this.setIcon(icon);
	}
	
	class ClickListener implements ActionListener {
		private SingleCalc leftCalc;
		private SingleCalc rightCalc;
		private int calcFocus;
		public ClickListener(SingleCalc leftCalc, SingleCalc rightCalc, int calcFocus) {
			this.leftCalc = leftCalc;
			this.rightCalc = rightCalc;
			this.calcFocus = calcFocus;
		}
		public void setCalcFocus(int calcFocus) {
			this.calcFocus = calcFocus;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boolean flag = false;
			switch (calcFocus) {
			case 0:// left
				if (leftCalc.getExpressionTextField().getText().length() > 0)
					flag = true;
				changeText(leftCalc);
				break;
			case 1:// right
				if (rightCalc.getExpressionTextField().getText().length() > 0)
					flag = true;
				changeText(rightCalc);
				break;
//			default:
//				System.out.println("Lose focus!");
			}
			try {
				if (flag) {
					PlayMusic.playButtonMusic();
				} else {
					PlayMusic.playWrongMusic();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		};
	}

	@Override
	public void changeText(SingleCalc panel) {
		// TODO Auto-generated method stub
		ExpressionTextField eprText = panel.getExpressionTextField();
		boolean flag = eprText.isAvailable("", BUTTON_CODE);
		if (flag) {
			if (eprText.getText().length() == 0) {
				panel.getResultLabel().setText("0");
			} else {
				boolean zeroFlag = false;
				String epr = eprText.getText();
				while (ExpressionTextField.getLastNumber(epr).equals(ExpressionTextField.NOT_A_NUMBER)) {
					epr = epr.substring(0, epr.length() - 1);
					if (epr.length() == 0) {
						zeroFlag = true;
						break;
					}
				}
				if (zeroFlag) {
					panel.getResultLabel().setText("0");
				} else {
					String number = ExpressionTextField.getLastNumber(epr);
					String signal = "";
					if (!ExpressionTextField.isPositive(number)) {
					signal = "-";
					number = ExpressionTextField.getAbs(number);
				}
				if (!number.equals("."))
					panel.getResultLabel().setText(signal + number);
				}
			}
		}
	}
	
	public void setCalcFocus(int calcFocus) {
		ActionListener[] listeners = this.getActionListeners();
		ClickListener listener = (ClickListener) listeners[0];
		listener.setCalcFocus(calcFocus);
	}
}
