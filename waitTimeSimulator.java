/*Source for getPoissonRandom used in donutShop.java: 
 * https://stackoverflow.com/questions/9832919/generate-poisson-arrival-in-java
 * 
 * Author: Matthew Bertrand
 */

/* The purpose of this project is to simulate a business that has a flow of customers.
 * The user can select the maximum time they want customers to wait, and how many servers
 * they would like to employ. The user can also select the average customers (per minute)
 * that arrive in the store. 
 * All of the values can be changed for user input but i've chosen
 * to use hard coded values for easier testing.
 * */
public class waitTimeSimulator {
	// Explination of the variables in depth are located in "README.md"
	public static void main(String[] args) {
		donutShop ds = new donutShop();
		ds.maxServiceTime = 3; // service time max
		ds.poissonMean = 1; // average customer arrivals per minute
		ds.numberOfServers = 2; // number of servers
		ds.ticks = 30;
		ds.tickTimer();
	}
}
