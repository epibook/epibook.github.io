import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays1 {
  // @include
  public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                       List<Integer> B) {
    List<Integer> intersectionAB = new ArrayList<>();
    for (int i = 0; i < A.size(); ++i) {
      if ((i == 0 || A.get(i) != A.get(i - 1)) && B.contains(A.get(i))) {
        intersectionAB.add(A.get(i));
      }
    }
    return intersectionAB;
  }
  // @exclude
}
