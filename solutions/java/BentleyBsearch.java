import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BentleyBsearch {
  // @include
  public static int bsearch(int t, List<Integer> A) {
    int L = 0, U = A.size() - 1;
    while (L <= U) {
      int M = (L + U) / 2;
      if (A.get(M) < t) {
        L = M + 1;
      } else if (A.get(M) == t) {
        return M;
      } else {
        U = M - 1;
      }
    }
    return -1;
  }
  // @exclude

  private static void simpleTest() {
    List<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3));
    assert(0 == bsearch(1, A));
    assert(1 == bsearch(2, A));
    assert(2 == bsearch(3, A));
    A = new ArrayList<Integer>(Arrays.asList(2, 2, 2));
    assert(0 <= bsearch(2, A) && bsearch(2, A) <= 2);
    assert(-1 == bsearch(3, A));
    assert(-1 == bsearch(0, A));
  }

  public static void main(String[] args) {
    simpleTest();
    List<Integer> A
        = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 2, 3, 3, 3, 5, 6, 10, 100));
    assert(0 == bsearch(1, A));
    assert(1 <= bsearch(2, A) && bsearch(2, A) <= 4);
    assert(5 <= bsearch(3, A));
    assert(-1 == bsearch(4, A));
  }
}
