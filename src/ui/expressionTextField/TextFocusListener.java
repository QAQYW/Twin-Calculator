package ui.expressionTextField;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import ui.button.DeleteButton;

public class TextFocusListener implements FocusListener {
	
	private int calcIndex;
	private DeleteButton delBtn;
	
	public TextFocusListener(int calcIndex, DeleteButton delBtn) {
		this.calcIndex = calcIndex;
		this.delBtn = delBtn;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		delBtn.setCalcFocus(calcIndex);
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
	}
	
}
