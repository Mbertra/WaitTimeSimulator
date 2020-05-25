/*Source for getPoissonRandom used in donutShop.java: 
 * https://stackoverflow.com/questions/9832919/generate-poisson-arrival-in-java
 * 
 * Author: Matthew Bertrand
 */
public class Server extends waitTimeSimulator {
	private int status = 0; // 0 = idle, 1 = active.
	private int serverName; // int value to represent the servers name
	customers customer; // customer from customers class for a server to point to.

	public Server() {

	}

	// each server will have a status, name, and a customer.
	public Server(int sStatus, int sName, customers sCustomer) {
		this.setStatus(sStatus);
		this.setName(sName);
		this.setCustomer(sCustomer);
	}

	public void setStatus(int sStatus) {
		this.status = sStatus;
	}

	public int getStatus() {
		return this.status;
	}

	public void setName(int sName) {
		this.serverName = sName;
	}

	public int getName() {
		return this.serverName;
	}

	public void setCustomer(customers sCustomer) {
		this.customer = sCustomer;
	}

	public customers getCustomer() {
		return this.customer;
	}
}
