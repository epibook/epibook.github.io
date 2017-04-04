import java.util.Random;

public class ReverseWords {
  static String randString(int len) {
    Random gen = new Random();
    StringBuilder ret = new StringBuilder();
    while (len-- > 0) {
      int ch = gen.nextInt(53);
      if (ch == 52) {
        ret.append(' ');
      } else if (ch < 26) {
        ret.append(ch + 'a');
      } else {
        ret.append(ch - 26 + 'A');
      }
    }
    return ret.toString();
  }

  // @include
  public static void reverseWords(char[] input) {
    int n = input.length;
    // First, reverses the whole string.
    reverse(input, 0, n - 1);

    // Second, Reverses each word in the string.
    int start = 0, end = 0;
    while (start < n) {
      while (start < end || start < n && input[start] == ' ') {
        ++start; // Skip spaces chars.
      }
      while (end < start || end < n && input[end] != ' ') {
        ++end; // Skip non-spaces chars.
      }
      reverse(input, start, end - 1);
    }
  }

  public static void reverse(char[] array, int start, int end) {
    while (start < end) {
      char tmp = array[start];
      array[start++] = array[end];
      array[end--] = tmp;
    }
  }

  public static int find(char[] array, char c, int start) {
    for (int i = start; i < array.length; i++) {
      if (array[i] == c) {
        return i;
      }
    }
    return -1;
  }
  // @exclude

  private static void checkAnswer(String ori, String str) {
    char[] input = str.toCharArray();
    reverseWords(input);
    assert ori.equals(new String(input));
  }

  private static void simpleTest() {
    char[] input = "a cat and dog".toCharArray();
    reverseWords(input);
    assert "dog and cat a".equals(new String(input));
    input = "dog".toCharArray();
    reverseWords(input);
    assert "dog".equals(new String(input));
  }

  public static void main(String[] args) {
    simpleTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      StringBuilder str = new StringBuilder();
      if (args.length >= 1) {
        str.append(args[0]);
        for (int i = 1; i < args.length; ++i) {
          str.append(' ').append(args[i]);
        }
      } else {
        str.append(randString(gen.nextInt(10000)));
      }
      System.out.println(str);
      char[] input = str.toString().toCharArray();
      reverseWords(input);
      checkAnswer(str.toString(), new String(input));
    }
  }
}
