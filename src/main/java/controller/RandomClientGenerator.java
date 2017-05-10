package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Client;
import view.PopUpView;
import view.*;

public class RandomClientGenerator implements Runnable {

	private int minArrivalTime;
	private int maxArrivalTime;
	private int minServiceTime;
	private int maxServiceTime;
	public int clientID;
	private int simulationTime;
	private Controller controller;
	private int timer;
	private ShopEvolution shopView;

	public RandomClientGenerator(int simulationT, int minArrT, int maxArrT, int minSerT, int maxSerT,
			Controller controller) {
		this.simulationTime = simulationT;
		this.minArrivalTime = minArrT;
		this.maxArrivalTime = maxArrT;
		this.minServiceTime = minSerT;
		this.maxServiceTime = maxSerT;
		this.clientID = 1;
		this.controller = controller;
		this.timer = 0;
		this.shopView = new ShopEvolution("Empty Shop", controller.timer, simulationTime);
	}

	public void run() {
		while (timer <= this.simulationTime) {
			Random random = new Random();
			int randGenerateClient = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime; 
			try {
				Thread.sleep(randGenerateClient * 1000);
			} catch (InterruptedException e) {
				PopUpView view = new PopUpView(e.toString());
				e.printStackTrace();
			}
			timer += randGenerateClient;
			int randServiceTime = random.nextInt(maxServiceTime - minServiceTime) + minServiceTime + timer;
			int randServiceNeed = random.nextInt(5) + 1;
			Client c = new Client(timer, randServiceTime, randServiceNeed, clientID);
			controller.getShopQueue().add(c);
			controller.setTotalNoOfClients(controller.getTotalNoOfClients() + 1);
			shopView.updateShop(c.toString());
			System.out.println(c);
			clientID++;
		}
		System.out.println("RandomClientGenerator Thread is Terminated");
	}
}
