import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class TransformStringToOther {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  // @include
  private static class StringWithDistance {
    public String candidateString;
    public Integer distance;

    public StringWithDistance(String candidateString, Integer distance) {
      this.candidateString = candidateString;
      this.distance = distance;
    }
  }

  // Uses BFS to find the least steps of transformation.
  public static int transformString(Set<String> D, String s, String t) {
    Set<String> visited = new HashSet<String>(D);
    Queue<StringWithDistance> q = new LinkedList<>();
    visited.add(s);
    q.add(new StringWithDistance(s, 0));

    StringWithDistance f;
    while ((f = q.poll()) != null) {
      // Returns if we find a match.
      if (f.candidateString.equals(t)) {
        return f.distance; // Number of steps reaches t.
      }

      // Tries all possible transformations of f.candidateString.
      String str = f.candidateString;
      for (int i = 0; i < str.length(); ++i) {
        String strStart = i == 0 ? "" : str.substring(0, i);
        String strEnd = i + 1 < str.length() ? str.substring(i + 1) : "";
        for (int c = 0; c < 26; ++c) { // Iterates through 'a' ~ 'z'.
          String modStr = strStart + (char)('a' + c) + strEnd;
          if (!visited.contains(modStr)) {
            visited.add(modStr);
            q.add(new StringWithDistance(modStr, f.distance + 1));
          }
        }
      }
    }

    return -1; // Cannot find a possible transformations.
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    int len;
    if (args.length == 1) {
      len = Integer.parseInt(args[0]);
    } else {
      len = r.nextInt(10) + 1;
    }
    String s = randString(len);
    String t = randString(len);
    Set<String> D = new HashSet<>();
    D.add(s);
    D.add(t);
    int n = r.nextInt(1000000) + 1;
    for (int i = 0; i < n; ++i) {
      D.add(randString(len));
    }
    System.out.println(D);
    System.out.println(s + " " + t + " " + D.size());
    System.out.println(transformString(D, s, t));
  }
}
