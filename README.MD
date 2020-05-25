The main class in this project is the "waitTimeSimulator.java" file. If you wish to chnage any value
they are all labeled within the main method. 

**Variable explinations:**

**maxWaitTime:** This variable sets the desired max wait time. This means a server will try to help the customer before this time.
the server will try but if overworked, will extend this time as a real server would.

**ticks:** This number is simply how many ticks you want to run the program for. A tick is equal to one real minute in 
simulation time. So, 30 ticks in the program = 30 minutes in real life.

**poissonMean:** This is a variable used in the possionMean method inside donutShop.java. It controls how often your shop will recieve customers. This method can vary on output however much like a real business. For example, if you put in 1, you might expect between 20-30 customers in a 30 minute time period. This is done on purpose to simulate a real business.

**numberOfServers:** This controls the number of servers you would like to simulate. The program does not dynamically create servers. I designed this program not to create servers dynamically as needed because in real life, you would not have the option to spawn another server. If I had designed this program using machien learning, dynamic would be a way better option.
