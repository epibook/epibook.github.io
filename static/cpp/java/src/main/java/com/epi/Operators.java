import java.util.List;
import java.util.LinkedList;

public class Operators {
  static int eval(int[] A, int start, int end) {
    int result = 0;
    int multiplier = 1;
    for (int i = end - 1; i >= start; i--) {
      result += A[i] * multiplier;
      multiplier *= 10;
    }
    System.out.println("\t>> start, end result : " + start + " " + end);
    System.out.println("\t>> result : " + result);
    return result;
  }

  static List<Integer> allPossibleValues(int[] A, int offset) {
    List<Integer> remainder;
    System.out.println("offset = " + offset);
    if (offset == (A.length - 1)) {
      remainder = new LinkedList<Integer>();
      remainder.add(0);
      System.out.println("base case, remainder = " + remainder);
      return remainder;
    } else {
      remainder = new LinkedList<Integer>();
      for (int i = offset + 1; i <= A.length; i++) {
        List<Integer> tmp = allPossibleValues(A, i);
        int base = eval(A, offset, i);
        System.out.println("base = " + base);
        System.out.println("offset, i = " + offset + " " + i);
        System.out.println("tmp = " + tmp);
        for (Integer suffixVal : tmp) {
          remainder.add(base + suffixVal);
        }
      }
      return remainder;
    }
  }

  public static void main(String[] args) {
    final int[] t0 = {1, 2, 3, 4};
    List<Integer> r0 = allPossibleValues(t0, 0);
    System.out.println(r0);
  }
}
