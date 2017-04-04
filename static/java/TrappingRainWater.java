import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TrappingRainWater {
  // @include
  public static int calculateTrappingWater(List<Integer> heights) {
    // Finds the index with maximum height.
    int maxH = heights.indexOf(Collections.max(heights));
    return trappingWaterTillEnd(heights, 0, maxH, 1)
        + trappingWaterTillEnd(heights, heights.size() - 1, maxH, -1);
  }

  // Assume heights[end] is maximum height.
  private static int trappingWaterTillEnd(List<Integer> heights, int begin,
                                          int end, int step) {
    int sum = 0, highestLevelSeen = Integer.MIN_VALUE;
    for (int i = begin; i != end; i += step) {
      if (heights.get(i) >= highestLevelSeen) {
        highestLevelSeen = heights.get(i);
      } else {
        sum += highestLevelSeen - heights.get(i);
      }
    }
    return sum;
  }
  // @exclude

  // Stack approach, O(n) time, O(n) space
  private static class HeightBound {
    public Integer leftBound;
    public Integer rightBound;

    public HeightBound(Integer leftBound, Integer rightBound) {
      this.leftBound = leftBound;
      this.rightBound = rightBound;
    }
  }

  private static int checkAnswer(List<Integer> A) {
    Deque<HeightBound> s = new LinkedList<>();
    int sum = 0;
    for (int i = 0; i < A.size(); ++i) {
      while (!s.isEmpty() && s.peekFirst().rightBound <= A.get(i)) {
        int bottom = s.removeFirst().rightBound;
        if (s.isEmpty()) {
          break;
        }
        int top = Math.min(s.peekFirst().rightBound, A.get(i));
        sum += (top - bottom) * (i - s.peekFirst().leftBound - 1);
      }
      s.addFirst(new HeightBound(i, A.get(i)));
    }
    return sum;
  }

  private static void smallTest() {
    List<Integer> A = Arrays.asList(1, 0, 3, 2, 5, 0, 1);
    assert(calculateTrappingWater(A) == 3);
    A = Arrays.asList(1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1);
    assert(calculateTrappingWater(A) == 18);
  }

  public static void main(String[] args) {
    smallTest();
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      List<Integer> A = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        A.add(r.nextInt(11));
      }
      System.out.println(A);
      System.out.println(checkAnswer(A) + " " + calculateTrappingWater(A));
      assert(checkAnswer(A) == calculateTrappingWater(A));
    }
  }
}
