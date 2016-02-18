public class OddEven {

  //@include
  static class OddEvenMonitor {
    public static final boolean ODD_TURN = true;
    public static final boolean EVEN_TURN = false;
    private boolean turn = ODD_TURN;

    public synchronized void waitTurn(boolean oldTurn) {
      while (turn != oldTurn) {
        try {
          wait();
        } catch(Exception e) {
        }
      }
    }

    public synchronized void toggleTurn() {
      turn ^= true;
      notify();
    }
  }

  static class OddThread extends Thread {
    private final OddEvenMonitor monitor;

    public OddThread(OddEvenMonitor monitor) {
      this.monitor = monitor;
    }
    @Override
      public void run() {
        for (int i = 1; i <= 100; i+=2) {
          monitor.waitTurn(OddEvenMonitor.ODD_TURN);
          System.out.println(i);
          monitor.toggleTurn();
        }
      }
  }

  static class EvenThread extends Thread {
    private final OddEvenMonitor monitor;

    public EvenThread(OddEvenMonitor monitor) {
      this.monitor = monitor;
    }
    @Override
      public void run() {
        for (int i = 2; i <= 100; i+=2) {
          monitor.waitTurn(OddEvenMonitor.EVEN_TURN);
          System.out.println(i);
          monitor.toggleTurn();
        }
      }
  }
  //@exclude

  public static void main (String[] args) {
    OddEvenMonitor monitor = new OddEvenMonitor();
    Thread t1 = new OddThread(monitor);
    Thread t2 = new EvenThread(monitor);
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch(Exception e) {
    }
  } 
}
