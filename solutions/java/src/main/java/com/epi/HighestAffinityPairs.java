package com.epi;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static com.epi.utils.Utils.closeSilently;
import static com.epi.utils.Utils.objectInputStreamFromList;

public class HighestAffinityPairs {
  // @include
  private static class PagePair {
    public String pageA;
    public String pageB;

    public PagePair(String pageA, String pageB) {
      this.pageA = pageA;
      this.pageB = pageB;
    }
  }

  public static PagePair highestAffinityPair(ObjectInputStream ois)
      throws ClassNotFoundException, IOException {
    // Creates a mapping from pages to distinct users.
    Map<String, Set<String>> pageUsersMap = new HashMap<>();
    try {
      while (true) {
        String page = (String)ois.readObject();
        String user = (String)ois.readObject();
        Set<String> users = pageUsersMap.get(page);
        if (users == null) {
          users = new HashSet<>();
        }
        users.add(user);
        pageUsersMap.put(page, users);
      }
    } catch (EOFException e) {
      // Do nothing since the end of the stream has been reached.
    }

    PagePair result = null;
    int maxCount = 0;
    // Compares all pairs of pages to users maps.
    List<String> keys = new ArrayList<>(pageUsersMap.keySet());
    for (int i = 0; i < keys.size(); i++) {
      for (int j = i + 1; j < keys.size(); ++j) {
        Set<String> intersectUsers
            = new HashSet<>(pageUsersMap.get(keys.get(i)));
        intersectUsers.retainAll(pageUsersMap.get(keys.get(j)));

        // Updates result if we find larger intersection.
        if (intersectUsers.size() > maxCount) {
          maxCount = intersectUsers.size();
          result = new PagePair(keys.get(i), keys.get(j));
        }
      }
    }
    return result;
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

  private static void simpleTest() throws ClassNotFoundException, IOException {
    ObjectInputStream ois = objectInputStreamFromList(
        Arrays.asList("a", "A", "b", "B", "c", "A"));
    PagePair result = highestAffinityPair(ois);
    assert(result.pageA.equals("a") && result.pageB.equals("c")
           || result.pageA.equals("c") && result.pageB.equals("a"));
  }

  public static void main(String[] args)
      throws ClassNotFoundException, IOException {
    simpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    OutputStream ofs = null;
    ObjectOutputStream oos = null;
    try {
      ofs = new FileOutputStream("logs.txt");
      oos = new ObjectOutputStream(ofs);
      for (int i = 0; i < n; ++i) {
        String name = randString(5).toUpperCase();
        oos.writeUTF(name);
        oos.writeUTF(randString(5));
      }
    } catch (Exception e) {
      System.out.println("Error creating logs.txt: " + e.getMessage());
    } finally {
      closeSilently(ofs, oos);
    }
    InputStream ifs = null;
    try {
      ifs = new FileInputStream("logs.txt");
      PagePair result = highestAffinityPair(new ObjectInputStream(ifs));
      System.out.println(result);
    } catch (Exception e) {
      System.out.println("Error reading logs.txt: " + e.getMessage());
    } finally {
      closeSilently(ifs);
    }
  }
}
