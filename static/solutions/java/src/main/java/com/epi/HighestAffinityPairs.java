package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

  public static PagePair highestAffinityPair(Iterator<String> pageViews) {
    // Creates a mapping from pages to distinct users.
    Map<String, Set<String>> pageUsersMap = new HashMap<>();
    while (pageViews.hasNext()) {
      String page = pageViews.next();
      String user = pageViews.next();
      Set<String> users = pageUsersMap.get(page);
      if (users == null) {
        users = new HashSet<>();
      }
      users.add(user);
      pageUsersMap.put(page, users);
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

  private static void simpleTest() {
    PagePair result = highestAffinityPair(
        Arrays.asList("a", "A", "b", "B", "c", "A").iterator());
    assert(result.pageA.equals("a") && result.pageB.equals("c")
           || result.pageA.equals("c") && result.pageB.equals("a"));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    List<String> pageViewData = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      String name = randString(5).toUpperCase();
      pageViewData.add(name);
      pageViewData.add(randString(5));
    }
    PagePair result = highestAffinityPair(pageViewData.iterator());
    System.out.println(result);
  }
}
