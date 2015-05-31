package com.epi;

import com.epi.utils.Pair;

import java.io.*;
import java.util.*;

public class HighestAffinityPairs {
  // @include
  public static Pair<String, String> highestAffinityPair(InputStream ifs) {
    // Creates a mapping from pages to distinct users.
    Map<String, Set<String>> pageUsersMap = new HashMap<>();
    try {
      ObjectInputStream ois = new ObjectInputStream(ifs);
      while (true) {
        String page = ois.readUTF();
        String user = ois.readUTF();
        Set<String> users = pageUsersMap.get(page);
        if (users == null) {
          users = new HashSet<>();
        }
        users.add(user);
        pageUsersMap.put(page, users);
      }
    } catch (IOException e) {
    }

    Pair<String, String> result = null;
    int maxCount = 0;
    // Compares all pairs of pages to users maps.
    List<String> keys = new ArrayList<>(pageUsersMap.keySet());
    for (int i = 0; i < keys.size(); i++) {
      for (int j = i + 1; j < keys.size(); ++j) {
        Set<String> intersectUsers = new HashSet<>(pageUsersMap.get(keys.get(i)));
        intersectUsers.retainAll(pageUsersMap.get(keys.get(j)));

        // Updates result if we find larger intersection.
        if (intersectUsers.size() > maxCount) {
          maxCount = intersectUsers.size();
          result = new Pair<>(keys.get(i), keys.get(j));
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

  public static void main(String[] args) {
    Random r = new Random();
    int n;
    if (args.length == 1) {
      n = Integer.parseInt(args[0]);
    } else {
      n = r.nextInt(10000) + 1;
    }
    try {
      OutputStream ofs = new FileOutputStream("logs.txt");
      ObjectOutputStream oos = new ObjectOutputStream(ofs);
      for (int i = 0; i < n; ++i) {
        String name = randString(5).toUpperCase();
        oos.writeUTF(name);
        oos.writeUTF(randString(5));
      }
      ofs.close();
    } catch (Exception e) {
      System.out.println("Error creating logs.txt: " + e.getMessage());
    }
    try {
      InputStream ifs = new FileInputStream("logs.txt");
      Pair<String, String> result = highestAffinityPair(ifs);
      System.out.println(result);
      ifs.close();
    } catch (Exception e) {
      System.out.println("Error reading logs.txt: " + e.getMessage());
    }
  }
}
