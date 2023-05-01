package ui.panel;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ui.button.ACButton;
import ui.button.DigitButton;
import ui.button.DotButton;
import ui.button.EqualButton;
import ui.button.OperatorButton;
import ui.button.SignalButton;
import ui.expressionTextField.ExpressionTextField;

import java.awt.Insets;

public class SingleCalc extends JPanel {
	
	private static final long serialVersionUID = -4333356285946715767L;
	
	private JLabel resultLabel;
	private ExpressionTextField expressionTextField;

	/**
	 * Create the panel.
	 */
	public SingleCalc() {
		setBackground(java.awt.Color.BLACK);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{26, 26, 26, 26, 26, 26, 26, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// 结果框 - 表达式的最后一个数 or 表达式计算结果（如果点击"="）
		resultLabel = new JLabel("0");
		resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		resultLabel.setForeground(Color.WHITE);
		resultLabel.setFont(new java.awt.Font("方正姚体", 1, 80));// Times New Roman
		GridBagConstraints gbc_resultLabel = new GridBagConstraints();
		gbc_resultLabel.gridwidth = 4;
		gbc_resultLabel.gridheight = 4;
		gbc_resultLabel.fill = GridBagConstraints.BOTH;
		gbc_resultLabel.gridx = 0;
		gbc_resultLabel.gridy = 0;
		add(resultLabel, gbc_resultLabel);
		resultLabel.setPreferredSize(new Dimension(0, 26));
		
		// 表达式框 - 显示表达式 保证合法
		expressionTextField = new ExpressionTextField();
		expressionTextField.setBackground(Color.black);
		expressionTextField.setBorder(new EmptyBorder(0,0,0,0));
		expressionTextField.setForeground(Color.WHITE);
		expressionTextField.setFont(new java.awt.Font("方正姚体", 1, 30));
		GridBagConstraints gbc_equationTextField = new GridBagConstraints();
		gbc_equationTextField.gridwidth = 4;
		gbc_equationTextField.insets = new Insets(0, 16, 0, 0);
		gbc_equationTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_equationTextField.gridx = 0;
		gbc_equationTextField.gridy = 4;
		gbc_equationTextField.weightx = 50;
		add(expressionTextField, gbc_equationTextField);
		expressionTextField.setPreferredSize(new Dimension(0, 26));
		
		// All Clear (AC)
		ACButton acBtn = new ACButton(this);
//		ImageIcon icon=new ImageIcon("src/rsc/imgs/btn_AC.png");
//		icon.setImage(icon.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));// 图片大小 目前 20*20
//		acBtn.setIcon(icon);
		GridBagConstraints gbc_acBtn = new GridBagConstraints();
		gbc_acBtn.gridx = 0;
		gbc_acBtn.gridy = 5;
		add(acBtn, gbc_acBtn);
		
		// 正负 signal
		SignalButton sigBtn = new SignalButton(this);
//		ImageIcon iconSg=new ImageIcon("src/rsc/imgs/btn_sig.png");
//		iconSg.setImage(iconSg.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));// 图片大小 目前 20*20
//		sigBtn.setIcon(iconSg);
		GridBagConstraints gbc_sigBtn = new GridBagConstraints();
		gbc_sigBtn.gridx = 1;
		gbc_sigBtn.gridy = 5;
		add(sigBtn, gbc_sigBtn);
		
		// 数字
		
		DigitButton digBtn7 = new DigitButton("7", this );
//		ImageIcon icon7=new ImageIcon("src/rsc/imgs/btn_7.png");
//		icon7.setImage(icon7.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn7.setIcon(icon7);
		GridBagConstraints gbc_digBtn7 = new GridBagConstraints();
		gbc_digBtn7.gridx = 0;
		gbc_digBtn7.gridy = 6;
		add(digBtn7, gbc_digBtn7);
		
		DigitButton digBtn8 = new DigitButton("8", this);
//		ImageIcon icon8=new ImageIcon("src/rsc/imgs/btn_8.png");
//		icon8.setImage(icon8.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn8.setIcon(icon8);
		GridBagConstraints gbc_digBtn8 = new GridBagConstraints();
		gbc_digBtn8.fill = GridBagConstraints.NONE;
		gbc_digBtn8.gridx = 1;
		gbc_digBtn8.gridy = 6;
		add(digBtn8, gbc_digBtn8);
		
		DigitButton digBtn9 = new DigitButton("9", this);
//		ImageIcon icon9=new ImageIcon("src/rsc/imgs/btn_9.png");
//		icon9.setImage(icon9.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn9.setIcon(icon9);
		GridBagConstraints gbc_digBtn9 = new GridBagConstraints();
		gbc_digBtn9.gridx = 2;
		gbc_digBtn9.gridy = 6;
		add(digBtn9, gbc_digBtn9);
		
		DigitButton digBtn4 = new DigitButton("4", this);
//		ImageIcon icon4=new ImageIcon("src/rsc/imgs/btn_4.png");
//		icon4.setImage(icon4.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn4.setIcon(icon4);
		GridBagConstraints gbc_digBtn4 = new GridBagConstraints();
		gbc_digBtn4.gridx = 0;
		gbc_digBtn4.gridy = 7;
		add(digBtn4, gbc_digBtn4);
		
		DigitButton digBtn5 = new DigitButton("5", this);
//		ImageIcon icon5=new ImageIcon("src/rsc/imgs/btn_5.png");
//		icon5.setImage(icon5.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn5.setIcon(icon5);
		GridBagConstraints gbc_digBtn5 = new GridBagConstraints();
		gbc_digBtn5.gridx = 1;
		gbc_digBtn5.gridy = 7;
		add(digBtn5, gbc_digBtn5);
		
		DigitButton digBtn6 = new DigitButton("6", this);
//		ImageIcon icon6=new ImageIcon("src/rsc/imgs/btn_6.png");
//		icon6.setImage(icon6.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn6.setIcon(icon6);
		GridBagConstraints gbc_digBtn6 = new GridBagConstraints();
		gbc_digBtn6.gridx = 2;
		gbc_digBtn6.gridy = 7;
		add(digBtn6, gbc_digBtn6);
		
		DigitButton digBtn1 = new DigitButton("1", this);
//		ImageIcon icon1=new ImageIcon("src/rsc/imgs/btn_1.png");
//		icon1.setImage(icon1.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn1.setIcon(icon1);
		GridBagConstraints gbc_digBtn1 = new GridBagConstraints();
		gbc_digBtn1.gridx = 0;
		gbc_digBtn1.gridy = 8;
		add(digBtn1, gbc_digBtn1);
		
		DigitButton digBtn2 = new DigitButton("2", this);
//		ImageIcon icon2=new ImageIcon("src/rsc/imgs/btn_2.png");
//		icon2.setImage(icon2.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn2.setIcon(icon2);
		GridBagConstraints gbc_digBtn2 = new GridBagConstraints();
		gbc_digBtn2.gridx = 1;
		gbc_digBtn2.gridy = 8;
		add(digBtn2, gbc_digBtn2);
		
		DigitButton digBtn3 = new DigitButton("3", this);
//		ImageIcon icon3=new ImageIcon("src/rsc/imgs/btn_3.png");
//		icon3.setImage(icon3.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		digBtn3.setIcon(icon3);
		GridBagConstraints gbc_digBtn3 = new GridBagConstraints();
		gbc_digBtn3.gridx = 2;
		gbc_digBtn3.gridy = 8;
		add(digBtn3, gbc_digBtn3);
		
		DigitButton digBtn0 = new DigitButton("0", this);
//		ImageIcon icon0=new ImageIcon("src/rsc/imgs/btn_0.png");
//		icon0.setImage(icon0.getImage().getScaledInstance(210,83,Image.SCALE_SMOOTH));
//		digBtn0.setIcon(icon0);
		GridBagConstraints gbc_digBtn0 = new GridBagConstraints();
		gbc_digBtn0.fill = GridBagConstraints.HORIZONTAL;
		gbc_digBtn0.gridwidth = 2;
		gbc_digBtn0.gridx = 0;
		gbc_digBtn0.gridy = 9;
		add(digBtn0, gbc_digBtn0);
		
		// dot (point)
		DotButton dotButton = new DotButton(this);
//		ImageIcon iconDot=new ImageIcon("src/rsc/imgs/btn_dot.png");
//		iconDot.setImage(iconDot.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));
//		dotButton.setIcon(iconDot);
		GridBagConstraints gbc_dotButton = new GridBagConstraints();
		gbc_dotButton.gridx = 2;
		gbc_dotButton.gridy = 9;
		add(dotButton, gbc_dotButton);
		
		// operators
		OperatorButton divBtn = new OperatorButton("/", this, "div");
		GridBagConstraints gbc_divBtn = new GridBagConstraints();
		gbc_divBtn.gridx = 3;
		gbc_divBtn.gridy = 5;
		add(divBtn, gbc_divBtn);
		
		OperatorButton mulBtn = new OperatorButton("*", this, "mul");
		GridBagConstraints gbc_mulBtn = new GridBagConstraints();
		gbc_mulBtn.gridx = 3;
		gbc_mulBtn.gridy = 6;
		add(mulBtn, gbc_mulBtn);
		
		OperatorButton subBtn = new OperatorButton("-", this, "sub");
		GridBagConstraints gbc_subBtn = new GridBagConstraints();
		gbc_subBtn.gridx = 3;
		gbc_subBtn.gridy = 7;
		add(subBtn, gbc_subBtn);
		
		OperatorButton addBtn = new OperatorButton("+", this, "add");
		GridBagConstraints gbc_addBtn = new GridBagConstraints();
		gbc_addBtn.gridx = 3;
		gbc_addBtn.gridy = 8;
		add(addBtn, gbc_addBtn);
		
		// '%' 表示 mod
		OperatorButton modBtn = new OperatorButton("%", this, "mod");
		GridBagConstraints gbc_modBtn = new GridBagConstraints();
		gbc_modBtn.gridx = 2;
		gbc_modBtn.gridy = 5;
		add(modBtn, gbc_modBtn);
		
		// 等号
		EqualButton equBtn = new EqualButton(this);
//		ImageIcon iconEqu = new ImageIcon("src/rsc/imgs/btn_equal.png");
//		iconEqu.setImage(iconEqu.getImage().getScaledInstance(94, 83, Image.SCALE_SMOOTH));
//		equBtn.setIcon(iconEqu);
		GridBagConstraints gbc_equBtn = new GridBagConstraints();
		gbc_equBtn.gridx = 3;
		gbc_equBtn.gridy = 9;
		add(equBtn, gbc_equBtn);
	}

	public JLabel getResultLabel() {
		return resultLabel;
	}

	public void setResultLabel(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}

	public ExpressionTextField getExpressionTextField() {
		return expressionTextField;
	}

	public void setExpressionTextField(ExpressionTextField equationTextField) {
		this.expressionTextField = equationTextField;
	}
}
