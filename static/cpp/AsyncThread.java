class Requestor {
  public String execute(String req) {
    return "response:" + req;
  }
  public String error(String req) {
    return "response:" + req + ":" + "TIMEDOUT";
  }
  // @include
  public String execute(String req, long delay) {
    try {
      // simulate the time taken to perform a computation
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      return error(req);
    }
    return execute(req);
  }
  // @exclude
  public void ProcessResponse(String response) {
    System.out.println("ProcessResponse:" + response);
    return;
  }
}

public class AsyncThread {
  public static final long TIMEOUT = 500L;

  public static void main(String [] args) {
    Dispatch(new Requestor(), "t1", 1000L);
    Dispatch(new Requestor(), "t2", 100L);
    Dispatch(new Requestor(), "t3", 10L);
    Dispatch(new Requestor(), "t4", 1L);
    Dispatch(new Requestor(), "t5", 200L);
  }

  // @include
  public static void Dispatch(final Requestor requestor, final String request,
                              final long delay) {
    Runnable task = new Runnable() {
      public void run() {
        Runnable actualTask = new Runnable() {
          public void run() {
            String response = requestor.execute(request, delay);
            requestor.ProcessResponse(response);
          }
        };
        Thread innerThread = new Thread(actualTask);
        innerThread.start();
        try {
          Thread.sleep(TIMEOUT);
          innerThread.interrupt();
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    new Thread(task).start();
  }
  // @exclude
}
