import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BiggestProductNMinus1 {
  // @include
  public static int findBiggestProductNMinusOneProduct(List<Integer> A) {
    // Builds suffix products.
    int product = 1;
    List<Integer> suffixProducts
        = new ArrayList<>(Collections.nCopies(A.size(), 0));
    for (int i = A.size() - 1; i >= 0; --i) {
      product *= A.get(i);
      suffixProducts.set(i, product);
    }

    // Finds the biggest product of (n - 1) numbers.
    int prefixProduct = 1;
    int maxProduct = Integer.MIN_VALUE;
    for (int i = 0; i < A.size(); ++i) {
      int suffixProduct = i + 1 < A.size() ? suffixProducts.get(i + 1) : 1;
      maxProduct = Math.max(maxProduct, prefixProduct * suffixProduct);
      prefixProduct *= A.get(i);
    }
    return maxProduct;
  }
  // @exclude

  // n^2 checking.
  private static int checkAns(List<Integer> A) {
    int maxProduct = Integer.MIN_VALUE;
    for (int i = 0; i < A.size(); ++i) {
      int product = 1;
      for (int j = 0; j < i; ++j) {
        product *= A.get(j);
      }
      for (int j = i + 1; j < A.size(); ++j) {
        product *= A.get(j);
      }
      if (product > maxProduct) {
        maxProduct = product;
      }
    }
    return maxProduct;
  }

  private static void check(List<Integer> A) {
    int got = findBiggestProductNMinusOneProduct(A);
    int expected = checkAns(A);
    if (got != expected) {
      System.err.println("Your program computed the wrong result for input "
                         + A);
      System.err.println("Expected " + expected);
      System.err.println("Got " + got);
      System.exit(-1);
    }
  }

  private static void directedTest() {
    check(Arrays.asList(1, 2, 3));
    check(Arrays.asList(3, 2, 1));
    check(Arrays.asList(1, 1, 1));
    check(Arrays.asList(-1, 1, 1));
    check(Arrays.asList(-1, 1, -1));
    check(Arrays.asList(-10, 12, -1001));
    check(Arrays.asList(12, -10, 12, -1001));
    check(Arrays.asList(1));
  }

  private static void stressTest() {
    int N = 100000;
    List<Integer> A = new ArrayList<>(Collections.nCopies(N, new Integer(1)));
    if (findBiggestProductNMinusOneProduct(A) != 1) {
      System.err.println(
          "Your program computed the wrong result for an array consisting of "
          + N + " 1s.");
      System.exit(-1);
    }
  }

  public static void main(String[] args) {
    directedTest();
    stressTest();
    Random gen = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(10) + 2;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        A.add(gen.nextInt(19) - 9);
        System.out.print(A.get(i) + " ");
      }
      System.out.println();
      int res = findBiggestProductNMinusOneProduct(A);
      assert res == checkAns(A);
      System.out.println(res);
    }
  }
}
