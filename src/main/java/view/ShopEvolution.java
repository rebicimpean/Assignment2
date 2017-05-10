package view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class ShopEvolution extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;
	private JLabel lblShop;
	private String s;
	private int displayTimer;
	private JLabel lblTime;
	private Thread timerThread;
	private boolean stopTimer;
	private int simulationTime;

	public ShopEvolution(String message, int time, int simTime) {
		s="Currently shop is empty";
		simulationTime = simTime;
		timerThread = new Thread(this);
		timerThread.start();
		stopTimer = false;
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Display Shop Evolution");
		this.setSize(700, 1000);
		this.setVisible(false);
		this.setLayout(null);
		this.setLocation(20, 20);

		getContentPane().setBackground(Color.MAGENTA);
		getContentPane().setLayout(null);
		getContentPane().setBounds(0, 0, 700, 800);
		
		lblShop = new JLabel(" ");
		lblShop.setFont(new Font("SansSerif", Font.ITALIC, 20));
		lblShop.setBounds(5, 10, 650, 700);
		lblShop.setText("<html> " + s +" </html>" );
		getContentPane().add(lblShop);
		
		lblTime = new JLabel("");
		lblTime.setFont(new Font("Sans Serif", Font.ITALIC, 40));
		lblTime.setBounds(50,500, 670, 800);
		lblTime.setText(((Integer)this.displayTimer).toString());
		this.add(lblTime);
		
		lblShop.setVisible(true);
		lblTime.setVisible(true);
		this.setVisible(true);
	}

	public void updateShop(String client) {
		s += "<br>" + client;
		lblShop.setText("<html> " + s +" </html>" );
	}

	public void updateTimer(int timer) {
		this.displayTimer = timer;
		lblTime.setText(((Integer) timer).toString());
	}
	
	public synchronized void stopTimer()
	{
		stopTimer = true;
	}
	public void run() {
		while(displayTimer < simulationTime )
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			displayTimer++;
			updateTimer(displayTimer);
		}
		
	}
}
