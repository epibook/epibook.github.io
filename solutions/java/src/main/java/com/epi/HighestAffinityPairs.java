package com.epi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.epi.utils.Pair;

public class HighestAffinityPairs {
  // @include
  public static Pair<String, String> highestAffinityPair(InputStream ifs) {
    // Creates a mapping from pages to distinct users.
    HashMap<String, Set<String>> pageUsersMap = new HashMap<>();
    try {
      ObjectInputStream ois = new ObjectInputStream(ifs);
      while (true) {
        String page = ois.readUTF();
        String user = ois.readUTF();
        Set<String> users = pageUsersMap.get(page);
        if (users == null) {
          users = new HashSet<String>();
        }
        users.add(user);
        pageUsersMap.put(page, users);
      }
    } catch (IOException e) {
    }

    Pair<String, String> res = null;
    int maxCount = 0;
    // Compares all pairs of pages to users maps.
    List<String> keys = new ArrayList<String>(pageUsersMap.keySet());
    for (int i = 0; i < keys.size(); i++) {
      for (int j = i + 1; j < keys.size(); ++j) {
        Set<String> intersectUsers = new HashSet<String>(pageUsersMap.get(keys
            .get(i)));
        // set_intersection compares the intersetion of two sets and store the
        // result in intersectUsers.
        intersectUsers.retainAll(pageUsersMap.get(keys.get(j)));

        // Updates res if we find larger intersection.
        if (intersectUsers.size() > maxCount) {
          maxCount = intersectUsers.size();
          res = new Pair<String, String>(keys.get(i), keys.get(j));
        }
      }
    }
    return res;
  }

  // @exclude

  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder(len);
    while (len-- > 0) {
      ret.append((char) (r.nextInt(26) + 'a'));
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
      Pair<String, String> res = highestAffinityPair(ifs);
      System.out.println(res);
      ifs.close();
    } catch (Exception e) {
      System.out.println("Error reading logs.txt: " + e.getMessage());
    }
  }
}
