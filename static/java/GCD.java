import java.math.BigInteger;
import java.util.Random;

public class GCD {
  private static long gcdGolden(long a, long b) {
    BigInteger b1 = BigInteger.valueOf(a);
    BigInteger b2 = BigInteger.valueOf(b);
    BigInteger gcd = b1.gcd(b2);
    return gcd.longValue();
  }

  private static void check(long a, long b) {
    assert GCD1.GCD(a, b) == gcdGolden(a, b);
  }

  public static void main(String[] args) {
    check(2L, 2L);
    check(2L, 3L);
    check(17L, 289L);
    check(13L, 17L);
    check(17L, 289L);
    check(18L, 24L);
    check(1024L * 1023L, 1023L * 1025L);
    check(317L * 1024L * 1023L, 317L * 1023L * 1025L);
    check(Long.MAX_VALUE, Long.MAX_VALUE - 1L);
    check(Long.MAX_VALUE - 1L, (Long.MAX_VALUE - 1L) / (2L));
    check(0L, 0L);
    check(0L, 1L);
    check(10L, 100L);
    long x = 18, y = 12;
    assert(GCD1.GCD(x, y) == 6);
    if (args.length == 2) {
      x = Integer.parseInt(args[0]);
      y = Integer.parseInt(args[1]);
      System.out.println(GCD1.GCD(x, y));
      assert(GCD1.GCD(x, y) == GCD2.GCD(x, y));
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        x = r.nextInt(Integer.MAX_VALUE);
        y = r.nextInt(Integer.MAX_VALUE);
        assert(GCD1.GCD(x, y) == GCD2.GCD(x, y));
      }
    }
  }
}
