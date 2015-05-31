package com.epi;

import java.io.*;
import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchMajority {
  // @include
  public static String majoritySearch(InputStream sin) {
    String candidate = "";
    String buf;
    int count = 0;
    Scanner s = new Scanner(sin);
    while (s.hasNextLine()) {
      buf = s.nextLine();
      if (count == 0) {
        candidate = buf;
        count = 1;
      } else if (candidate.equals(buf)) {
        ++count;
      } else {
        --count;
      }
    }
    return candidate;
  }
  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char)(r.nextInt(26) + 'a'));
    }
    return ret.toString();
  }

  private static void checkAns(List<String> stream, String ans) {
    Collections.sort(stream);
    int count = 1;
    boolean find = false;
    for (int i = 1; i < stream.size(); ++i) {
      if (!stream.get(i).equals(stream.get(i - 1))) {
        if (count * 2 >= stream.size()) {
          assert(ans.equals(stream.get(i - 1)));
          find = true;
        }
        count = 1;
      } else {
        ++count;
      }
    }
    if (count * 2 >= stream.size()) {
      assert(ans.equals(stream.get(stream.size() - 1)));
      find = true;
    }
    assert(find);
  }

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      ArrayList<String> stream = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(1000) + 1;
      }
      for (int i = 0; i < n; ++i) {
        stream.add(randString(r.nextInt(5) + 1));
      }
      // generate the majority
      for (int i = 0; i < n; ++i) {
        stream.add(stream.get(stream.size() - 1));
      }
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      OutputStreamWriter osw = new OutputStreamWriter(baos);
      try {
        for (String aStream : stream) {
          osw.write(aStream + "\n");
        }
        osw.close();
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      String ret = majoritySearch(bais);
      System.out.println(ret);
      checkAns(stream, ret);
    }
  }
}
