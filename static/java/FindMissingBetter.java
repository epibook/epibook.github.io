// - mixed x and num (which was undefined)
// - precedence of &
// used sb, forgot binary to int is not how string conversion works!
import java.util.*;

class FindMissingBetter {
  // @include
  public static int numberAppearingOnce(int[] nums) {
    int[] countsMod3 = new int[32];
    for (int x : nums) {
      for (int i = 0; i < 32; i++) {
        if ((x & (1 << i)) != 0) {
          countsMod3[i] = (countsMod3[i] + 1) % 3;
        }
      }
    }
    int result = 0;
    for (int i = 31; i >= 0; i--) {
      result += countsMod3[i] * (1 << i);
    }
    return result;
  }
  // @exclude

  public static void main(String[] args) {
    System.out.println(
        numberAppearingOnce(new int[] {1, 7, 1, 3, 1, 3, 2, 4, 4, 4, 7, 3, 7}));
  }
}
