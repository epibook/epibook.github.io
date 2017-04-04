import com.epi.GeneratingABSqrt2.ABSqrt2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class GeneratingABSqrt2Improved {
  // @include
  public static List<ABSqrt2> generateFirstKABSqrt2(int k) {
    // Will store the first k numbers of the form a + b sqrt(2).
    List<ABSqrt2> result = new ArrayList<>();
    result.add(new ABSqrt2(0, 0));
    int i = 0, j = 0;
    for (int n = 1; n < k; ++n) {
      ABSqrt2 resultIPlus1 = new ABSqrt2(result.get(i).a + 1, result.get(i).b);
      ABSqrt2 resultJPlusSqrt2
          = new ABSqrt2(result.get(j).a, result.get(j).b + 1);
      result.add(resultIPlus1.val < resultJPlusSqrt2.val ? resultIPlus1
                                                         : resultJPlusSqrt2);
      if (resultIPlus1.compareTo(result.get(result.size() - 1)) == 0) {
        ++i;
      }
      if (resultJPlusSqrt2.compareTo(result.get(result.size() - 1)) == 0) {
        ++j;
      }
    }
    return result;
  }
  // @exclude
}
