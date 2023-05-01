package ui.button;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ui.panel.SingleCalc;
import ui.music.PlayMusic;

public class OperatorButton extends JButton implements ChangeText {
	
	private static final long serialVersionUID = 8100507604479955831L;
	private static int BUTTON_CODE = 3;
	private String eText;

	public OperatorButton(String eText, SingleCalc calc, String opName) {
		super();
		this.eText = eText;
		this.setBackground(Color.black);
		this.setBorderPainted(false);
		this.addActionListener(new ClickListener(calc));
		String path = "/rsc/imgs/btn_" + opName + ".png";
		java.net.URL url = getClass().getResource(path);
//		System.out.println(opName + ": " + path);
//		try {
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(94, 83, Image.SCALE_SMOOTH));
		this.setIcon(icon);
//		} catch (Exception e) {
//			// TODO: handle exception
//
//			System.out.println(opName + ": " + path);
//		}
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
		panel.getExpressionTextField().isAvailable(eText, BUTTON_CODE);
//		System.out.println("test operator");
	}
}
