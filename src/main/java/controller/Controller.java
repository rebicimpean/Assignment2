package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Client;
import model.Queue;
import view.CheckOutsFrame;
import view.MainGui;
import view.PopUpView;
import view.ShopEvolution;

public class Controller extends JFrame implements Runnable {

	private volatile ArrayList<Client> shopQueue;
	private ArrayList<Queue> checkOuts;
	private int simulationTime;
	protected int timer;
	private int minArrivalTime;
	private int maxArrivalTime;
	private int minServiceTime;
	private int maxServiceTime;
	private int nrOfCheckOuts;
	private int totalNoOfClients;
	private int clientID;
	private boolean runThread;

	public Controller(int simulationT, int minArrT, int maxArrT, int minSerT, int maxSerT, int noOfCheckOuts) {
		this.simulationTime = simulationT;
		this.minArrivalTime = minArrT;
		this.maxArrivalTime = maxArrT;
		this.minServiceTime = minSerT;
		this.maxServiceTime = maxSerT;
		this.nrOfCheckOuts = noOfCheckOuts;
		this.timer = 0;
		this.setTotalNoOfClients(0);
		this.shopQueue = new ArrayList<Client>();
		this.setCheckOuts(new ArrayList<Queue>());
		this.runThread = true;

		for (int i = 0; i < this.nrOfCheckOuts; i++) {
			Queue queue = new Queue(i, simulationTime);
			Thread queueThread = new Thread(queue);
			queueThread.start();
			this.checkOuts.add(queue);
		}
	}

	public void run() {
		while (this.timer < this.simulationTime + 15) {
			try {
				Thread.sleep(1000);
				timer++;
				System.out.println(timer);
				Collections.sort(shopQueue);
				for (Client c : shopQueue) {
					if (c.getServiceTime() <= this.timer) {
						Random r = new Random();
						int index = r.nextInt(nrOfCheckOuts);
						checkOuts.get(index).addClient(c);
						shopQueue.remove(c);
					}
					break;
				}
				boolean allQueuesAreClosed = true;
				for (Queue q : this.checkOuts)
					if (q.isOpen()) {
						allQueuesAreClosed = false;
					}
				if (allQueuesAreClosed)
					setRunThread(false);
				else
					setRunThread(true);
			} catch (InterruptedException e) {
				PopUpView view = new PopUpView(e.toString());
				e.printStackTrace();
			}
		}	
		{
			double totalWaitingTime = 0;
			double totalServiceNeed = 0; 
			double totalEmptyTime = 0;
			int peakTime = 0;
			for (Queue q : checkOuts)
			{
				totalWaitingTime += q.getTotalWaitingTime();
				totalServiceNeed += q.getTotalServiceNeed();
				totalEmptyTime += q.getEmptyTime();
				peakTime = q.getTotalWaitingTime() - q.getTotalServiceNeed();
			}
		;
		System.out.println("Average waiting time was: " + String.format("$%,.2f", totalWaitingTime/this.totalNoOfClients));
		System.out.println("Average service time was: " + totalServiceNeed/this.totalNoOfClients );
		System.out.println("Average empty time was: " + totalEmptyTime/this.nrOfCheckOuts );
		
		
		System.out.println("Total waiting time was: " + totalWaitingTime);
		System.out.println("Total serving time was: " + totalServiceNeed);
		System.out.println("Peak time was: " + peakTime);
		}
		System.out.println("Controller Thread is Terminated");
	}

	public ArrayList<Queue> getCheckOuts() {
		return checkOuts;
	}

	public void setCheckOuts(ArrayList<Queue> checkOuts) {
		this.checkOuts = checkOuts;
	}

	public ArrayList<Client> getShopQueue() {
		return shopQueue;
	}

	public void setShopQueue(ArrayList<Client> shopQueue) {
		this.shopQueue = shopQueue;
	}

	public int getTotalNoOfClients() {
		return totalNoOfClients;
	}

	public synchronized void setTotalNoOfClients(int totalNoOfClients) {
		this.totalNoOfClients = totalNoOfClients;
	}

	public synchronized void setRunThread(boolean set) {
		runThread = set;
	}
}
