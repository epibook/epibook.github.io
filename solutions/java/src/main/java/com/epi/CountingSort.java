// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

// @include
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

class Person implements Comparable<Person> {
  public Integer key;
  public String name;

  public Person(Integer k, String n) {
    key = k;
    name = n;
  }

  // Hash function for Person.
  @Override
  public int hashCode() {
    return key.hashCode() ^ name.hashCode();
  }

  @Override
  public int compareTo(Person p) {
    return key.compareTo(p.key);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Person && ((Person) o).key.equals(key)) {
      return true;
    } else {
      return false;
    }
  }
}

class CountingSort {
  public static void countingSort(ArrayList<Person> people) {
    HashMap<Integer, Integer> keyToCount = new HashMap<Integer, Integer>();
    for (Person p : people) {
      if (keyToCount.containsKey(p.key)) {
        keyToCount.put(p.key, keyToCount.get(p.key) + 1);
      } else {
        keyToCount.put(p.key, 1);
      }
    }
    HashMap<Integer, Integer> keyToOffset = new HashMap<Integer, Integer>();
    int offset = 0;
    for (Map.Entry<Integer, Integer> kc : keyToCount.entrySet()) {
      keyToOffset.put(kc.getKey(), offset);
      offset += kc.getValue();
    }

    while (!keyToOffset.isEmpty()) {
      Map.Entry<Integer, Integer> from = keyToOffset.entrySet().iterator()
          .next();
      Integer toKey = people.get(from.getValue()).key;
      Integer toValue = keyToOffset.get(toKey);
      Collections.swap(people, from.getValue(), toValue);
      // Use key_to_count to see when we are finished with a particular key.
      Integer count = keyToCount.get(toKey) - 1;
      keyToCount.put(toKey, count);
      if (count > 0) {
        keyToOffset.put(toKey, toValue + 1);
      } else {
        keyToOffset.remove(toKey);
      }
    }
  }

  // @exclude

  private static String randomString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char) (rnd.nextInt(26) + 97));
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    Random rnd = new Random();
    for (int times = 0; times < 1000; ++times) {
      int size = 0;
      if (args.length == 1 || args.length == 2) {
        size = Integer.parseInt(args[0]);
      } else {
        size = rnd.nextInt(10000) + 1;
      }
      int k = 0;
      if (args.length == 2) {
        k = Integer.parseInt(args[1]);
      } else {
        k = rnd.nextInt(size) + 1;
      }
      ArrayList<Person> people = new ArrayList<Person>();
      for (int i = 0; i < size; ++i) {
        people
            .add(new Person(rnd.nextInt(k), randomString(rnd.nextInt(10) + 1)));
      }
      HashSet<Integer> keySet = new HashSet<Integer>();
      for (Person p : people) {
        keySet.add(p.key);
      }

      countingSort(people);

      // Check the correctness of sorting.
      int diffCount = 1;
      for (int i = 1; i < people.size(); ++i) {
        if (!people.get(i).equals(people.get(i - 1))) {
          ++diffCount;
        }
      }
      assert (diffCount == keySet.size());
    }
  }
  // @include
}
