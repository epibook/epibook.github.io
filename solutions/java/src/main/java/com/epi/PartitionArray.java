// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.*;

class PartitionArray {
  // @include
  private static class Person implements Comparable<Person> {
    public Integer age;
    public String name;

    public Person(Integer k, String n) {
      age = k;
      name = n;
    }

    // Hash function for Person.
    @Override
    public int hashCode() {
      return age.hashCode() ^ name.hashCode();
    }

    @Override
    public int compareTo(Person p) {
      return age.compareTo(p.age);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Person && ((Person)o).age.equals(age)) {
        return true;
      } else {
        return false;
      }
    }
  }

  public static void groupByAge(Person[] people) {
    Map<Integer, Integer> ageToCount = new HashMap<>();
    for (Person p : people) {
      if (ageToCount.containsKey(p.age)) {
        ageToCount.put(p.age, ageToCount.get(p.age) + 1);
      } else {
        ageToCount.put(p.age, 1);
      }
    }
    Map<Integer, Integer> ageToOffset = new HashMap<>();
    int offset = 0;
    for (Map.Entry<Integer, Integer> kc : ageToCount.entrySet()) {
      ageToOffset.put(kc.getKey(), offset);
      offset += kc.getValue();
    }

    while (!ageToOffset.isEmpty()) {
      Map.Entry<Integer, Integer> from = ageToOffset.entrySet().iterator().next();
      Integer toAge = people[from.getValue()].age;
      Integer toValue = ageToOffset.get(toAge);
      swap(people, from.getValue(), toValue);
      // Use ageToCount to see when we are finished with a particular age.
      Integer count = ageToCount.get(toAge) - 1;
      ageToCount.put(toAge, count);
      if (count > 0) {
        ageToOffset.put(toAge, toValue + 1);
      } else {
        ageToOffset.remove(toAge);
      }
    }
  }

  private static void swap(Person[] people, int a, int b) {
    Person temp = people[a];
    people[a] = people[b];
    people[b] = temp;
  }
  // @exclude

  private static String randomString(int len) {
    StringBuilder ret = new StringBuilder();
    Random rnd = new Random();

    while (len-- > 0) {
      ret.append((char)(rnd.nextInt(26) + 97));
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
      Person[] people = new Person[size];
      for (int i = 0; i < size; ++i) {
        people[i] = new Person(rnd.nextInt(k), randomString(rnd.nextInt(10) + 1));
      }
      Set<Integer> ageSet = new HashSet<>();
      for (Person p : people) {
        ageSet.add(p.age);
      }

      groupByAge(people);

      // Check the correctness of sorting.
      int diffCount = 1;
      for (int i = 1; i < people.length; ++i) {
        if (!people[i].equals(people[i - 1])) {
          ++diffCount;
        }
      }
      assert(diffCount == ageSet.size());
    }
  }
}
