package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUpView extends JFrame {
	private JLabel lblError;
	private JLabel lblErrorText;

	public PopUpView(String message) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Error Message");
		this.setSize(500, 300);
		this.setVisible(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);

		getContentPane().setBackground(Color.CYAN);
		getContentPane().setLayout(null);
		getContentPane().setBounds(0, 0, 300, 50);

		lblError = new JLabel("ERROR MESSAGE");
		lblError.setBounds(0, 0, 500, 50);
		lblError.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblError.setVisible(true);
		lblError.setLocation(20,30);
		lblError.setForeground(Color.RED);
		
		lblErrorText = new JLabel(message);
		lblErrorText.setBounds(0, 0, 500, 100);
		lblErrorText.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblErrorText.setVisible(true);
		lblErrorText.setLocation(20,30);
		lblErrorText.setForeground(Color.BLACK);

		getContentPane().add(lblError);
		getContentPane().add(lblErrorText);
		this.setVisible(true);
	}

}
