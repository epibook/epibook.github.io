import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FirstMissingPositive {
  private static int checkAns(List<Integer> A) {
    Collections.sort(A);
    int target = 1;
    for (int a : A) {
      if (a > 0) {
        if (a == target) {
          ++target;
        } else if (a > target) {
          return target;
        }
      }
    }
    return target;
  }

  // @include
  public static int findFirstMissingPositive(List<Integer> A) {
    // Record which values are present by writing A.get(i) to index A.get(i) - 1
    // if A.get(i) is between 1 and A.size(), inclusive. We save the value at
    // index A.get(i) - 1 by swapping it with the entry at i. If A.get(i) is
    // negative or greater than n, we just advance i.
    for (int i = 0; i < A.size(); ++i) {
      while (0 < A.get(i) && A.get(i) <= A.size()
             && A.get(A.get(i) - 1) != A.get(i)) {
        Collections.swap(A, i, A.get(i) - 1);
      }
    }

    // Second pass through A to search for the first index i such that A.get(i)
    // != i + 1, indicating that i + 1 is absent. If all numbers between 1 and
    // A.size() are present, the smallest missing positive is A.size() + 1.
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) != i + 1) {
        return i + 1;
      }
    }
    return A.size() + 1;
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(500001);
      }
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(n + 1));
      }
      System.out.println("n = " + n);
      findFirstMissingPositive(A);
      assert(findFirstMissingPositive(A) == checkAns(A));
    }
  }
}
