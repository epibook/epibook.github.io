import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/*
 * A request class carrying a name for the task, the time it is scheduled for execution
 * and the thread to run.
 *   
 * **/

class Request implements Comparable<Request> {
	String name;
	long time;
	Thread requestingThread;

	public Request(String name, long offset, Thread requestingThread) {
		this.name = name;
		this.time = offset;
		this.requestingThread = requestingThread;
	}

	public int compareTo(Request request) {
		if (this.time < request.time)
			return -1;
		if (this.time > request.time)
			return 1;
		return 0;
	}
}

/*
 * Class holding a list of tasks to simulate the stream of tasks to be passed to
 * the dispatcher hence modifying its status.
 * 
 * **/

class Updater implements Runnable {

	ArrayList<Request> updates;
	Timer dispatcher;

	public Updater(ArrayList<Request> updates, Timer dispatcher) {
		this.updates = updates;
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		for (Request update : updates) {
			synchronized (dispatcher.requests) {
				if (dispatcher.requests.contains(update)) {
					/*
					 * If the Request object is found here, we consider this a
					 * request to cancel its execution (provided it has not yet
					 * started).
					 * 
					 * *
					 */
					System.out.println("Cancelling task " + update.name);
					dispatcher.requests.remove(update);
				} else {
					/*
					 * If the Request object is not in the scheduled requests
					 * and has not been done before, it is sent to the
					 * dispatcher to get scheduled for execution. The dispatcher
					 * is notified to wake up if sleeping.
					 * 
					 * *
					 */
					if (!dispatcher.done.contains(update)) {
						System.out
								.println("Sending to the dispatcher a new Task: "
										+ update.name);
						dispatcher.requests.offer(update);
						dispatcher.requests.notify();
					}
				}
			}
		}
	}

}

/*
 * The main class representing the dispatcher.
 * It contains a heap data structure (PriorityQueue) to hold the tasks in their order of
 * expected execution and a HashSet to hold the tasks already executed.
 * The idea in this implementation is that the updater is passed a list of requests. If a request has not been seen
 * before then it is considered a new request and is passed to the dispatcher for execution.
 * If the request is currently in the heap then it must be cancelled.
 * This kind off changes what is written in the text of the book as to using a min heap + hash map and so on
 * however if that is to be done, the code will become significantly bigger (it is already big).
 * I think this is a point we can discuss together. If you are not satisfied with this implementation model, I can
 * definitely change it :)
 *  
 * **/

public class Timer implements Runnable {

	PriorityQueue<Request> requests = new PriorityQueue<Request>();
	HashSet<Request> done = new HashSet<Request>();

	public Timer(ArrayList<Request> requests) {
		for (Request request : requests)
			this.requests.offer(request);
	}

	public void run() {
		int i = 0;
		/*
		 * This loop is just so that the dispatcher terminates after some time.		 
		 * **/
		while (i < 100000000) {
			++i;
			synchronized (requests) {
				if (!requests.isEmpty()) {									
					while (System.currentTimeMillis() < requests.peek().time) {
						try {
							requests.wait(requests.peek().time
									- System.currentTimeMillis());
						} catch (InterruptedException e) {							
							e.printStackTrace();
						}
					}					
					requests.peek().requestingThread.start();
					done.add(requests.poll());
				}
			}
		}
	}
	
	/*
	 * A test case which runs the dispatcher with two tasks (add and subtract).
	 * It then runs an update with 2 additional tasks (multiply and divide) and with a request to
	 * cancel the subtract task which succeeds.
	 * Playing around with the time passed to each task will change the order of execution of course.
	 * In the case below, addition 1st, multiplication 2nd and last division.
	 * I am willing to discuss any changes you may want and even discuss what parts of the code
	 * to be included in the book since I understand this could be tidious.
	 * **/

	public static void main(String[] args) throws InterruptedException {
		// ---------------------------------------------------------------
		ArrayList<Request> tasks = new ArrayList<Request>();
		Request addThread = new Request("add",
				System.currentTimeMillis() + 3000, new Thread(new Runnable() {

					@Override
					public void run() {
						System.out.println("Addition: " + (10 + 20));
					}
				}));
		Request subtractThread = new Request("subtract",
				System.currentTimeMillis() + 10000, new Thread(new Runnable() {

					@Override
					public void run() {
						System.out.println("Subtraction: " + (70 - 30));

					}
				}));
		tasks.add(addThread);
		tasks.add(subtractThread);
		Timer timer = new Timer(tasks);
		Thread dispatcher = new Thread(timer); // dispatcher
		// ----------------------------------------------------------------
		ArrayList<Request> newTasks = new ArrayList<Request>();
		Request multiplyThread = new Request("multiply",
				System.currentTimeMillis() + 3200, new Thread(new Runnable() {

					@Override
					public void run() {
						System.out.println("Multiplication: " + (10 * 20));
					}
				}));
		Request divideThread = new Request("divide",
				System.currentTimeMillis() + 11000, new Thread(new Runnable() {

					@Override
					public void run() {
						System.out.println("Division: " + (20 / 2));

					}
				}));
		newTasks.add(multiplyThread);
		newTasks.add(divideThread);
		newTasks.add(subtractThread);
		Thread updater = new Thread(new Updater(newTasks, timer));
		// -------------------------------------------------------------------
		dispatcher.start();
		updater.start();
		dispatcher.join();
		updater.join();
		System.out
				.println("----------------- DONE ----------------------------");
	}
}
