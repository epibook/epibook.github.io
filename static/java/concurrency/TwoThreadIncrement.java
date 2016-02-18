
//@include
class IncrementThread implements Runnable {
  public void run() {
    for (int i = 0 ; i < TwoThreadIncrement.N; i++) {
      TwoThreadIncrement.counter++;
    }
  }
}

public class TwoThreadIncrement {
  public static int counter;
  public static int N;

  public static void main(String[] args) throws Exception {

    N = (args.length > 0) ? new Integer(args[0]) : 100;

    Thread T1 = new Thread( new IncrementThread() );
    Thread T2 = new Thread( new  IncrementThread() );

    T1.start(); T2.start();
    T1.join(); T2.join();

    System.out.println(counter);
  }
}
//@exclude

