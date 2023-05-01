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

public class DigitButton extends JButton implements ChangeText {
	
	private static final long serialVersionUID = -8244741425699293122L;

	private static int BUTTON_CODE = 1;
	
	private String eText;

	public DigitButton(String eText, SingleCalc calc) {
		super();
		this.eText = eText;
		this.setBackground(Color.black);
		this.setBorderPainted(false);
		this.addActionListener(new ClickListener(calc));
		String path = "/rsc/imgs/btn_" + eText + ".png";
		java.net.URL url = getClass().getResource(path);
		ImageIcon icon = new ImageIcon(url);
		int width = 94;
		if (eText.charAt(0) == '0')
			width = 210;
		icon.setImage(icon.getImage().getScaledInstance(width, 83, Image.SCALE_SMOOTH));
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
				PlayMusic.playButtonMusic();
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
		if (flag) {
			String number = eprText.getLastNumber();
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