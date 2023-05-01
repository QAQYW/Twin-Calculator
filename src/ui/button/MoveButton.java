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

public class MoveButton extends JButton implements ChangeText {

	private static final long serialVersionUID = 4862732451199616848L;
	private SingleCalc calcFrom;
	private SingleCalc calcTo;
	
	public MoveButton(SingleCalc calcFrom, SingleCalc calcTo, String direction) {
		super();
		this.calcFrom = calcFrom;
		this.calcTo = calcTo;
		this.setBackground(Color.black);
		this.setBorderPainted(false);
		this.addActionListener(new ClickListener());
		String path = "/rsc/imgs/btn_" + direction + ".png";
		java.net.URL url = getClass().getResource(path);
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(94, 83, Image.SCALE_SMOOTH));
		this.setIcon(icon);
	}
	
	class ClickListener implements ActionListener {
		public ClickListener() {}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			changeText(null);
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
		String numFrom = calcFrom.getResultLabel().getText();
		
		if (numFrom.equals(ExpressionTextField.NOT_A_NUMBER))
			return;
		if (numFrom.equals(ExpressionTextField.OVERFLOW))
			return;
		
		String eprText = calcTo.getExpressionTextField().getText();
		
		if (eprText.length() == 0) {
			calcTo.getExpressionTextField().setText(numFrom);
			calcTo.getResultLabel().setText(numFrom);
			return;
		}
		
		String lastNum = calcTo.getExpressionTextField().getLastNumber();
		if (ExpressionTextField.isPositive(numFrom)) {
			// >= 0
			if (lastNum.equals(ExpressionTextField.NOT_A_NUMBER)) {
				// 最后一个不是数字，直接添加在最后
				eprText = eprText + numFrom;
				calcTo.getExpressionTextField().setText(eprText);
				calcTo.getResultLabel().setText(numFrom);
			} else {
				// 最后一个是数字，将该数字替换为传输过去的数字 numFrom
				eprText = eprText.substring(0, eprText.length() - lastNum.length());
				eprText = eprText + numFrom;
				calcTo.getExpressionTextField().setText(eprText);
				calcTo.getResultLabel().setText(numFrom);
			}
		} else {
			// < 0
			if (lastNum.equals(ExpressionTextField.NOT_A_NUMBER)) {
				// 最后一个不是数字，直接添加在最后
				eprText = eprText + "(" + numFrom + ")";
				calcTo.getExpressionTextField().setText(eprText);
				calcTo.getResultLabel().setText(numFrom);
			} else {
				// 最后一个是数字，将该数字替换为传输过去的数字 numFrom
				// 但还需要根据这个等式是不是只有一个数字，来讨论是否加括号
				eprText = eprText.substring(0, eprText.length() - lastNum.length());
				if (eprText.length() > 0) {
					eprText = eprText + "(" + numFrom + ")";
				} else {
					eprText = numFrom;
				}
				calcTo.getExpressionTextField().setText(eprText);
				calcTo.getResultLabel().setText(numFrom);
			}
		}
	}
}
