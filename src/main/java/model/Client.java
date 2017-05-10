package model;

public class Client implements Comparable<Client> {

	private int arrivalTime;
	private int serviceTime;
	private int serviceNeed;
	private int waitingTime;
	private int id;

	public Client(int arrivalT, int serviceT, int serviceN, int id) {
		setArrivalTime(arrivalT);
		setServiceTime(serviceT);
		setServiceNeed(serviceN);
		setId(id);
		setWaitingTime(0);

	}

	private void setArrivalTime(int arrivalT) {
		this.arrivalTime = arrivalT;
	}

	private void setServiceTime(int serviceT) {
		this.serviceTime = serviceT;
	}

	private void setServiceNeed(int serviceN) {
		this.serviceNeed = serviceN;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setWaitingTime(int waitingT) {
		this.waitingTime = waitingT;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public int getServiceNeed() {
		return serviceNeed;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Client  with id " + this.id + ", arrival time is " + this.arrivalTime + ", service time is "
				+ this.serviceTime + ", service need is " + serviceNeed;
	}

	public int compareTo(Client c) {
		if (serviceTime > c.serviceTime)
			return 1;
		else if (serviceTime == c.serviceTime)
			return 0;
		else
			return -1;

	}
}