/*Source for getPoissonRandom used in donutShop.java: 
 * https://stackoverflow.com/questions/9832919/generate-poisson-arrival-in-java
 * 
 * Author: Matthew Bertrand
 */
//class that is a customer including it's name, wait time, and service time.
//this class will be used in donutShop as an arraylist of type customers.
public class customers extends Server {
	private int waitTime = 0;
	private int name;
	private int serviceTime = 0;

	public customers() {
	}

	// constructor for customer
	public customers(int name, int waitTime, int sTime) {
		this.setName(name);
		this.setWaitTime(waitTime);
		this.setServiceTime(sTime);
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}


	public void setServiceTime(int sTime) {
		this.serviceTime = sTime;

	}

	public int getServiceTime() {
		return serviceTime;
	}

}
