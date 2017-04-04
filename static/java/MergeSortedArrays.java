import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class MergeSortedArrays {
  // @include
  private static class ArrayEntry {
    public Integer value;
    public Integer arrayId;

    public ArrayEntry(Integer value, Integer arrayId) {
      this.value = value;
      this.arrayId = arrayId;
    }
  }

  public static List<Integer> mergeSortedArrays(
      List<List<Integer>> sortedArrays) {
    List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());
    for (List<Integer> array : sortedArrays) {
      iters.add(array.iterator());
    }
    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
        ((int)sortedArrays.size()), new Comparator<ArrayEntry>() {
          @Override
          public int compare(ArrayEntry o1, ArrayEntry o2) {
            return Integer.compare(o1.value, o2.value);
          }
        });
    for (int i = 0; i < iters.size(); ++i) {
      if (iters.get(i).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(i).next(), i));
      }
    }

    List<Integer> result = new ArrayList<>();
    while (!minHeap.isEmpty()) {
      ArrayEntry headEntry = minHeap.poll();
      result.add(headEntry.value);
      if (iters.get(headEntry.arrayId).hasNext()) {
        minHeap.add(new ArrayEntry(iters.get(headEntry.arrayId).next(),
                                   headEntry.arrayId));
      }
    }
    return result;
  }
  // @exclude

  private static void check(List<List<Integer>> S, List<Integer> ans,
                            List<Integer> golden) {
    if (!ans.equals(golden)) {
      System.err.println("Your program failed on input " + S);
      System.err.println("Expected " + golden);
      System.err.println("Got " + ans);
      System.exit(-1);
    }
  }

  private static void simpleTest() {
    List<List<Integer>> S
        = Arrays.asList(Arrays.asList(1, 5, 10), Arrays.asList(2, 3, 100),
                        Arrays.asList(2, 12, Integer.MAX_VALUE));
    List<Integer> ans = mergeSortedArrays(S);
    List<Integer> golden
        = Arrays.asList(1, 2, 2, 3, 5, 10, 12, 100, Integer.MAX_VALUE);
    check(S, ans, golden);

    S = Arrays.asList(Arrays.asList(1));
    ans = mergeSortedArrays(S);
    golden = Arrays.asList(1);
    check(S, ans, golden);

    S = Arrays.asList(new ArrayList<Integer>(), Arrays.asList(1),
                      Arrays.asList(2));
    ans = mergeSortedArrays(S);
    golden = Arrays.asList(1, 2);
    check(S, ans, golden);
  }

  public static void main(String[] args) {
    simpleTest();
    Random rnd = new Random();
    for (int times = 0; times < 100; ++times) {
      int n;
      if (args.length == 2) {
        n = Integer.parseInt(args[0]);
      } else {
        n = 1 + rnd.nextInt(100);
      }

      List<List<Integer>> S = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        int m = rnd.nextInt(500);
        List<Integer> last = new ArrayList<>(m);
        S.add(last);
        for (int j = 0; j < m; ++j) {
          last.add(rnd.nextInt(500));
        }
        Collections.sort(last);
      }

      List<Integer> ans = mergeSortedArrays(S);
      for (int i = 1; i < ans.size(); ++i) {
        assert(ans.get(i - 1) <= ans.get(i));
      }
    }
  }
}
