package ui.application;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.button.DeleteButton;
import ui.button.MoveButton;
import ui.expressionTextField.TextFocusListener;
import ui.panel.SingleCalc;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TwinCalcApp extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JPanel middlePanel;
	private SingleCalc leftCalc;
	private SingleCalc rightCalc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TwinCalcApp frame = new TwinCalcApp();
					frame.setSize(1150, 650);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public TwinCalcApp() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		setTitle("Twin-Calc");
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeel);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// left calculator
		leftCalc = new SingleCalc();
		GridBagConstraints gbc_leftCalc = new GridBagConstraints();
		gbc_leftCalc.anchor = GridBagConstraints.NORTH;
		gbc_leftCalc.weightx = 1;
		gbc_leftCalc.gridx = 0;
		gbc_leftCalc.gridy = 0;
		gbc_leftCalc.gridheight = 7;
		contentPane.add(leftCalc, gbc_leftCalc);
		
		// 中间的panel, 包括3个button
		middlePanel = new JPanel();
		middlePanel.setBackground(Color.BLACK);
		GridBagConstraints gbc_movePanel = new GridBagConstraints();
		gbc_movePanel.anchor = GridBagConstraints.NORTH;
		gbc_movePanel.insets = new Insets(5, 5, 0, 5);
		gbc_movePanel.gridx = 1;
		gbc_movePanel.gridy = 0;
		gbc_movePanel.gridheight = 7;
		contentPane.add(middlePanel, gbc_movePanel);
		
		// right calculator
		rightCalc = new SingleCalc();
		GridBagConstraints gbc_rightCalc = new GridBagConstraints();
		gbc_rightCalc.anchor = GridBagConstraints.NORTH;
		gbc_rightCalc.gridx = 2;
		gbc_rightCalc.gridy = 0;
		gbc_rightCalc.gridheight = 7;
		contentPane.add(rightCalc, gbc_rightCalc);
		
		// 初始化middlePanel里的3个button
		initMiddlePanel();
	}
	
	private void initMiddlePanel() {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl.rowHeights = new int[]{26, 26, 26, 26, 26, 26, 26, 0};
		gbl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	
		middlePanel.setLayout(gbl);
		
		// 传递值的键 <-
		MoveButton movLBtn = new MoveButton(rightCalc, leftCalc, "left");
//		ImageIcon iconL=new ImageIcon("src/rsc/imgs/btn_left.png");
//		iconL.setImage(iconL.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));// 设置图片大小 目前 20*20
//		movLBtn.setIcon(iconL);
		GridBagConstraints gbc_movLBtn = new GridBagConstraints();
		gbc_movLBtn.insets = new Insets(4, 0, 0, 0);
		gbc_movLBtn.gridx = 0;
		gbc_movLBtn.gridy = 5;
		middlePanel.add(movLBtn, gbc_movLBtn);
		
		// 传递值的键 ->
		MoveButton movRBtn = new MoveButton(leftCalc, rightCalc, "right");
//		ImageIcon iconR=new ImageIcon("src/rsc/imgs/btn_right.png");
//		iconR.setImage(iconR.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));// 设置图片大小 目前 20*20
//		movRBtn.setIcon(iconR);
		GridBagConstraints gbc_movRBtn = new GridBagConstraints();
		gbc_movRBtn.insets = new Insets(0, 0, 182, 0);
		gbc_movRBtn.gridx = 0;
		gbc_movRBtn.gridy = 6;
		middlePanel.add(movRBtn, gbc_movRBtn);
		
		// delete button
		DeleteButton delBtn = new DeleteButton(leftCalc, rightCalc);
//		ImageIcon iconD=new ImageIcon("src/rsc/imgs/btn_del.png");
//		iconD.setImage(iconD.getImage().getScaledInstance(94,83,Image.SCALE_SMOOTH));// 设置图片大小 目前 20*20
//		delBtn.setIcon(iconD);
		GridBagConstraints gbc_delBtn = new GridBagConstraints();
		gbc_delBtn.gridx = 0;
		gbc_delBtn.gridy = 7;
		middlePanel.add(delBtn, gbc_delBtn);
		
		// 向两个expressionTextField中添加listener
		TextFocusListener leftTFL = new TextFocusListener(0, delBtn);
		leftCalc.getExpressionTextField().addFocusListener(leftTFL);
		TextFocusListener rightTFL = new TextFocusListener(1, delBtn);
		rightCalc.getExpressionTextField().addFocusListener(rightTFL);
	}
}
