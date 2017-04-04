import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DutchNationalFlag {
  // @include
  public static enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    /**
     * Keep the following invariants during partitioning:
     * bottom group: A.subList(0, smaller).
     * middle group: A.subList(smaller, equal).
     * unclassified group: A.subList(equal, larger).
     * top group: A.subList(larger, A.size()).
     */
    int smaller = 0, equal = 0, larger = A.size();
    // Keep iterating as long as there is an unclassified element.
    while (equal < larger) {
      // A.get(equal) is the incoming unclassified element.
      if (A.get(equal).ordinal() < pivot.ordinal()) {
        Collections.swap(A, smaller++, equal++);
      } else if (A.get(equal).ordinal() == pivot.ordinal()) {
        ++equal;
      } else { // A.get(equal) > pivot.
        Collections.swap(A, equal, --larger);
      }
    }
  }
  // @exclude

  private static List<Color> randArray(int len) {
    Random r = new Random();
    List<Color> ret = new ArrayList<>(len);
    for (int i = 0; i < len; ++i) {
      ret.add(Color.values()[r.nextInt(3)]);
    }
    return ret;
  }

  private static void unitTest(int pivot, List<Color> A) {
    List<Color> Adup = new ArrayList<>(A);
    new DutchNationalFlag().dutchFlagPartition(pivot, A);

    if (!check(Adup.get(pivot), A, Adup)) {
      System.exit(-1);
    }
  }

  public static void main(String[] args) {
    unitTest(0, Arrays.asList(Color.RED, Color.BLUE, Color.WHITE));
    unitTest(1, Arrays.asList(Color.RED, Color.BLUE, Color.WHITE));
    unitTest(2, Arrays.asList(Color.RED, Color.BLUE, Color.WHITE));
    unitTest(0, Arrays.asList(Color.RED, Color.WHITE, Color.WHITE));
    unitTest(1, Arrays.asList(Color.RED, Color.WHITE, Color.WHITE));
    unitTest(2, Arrays.asList(Color.RED, Color.WHITE, Color.WHITE));
    unitTest(0, Arrays.asList(Color.RED, Color.WHITE, Color.WHITE));
    unitTest(1, Arrays.asList(Color.WHITE, Color.WHITE, Color.WHITE));

    unitTest(1,
             Arrays.asList(Color.RED, Color.WHITE, Color.WHITE, Color.WHITE));
    unitTest(1, Arrays.asList(Color.RED, Color.BLUE, Color.WHITE, Color.WHITE));
    unitTest(0,
             Arrays.asList(Color.RED, Color.WHITE, Color.WHITE, Color.WHITE));
    unitTest(0, Arrays.asList(Color.RED, Color.BLUE, Color.WHITE, Color.WHITE));

    Random gen = new Random();

    for (int times = 0; times < 10; ++times) {
      int n;

      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(100) + 1;
      }

      List<Color> A = randArray(n);
      List<Color> Adup = new ArrayList<>(A);

      int pivotIndex = gen.nextInt(n);
      Color pivot = A.get(pivotIndex);

      new DutchNationalFlag().dutchFlagPartition(pivotIndex, A);

      if (!check(pivot, A, Adup)) {
        System.exit(-1);
      }
    }
    System.out.println("All tests passed!");
  }

  public static boolean check(Color pivot, List<Color> A, List<Color> Adup) {
    int n = A.size();
    int i = 0;
    while (i < n && A.get(i).ordinal() < pivot.ordinal()) {
      // System.out.print(A.get(i) + " ");
      ++i;
    }
    while (i < n && A.get(i) == pivot) {
      // System.out.print(A.get(i) + " ");
      ++i;
    }
    while (i < n && A.get(i).ordinal() > pivot.ordinal()) {
      // System.out.print(A.get(i) + " ");
      ++i;
    }

    if (i != n) {
      System.err.println("Failing test: pivot = " + pivot + " array = " + Adup
                         + "."
                         + "Your code updated to " + A);
      System.exit(-1);
    }
    return true;
  }
}
