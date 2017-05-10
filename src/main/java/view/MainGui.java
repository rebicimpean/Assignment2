package view;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;

import controller.Controller;
import controller.RandomClientGenerator;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;


public class MainGui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField minArrInterval;
	private JTextField maxArrInterval;
	private JTextField minServiceTime;
	private JTextField maxServiceTime;
	private JTextField nrOfQueues;
	private JTextField simulationTime;
	private JButton btnStart;
	private JFrame frame;
	private Controller controller;

	public MainGui() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Queue Based Managing System");
		this.setSize(550, 400);
		this.setVisible(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		getContentPane().setBackground(Color.CYAN);
		getContentPane().setLayout(null);
		getContentPane().setBounds(0, 0, 550, 400);

		JLabel lblArrivingInterval = new JLabel("Interval of arriving time between customers");
		lblArrivingInterval.setBounds(10, 11, 278, 14);
		getContentPane().add(lblArrivingInterval);

		minArrInterval = new JTextField();
		minArrInterval.setBounds(10, 36, 62, 20);
		getContentPane().add(minArrInterval);
		minArrInterval.setColumns(10);

		maxArrInterval = new JTextField();
		maxArrInterval.setBounds(111, 36, 62, 20);
		getContentPane().add(maxArrInterval);
		maxArrInterval.setColumns(10);

		JLabel lblServingInterval = new JLabel("Interval for service time");
		lblServingInterval.setBounds(10, 72, 216, 14);
		getContentPane().add(lblServingInterval);

		minServiceTime = new JTextField();
		minServiceTime.setBounds(10, 97, 62, 20);
		getContentPane().add(minServiceTime);
		minServiceTime.setColumns(10);

		maxServiceTime = new JTextField();
		maxServiceTime.setBounds(111, 97, 62, 20);
		getContentPane().add(maxServiceTime);
		maxServiceTime.setColumns(10);

		JLabel lblNoOfQueues = new JLabel("Give number of queues");
		lblNoOfQueues.setBounds(10, 128, 216, 14);
		getContentPane().add(lblNoOfQueues);

		nrOfQueues = new JTextField();
		nrOfQueues.setBounds(10, 153, 62, 20);
		getContentPane().add(nrOfQueues);
		nrOfQueues.setColumns(10);

		JLabel lblGiveSimulationInterval = new JLabel("Give simulation interval");
		lblGiveSimulationInterval.setBounds(10, 184, 216, 14);
		getContentPane().add(lblGiveSimulationInterval);
		
		simulationTime = new JTextField();
		simulationTime.setBounds(10, 210, 62, 20);
		getContentPane().add(simulationTime);
		simulationTime.setColumns(10);

		JTextArea infoText1 = new JTextArea();
		infoText1.setForeground(new Color(0, 0, 128));
		infoText1.setBackground(Color.GRAY);
		infoText1.setEditable(false);
		infoText1.setFont(new Font("Monospaced", Font.PLAIN, 12));
		infoText1.setText("For arrival time\r\nand for\r\nservice time \r\ngive the values\r\nin seconds!\r\n\r\n\r\nmaximum number\r\nof queues is 5\r\n\r");
		infoText1.setBounds(350, 11, 137, 266);
		getContentPane().add(infoText1);

		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnStart.setBounds(10, 254, 62, 23);
		getContentPane().add(btnStart);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnStart) {
			try {
				int aux;
				int minArrival = Integer.parseInt(minArrInterval.getText());
				int maxArrival = Integer.parseInt(maxArrInterval.getText());
				int minST = Integer.parseInt(minServiceTime.getText());
				int maxST = Integer.parseInt(maxServiceTime.getText());
				int nrOfQ = Integer.parseInt(nrOfQueues.getText());
				int simTime = Integer.parseInt(simulationTime.getText());
				
				if (minArrival > maxArrival) {
					aux = minArrival;
					minArrival = maxArrival;
					maxArrival = aux;
				}
				if (minST > maxST) {
					aux = minST;
					minST = maxST;
					maxST = aux;
				}
				if (simTime > 200 || nrOfQ > 5 || nrOfQ < 1) {
					PopUpView view = new PopUpView("<html><br>Incorrect input format<br> Please try again.</html>");
				} else {
					btnStart.setEnabled(false);
					controller = new Controller(simTime,minArrival,maxArrival,minST, maxST, nrOfQ);
					Thread t = new Thread(controller);
					t.start();
			    	RandomClientGenerator rand = new RandomClientGenerator(simTime,minArrival,maxArrival,minST, maxST,controller);
			    	Thread randThread = new Thread(rand);
			    	randThread.start();
			    	
				}
			} catch (Exception e) {
				PopUpView view = new PopUpView(e.getMessage());
			}
		}
	}
}
