package com.epi;

public class AsyncThread {
  public static class Request {
    String request;

    public Request(String request) { this.request = request; }

    public String execute(String req) {
      return "response:" + req.toLowerCase();
    }

    public String error(String req) {
      return "response:" + req + ":error - TIMEDOUT";
    }

    // @include
    // delay is used to simulate the time for computation by a dispatched
    // thread.
    public String execute(String req, long delay) {
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        return error(req);
      }
      return execute(req);
    }
    // @exclude
    public void processResponse(String response) {
      System.out.println("processResponse():" + response);
      return;
    }
  }

  public static class AsyncThreadDriver {
    public static final long TIMEOUT = 500L;

    public static void main(String[] args) throws InterruptedException {
      dispatch(new Request("Tb1"), 1000L);
      dispatch(new Request("Ta2"), 100L);
      dispatch(new Request("tc3"), 10L);
      dispatch(new Request("T4"), 1L);
      dispatch(new Request("TT5"), 200L);
    }

    // @include

    public static void dispatch(final Request requestor, final long delay) {
      Runnable task = new Runnable() {

        public void run() {
          Runnable actualTask = new Runnable() {
            public void run() {
              String response = requestor.execute(requestor.request, delay);
              requestor.processResponse(response);
            }
          };

          Thread innerThread = new Thread(actualTask);
          innerThread.start();

          try {
            Thread.sleep(TIMEOUT);
            innerThread.interrupt();
          } catch (InterruptedException e) {
            System.out.println("Request was interrupted: " + e);
          }
        }
      };
      new Thread(task).start();
    }
    // @exclude
  }
}
