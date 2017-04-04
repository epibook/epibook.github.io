// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EliminateDuplicate {
  //@include
  public static class Name implements Comparable<Name> {
    String firstName;
    String lastName;

    // @exclude
    public Name(String first, String last) {
      firstName = first;
      lastName = last;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Name)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      Name name = (Name)obj;
      return firstName.equals(name.firstName) && lastName.equals(name.lastName);
    }

    // @include
    public int compareTo(Name name) {
      int cmpFirst = firstName.compareTo(name.firstName);
      if (cmpFirst != 0) {
        return cmpFirst;
      }
      return lastName.compareTo(name.lastName);
    }
  }

  public static void eliminateDuplicate(List<Name> A) {
    Collections.sort(A); // Makes identical elements become neighbors.
    int result = 0;
    for (int first = 1; first < A.size(); first++) {
      if (!A.get(first).firstName.equals(A.get(result).firstName)) {
        A.set(++result, A.get(first));
      }
    }
    // Shrinks array size.
    A.subList(++result, A.size()).clear();
  }
  // @exclude

  public static void checkAnswer(List<Name> A) {
    for (int i = 1; i < A.size(); ++i) {
      assert(!A.get(i).equals(A.get(i - 1)));
    }
  }

  private static void smallTest() {
    List<Name> A = new ArrayList<>();
    A.add(new Name("Foo", "Bar"));
    A.add(new Name("ABC", "XYZ"));
    A.add(new Name("Foo", "Widget"));
    eliminateDuplicate(A);
    assert(A.size() == 2);
  }

  public static void main(String[] args) {
    smallTest();
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      List<Name> A = new ArrayList<>();
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = 1 + gen.nextInt(1000);
      }
      for (int i = 0; i < n; ++i) {
        A.add((n > 1)
                  ? new Name("" + gen.nextInt(n - 1), "" + gen.nextInt(n - 1))
                  : new Name("0", "0"));
      }
      eliminateDuplicate(A);
      checkAnswer(A);
    }
  }
}
