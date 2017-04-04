import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FindingMinMax {
  // @include
  private static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }
  }

  public static MinMax findMinMax(List<Integer> A) {
    if (A.size() <= 1) {
      return new MinMax(A.get(0), A.get(0));
    }

    MinMax globalMinMax = MinMax.minMax(A.get(0), A.get(1));
    // Process two elements at a time.
    for (int i = 2; i + 1 < A.size(); i += 2) {
      MinMax localMinMax = MinMax.minMax(A.get(i), A.get(i + 1));
      globalMinMax
          = new MinMax(Math.min(globalMinMax.smallest, localMinMax.smallest),
                       Math.max(globalMinMax.largest, localMinMax.largest));
    }
    // If there is odd number of elements in the array, we still
    // need to compare the last element with the existing answer.
    if ((A.size() % 2) != 0) {
      globalMinMax
          = new MinMax(Math.min(globalMinMax.smallest, A.get(A.size() - 1)),
                       Math.max(globalMinMax.largest, A.get(A.size() - 1)));
    }
    return globalMinMax;
  }
  // @exclude

  private static void simpleTest() {
    List<Integer> A = Arrays.asList(-1, 3, -4, 6, 4, 10, 4, 4, 9);
    MinMax res = findMinMax(A);
    assert(-4 == res.smallest && 10 == res.largest);
    A = Arrays.asList(-1, 3, -4, 6, 4, -12, 4, 4, 9);
    res = findMinMax(A);
    assert(-12 == res.smallest && 9 == res.largest);

    A = Arrays.asList(-1, 3, -4);
    res = findMinMax(A);
    assert(-4 == res.smallest && 3 == res.largest);
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(10000) + 1;
      }
      List<Integer> A = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        A.add(r.nextInt(1000000));
      }
      MinMax res = findMinMax(A);
      Collections.sort(A);
      assert(res.smallest.equals(A.get(0))
             && res.largest.equals(A.get(A.size() - 1)));
    }
  }
}
