package ui.button;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ui.panel.SingleCalc;
import ui.music.PlayMusic;

public class ACButton extends JButton implements ChangeText {
	
	private static final long serialVersionUID = 3686799991195602652L;
	@SuppressWarnings("unused")
	private static int BUTTON_CODE = 0;
	private boolean clearFlag = false;
	
	public ACButton(SingleCalc calc) {
		super();
		this.setBackground(Color.black);
		this.setBorderPainted(false);
		this.addActionListener(new ClickListener(calc));
		java.net.URL url = getClass().getResource("/rsc/imgs/btn_ac.png");
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
				if (!clearFlag)
					PlayMusic.playButtonMusic();
				else
					PlayMusic.playWrongMusic();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void changeText(SingleCalc panel) {
		// TODO Auto-generated catch block
		if (panel.getExpressionTextField().getText().length() == 0) {
			clearFlag = true;
		} else {
			clearFlag = false;
		}
		panel.getExpressionTextField().setText("");
		panel.getResultLabel().setText("0");
	}
}
