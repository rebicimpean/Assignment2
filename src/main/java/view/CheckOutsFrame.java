package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckOutsFrame extends JFrame {

	private JLabel lblInformation;
	private String s;
	private int checkOutID;

	public CheckOutsFrame(int id) {
		s = " ";
		this.checkOutID = id;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("CheckOut with ID: " + this.checkOutID);
		this.setSize(700, 800);
		this.setVisible(false);
		this.setLayout(null);
		this.setLocation(700, 50);
		//this.setLocationRelativeTo(null);

		getContentPane().setBackground(Color.CYAN);
		getContentPane().setLayout(null);
		getContentPane().setBounds(0, 0, 550, 400);

		lblInformation = new JLabel("Empty Queue");
		lblInformation.setBounds(20, 40, 1000, 500);
		lblInformation.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblInformation.setVisible(true);

		getContentPane().add(lblInformation);
		this.setVisible(true);
	}

	public void updateLog(String s) {
		this.s += "<br>" + s;
		lblInformation.setText("<html>" + this.s + "</html>");
	}
}
