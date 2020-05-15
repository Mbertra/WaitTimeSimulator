
/*Source for getPoissonRandom used in donutShop.java: 
 * https://stackoverflow.com/questions/9832919/generate-poisson-arrival-in-java
 * 
 * Author: Matthew Bertrand
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class donutShop{
	private Queue<customers> q = new LinkedList<>(); // queue of customers of type customers
	private customers customer;
	private Server server;
	Random r = new Random(); 
	private Queue<Server> serverLL = new LinkedList<>(); // queue of servers
	int numberOfServers; // number of servers to use
	double poissonMean; // so we can set the value from main
	int maxServiceTime; // so we can set variable from main
	int serviceCompleted; // used to check how many customers with completed service
	double totalWaitTime; // used to calculate total time waited by customers
	int beingHelped; // track the number of customers being helped currently
	int minWait = 0; // tracks the min wait time
	int maxWait = 0; // tracks max wait time
	double customersWhoWaited; // numbers of customers who waited in the que
	double averageWait; // average waittime of the customers who entered que
	int ticks = 20;
	/*
	 * This method assumes 1 tick is 1 minute, each customer not being served gets
	 * +1 second to their current wait time counter and getPoisson is calculating
	 * how many customers to add each tick, and then passing that value into
	 * addCustomers method.
	 * 
	 * This method also calls server(), which assigns a server to the head element
	 * in the queue and removes it.
	 */
	public void tickTimer() {
		addServer(); // deploy the servers before we begin
		// time tick is 1 minute
		for (int i = 0; i < ticks; i++) {

			// sets the wait time of customers still waiting +1 minute
			Iterator<customers> itr = q.iterator();
			while (itr.hasNext() == true) {

				customer = itr.next();

				customer.setWaitTime(customer.getWaitTime() + 1);
				totalWaitTime++;
			}

			// adds customers, calls to assign server and then displays
			addCustomers(getPoissonRandom(poissonMean));
			server();
			output(i + 1);

		}

	}

	private void output(int tickNum) {
		System.out.println("Tick #" + tickNum);
		System.out.println("Number customers being helped: " + beingHelped);
		System.out.println("Customers who have completed service: " + serviceCompleted);
		System.out.println("Customers still in queue: " + q.size());
		System.out.println("Total wait time: " + totalWaitTime);
		System.out.println("Min wait time: " + minWait);
		averageWait = totalWaitTime / customersWhoWaited; // calculate average wait
		System.out.printf("Average wait time: %.2f\n", averageWait);
		System.out.println("Maximum wait time: " + maxWait);
		System.out.println();//spacer

	}

	// generate customer with a name (we will make their name the index), initial
	// wait time, and a random service time.
	public void addCustomers(int n) {

		for (int i = 0; i < n; i++) {
			int addCustomer = r.nextInt((maxServiceTime - 1) + 1) + 1; // random number between 1 and max
			customer = new customers(q.size(), 0, addCustomer);
			q.add(customer);
			customersWhoWaited++;
		}

	}

	// random number generator to calculate the number of customer to add called
	// each tick
	// called by tickTimer().
	private int getPoissonRandom(double mean) {

		double L = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		do {
			p = p * r.nextDouble();
			k++;
		} while (p > L);
		return k - 1;
	}

	// method that adds servers
	public void addServer() {

		for (int i = 0; i < numberOfServers; i++) {
			server = new Server(0, serverLL.size(), null);
			serverLL.add(server);
		}

	}

	// handles servers, releases if they are done with customer and sets to idle,
	// then assigns
	// the server to a new person waiting in the queue if there is anyone.
	public void server() {
		Iterator<Server> itr = serverLL.iterator();
		while (itr.hasNext() && !q.isEmpty()) {
			server = itr.next();
			// check if the server needs to be set to idle, if so
			// clear the server
			if (server.getCustomer() != null && server.customer.getServiceTime() == 0) {
				beingHelped--; // decrement number of customers being helped.
				server.setCustomer(null);
				server.setStatus(0);
				serviceCompleted++;
			}
			// if the server is idle (status 0) assign a server to a customer
			if (server.getStatus() == 0 && !q.isEmpty()) {
				server.setStatus(1);
				server.setCustomer(q.poll());
				waitTime(); // calculates min wait time when served
				beingHelped++; // amount of customers currently being helped
			}
		} // decrement service time left for each customer being served
		Iterator<Server> itr2 = serverLL.iterator();
		while (itr2.hasNext()) {
			server = itr2.next();
			if (server.customer != null && server.customer.getServiceTime() > 0) {
				server.customer.setServiceTime(server.customer.getServiceTime() - 1);
			}
		}
	}

	// updates and keeps track of the wait times min/max
	private void waitTime() {
		// check for first iteration to set value of minWait
		if (minWait == 0) {
			minWait = server.getCustomer().getWaitTime();
		} // if min wait is more than current wait time, make new minWait
		else if (server.getCustomer().getWaitTime() < minWait) {
			minWait = server.getCustomer().getWaitTime();
		}
		// track the max wait time
		if (maxWait == 0) {
			maxWait = server.getCustomer().getWaitTime();
		} // if the current customers wait has been longer than max, make new max
		else if (server.getCustomer().getWaitTime() > maxWait) {
			maxWait = server.getCustomer().getWaitTime();
		}

	}
}
