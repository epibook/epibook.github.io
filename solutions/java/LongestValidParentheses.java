import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class LongestValidParentheses {
  // @include
  public static int longestValidParentheses(String s) {
    int maxLength = 0, end = -1;
    Deque<Integer> leftParenthesesIndices = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '(') {
        leftParenthesesIndices.addFirst(i);
      } else if (leftParenthesesIndices.isEmpty()) {
        end = i;
      } else {
        leftParenthesesIndices.removeFirst();
        int start = leftParenthesesIndices.isEmpty()
                        ? end
                        : leftParenthesesIndices.peekFirst();
        maxLength = Math.max(maxLength, i - start);
      }
    }
    return maxLength;
  }
  // @exclude

  private static int longestValidParenthesesConstantSpace(String s) {
    return Math.max(parseFromSide(s, '(', 0, s.length(), 1),
                    parseFromSide(s, ')', s.length() - 1, -1, -1));
  }

  private static int parseFromSide(String s, char paren, int begin, int end,
                                   int dir) {
    int maxLength = 0, numParensSoFar = 0, length = 0;
    for (int i = begin; i != end; i += dir) {
      if (s.charAt(i) == paren) {
        ++numParensSoFar;
        ++length;
      } else { // s.charAt(i) != paren
        if (numParensSoFar <= 0) {
          numParensSoFar = length = 0;
        } else {
          --numParensSoFar;
          ++length;
          if (numParensSoFar == 0) {
            maxLength = Math.max(maxLength, length);
          }
        }
      }
    }
    return maxLength;
  }

  private static void smallTest() {
    assert(longestValidParentheses(")(((())()(()(") == 6);
    assert(longestValidParentheses("((())()(()(") == 6);
    assert(longestValidParentheses(")(") == 0);
    assert(longestValidParentheses("()") == 2);
    assert(longestValidParentheses("") == 0);
    assert(longestValidParentheses("()()())") == 6);
    assert(longestValidParenthesesConstantSpace(")(((())()(()(") == 6);
    assert(longestValidParenthesesConstantSpace("((())()(()(") == 6);
    assert(longestValidParenthesesConstantSpace(")(") == 0);
    assert(longestValidParenthesesConstantSpace("()") == 2);
    assert(longestValidParenthesesConstantSpace("") == 0);
    assert(longestValidParenthesesConstantSpace("()()())") == 6);
  }

  private static String randString(int length) {
    Random r = new Random();
    StringBuilder result = new StringBuilder(length);
    while (length-- > 0) {
      result.append((r.nextInt(1) == 0 ? '(' : ')'));
    }
    return result.toString();
  }

  public static void main(String[] args) {
    smallTest();
    if (args.length == 1) {
      String s = args[0];
      System.out.println("s = " + s);
      System.out.println(longestValidParentheses(s));
    } else {
      Random r = new Random();
      for (int times = 0; times < 1000; ++times) {
        String s = randString(r.nextInt(100000));
        assert(longestValidParenthesesConstantSpace(s)
               == longestValidParentheses(s));
      }
    }
  }
}
