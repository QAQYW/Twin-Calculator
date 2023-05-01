package ui.button;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ui.panel.SingleCalc;
import ui.music.PlayMusic;

public class DotButton extends JButton implements ChangeText {
	
	private static final long serialVersionUID = 6482237963641745663L;
	private static int BUTTON_CODE = 2;
	private String eText;

	public DotButton(SingleCalc calc) {
		super();
		this.eText = ".";
		this.setBackground(Color.black);
		this.setBorderPainted(false);
		this.addActionListener(new ClickListener(calc));
		java.net.URL url = getClass().getResource("/rsc/imgs/btn_dot.png");
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
	}
}
