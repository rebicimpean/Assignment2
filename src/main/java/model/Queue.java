package model;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.Controller;
import view.CheckOutsFrame;

public class Queue implements Runnable {

	private int id;
	private boolean open;
	private PriorityBlockingQueue<Client> clients;
	private int emptyTime;
	private static int totalNrOfClients;
	private int totalWaitingTime;
	private int totalServiceNeed; 
	private CheckOutsFrame frame;
	private int simulationTime;

	public Queue(int id, int timer) {
		this.id = id;
		this.simulationTime = timer;
		open = true;
		emptyTime = 0;
		totalNrOfClients = 0;
		totalWaitingTime = 0;
		totalServiceNeed = 0;
		clients = new PriorityBlockingQueue<Client>();
		frame = new CheckOutsFrame(this.id);
	}

	public synchronized void openQueue() {
		open = true;
	}

	public synchronized void closeQueue() {
		open = false;
	}

	public boolean isOpen() {
		if (open == true)
			return true;
		else
			return false;
	}

	public int getEmptyTime() {
		return emptyTime;
	}

	public int getTotalWaitingTime() {
		return totalWaitingTime;
	}

	public int getTotalServiceNeed() {
		return totalServiceNeed;
	}

	public int getQueueId() {
		return id;
	}

	public void run() {
		while (open) {
			if (clients.size() == 0) {
				try {
					Thread.sleep(1000);
					emptyTime++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				int auxServiceNeed = clients.peek().getServiceNeed();
				int auxId = clients.peek().getId();
				int auxTime = clients.peek().getServiceTime() + clients.peek().getServiceNeed();
				//System.out.println("auxTime is " + auxTime + "state of the queue is" + open);
				//System.out.println("auxServiceNeed is " + auxServiceNeed);
				//System.out.println("auxId is " + auxId);

				totalWaitingTime += clients.peek().getWaitingTime();
				totalServiceNeed += clients.peek().getServiceNeed();
				try {
					for (Client c : clients) {
						c.setWaitingTime(c.getWaitingTime() + auxServiceNeed);
					}
					Thread.sleep(clients.peek().getServiceNeed() * 1000);
					if (clients.size() > 0) {
						frame.updateLog("<br>Client with ID " + clients.peek().getId() + " is leaving. waited for "
								+ clients.poll().getWaitingTime());
					}
					//System.out.println(
						//	"in Queue Total nr of Clients is: " + totalNrOfClients + " in Queue" + getQueueId());

					if (auxTime > simulationTime + 15) {
						//System.out.println(
							//	"in Queue Total nr of Clients is: " + totalNrOfClients + " in Queue" + getQueueId());
						closeQueue();
					//	System.out.println("auxTime is " + auxTime + "state of the queue is" + open);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Queue Thread" + this.getQueueId() + " is Terminated");
	}

	public synchronized void addClient(Client c) {
		this.clients.add(c);
		this.totalNrOfClients++;
		frame.updateLog(c.toString());
	}
}
