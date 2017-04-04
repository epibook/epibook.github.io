import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MaxDifferenceKPairs {
  // @include
  public static double maxKPairsProfits(List<Double> prices, int k) {
    if (k == 0) {
      return 0.0;
    } else if (2 * k >= prices.size()) {
      return unlimitedPairsProfits(prices);
    }
    List<Double> minPrices
        = new ArrayList(Collections.nCopies(k, Double.MAX_VALUE)),
        maxProfits = new ArrayList<>(Collections.nCopies(k, 0.0));
    for (Double price : prices) {
      for (int i = k - 1; i >= 0; --i) {
        maxProfits.set(i,
                       Math.max(maxProfits.get(i), price - minPrices.get(i)));
        minPrices.set(i,
                      Math.min(minPrices.get(i),
                               price - (i > 0 ? maxProfits.get(i - 1) : 0.0)));
      }
    }
    return maxProfits.get(maxProfits.size() - 1);
  }

  private static double unlimitedPairsProfits(List<Double> prices) {
    double profit = 0.0;
    for (int i = 1; i < prices.size(); ++i) {
      profit += Math.max(0.0, prices.get(i) - prices.get(i - 1));
    }
    return profit;
  }
  // @exclude

  // O(n^k) checking answer.
  private static double checkAnsHelper(List<Double> A, int l, int k, int pre,
                                       double ans, double maxAns) {
    double finalMaxAns;
    if (l == k) {
      finalMaxAns = Math.max(maxAns, ans);
    } else {
      finalMaxAns = maxAns;
      for (int i = pre; i < A.size(); ++i) {
        finalMaxAns = checkAnsHelper(
            A, l + 1, k, i + 1, ans + (((l & 1) == 1) ? A.get(i) : -A.get(i)),
            finalMaxAns);
      }
    }

    return finalMaxAns;
  }

  private static double checkAns(List<Double> A, int k) {
    double ans = 0, maxAns = Double.NEGATIVE_INFINITY;

    maxAns = checkAnsHelper(A, 0, 2 * k, 0, ans, maxAns);
    System.out.println("maxAns = " + maxAns);
    return maxAns;
  }

  public static void main(String[] args) {
    Random gen = new Random();

    int n = 30, k = 4;
    // random tests for n = 30, k = 4 for 100 times/
    for (int times = 0; times < 100; ++times) {
      List<Double> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextDouble() * 100.0);
      }

      System.out.println("n = " + n + ", k = " + k);
      System.out.println(maxKPairsProfits(A, k));
      assert(checkAns(A, k) == maxKPairsProfits(A, k));
    }

    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
      k = gen.nextInt(n / 2) + 1;
    } else if (args.length == 2) {
      n = Integer.parseInt(args[0]);
      k = Integer.parseInt(args[1]);
    } else {
      n = gen.nextInt(60) + 1;
      k = gen.nextInt(1 + (n / 10)) + 1;
    }

    List<Double> A = new ArrayList<>();

    for (int i = 0; i < n; ++i) {
      A.add(gen.nextDouble() * 100.0);
    }

    System.out.println("n = " + n + ", k = " + k);
    System.out.println(checkAns(A, k) + ", " + maxKPairsProfits(A, k));
    assert(checkAns(A, k) == maxKPairsProfits(A, k));
  }
}
