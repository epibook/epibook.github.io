import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FibonacciIterative {
  // @include
  public static int fibonacci(int n) {
    if (n <= 1) {
      return n;
    }

    int fMinus2 = 0;
    int fMinus1 = 1;
    for (int i = 2; i <= n; ++i) {
      int f = fMinus2 + fMinus1;
      fMinus2 = fMinus1;
      fMinus1 = f;
    }
    return fMinus1;
  }
  // @exclude

  private static void smallTest() {
    assert(fibonacci(10) == 55);
    assert(fibonacci(0) == 0);
    assert(fibonacci(1) == 1);
    assert(fibonacci(16) == 987);
    assert(fibonacci(40) == 102334155);
  }

  public static void main(String[] args) {
    smallTest();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      Random gen = new Random();
      n = gen.nextInt(41);
    }
    System.out.println("F(" + n + ") = " + fibonacci(n));
  }
}
