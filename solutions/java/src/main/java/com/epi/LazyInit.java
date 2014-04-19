// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;

// @include
class Array<ValueType> {
  private ValueType[] a;
  private int[] p;
  private int[] s;
  private int t;

  public Array(Class<ValueType> clazz, int N) {
    a = (ValueType[]) java.lang.reflect.Array.newInstance(clazz, N);
    p = new int[N];
    s = new int[N];
  }

  public boolean read(int i, Wrapper<ValueType> v) {
    if (isValid(i)) {
      v.object = a[i];
      return true;
    }
    return false;
  }

  public void write(int i, ValueType v) {
    if (!isValid(i)) {
      s[t] = i;
      p[i] = t++;
    }
    a[i] = v;
  }

  private boolean isValid(int i) {
    return (0 <= p[i] && p[i] < t && s[p[i]] == i);
  }
}

class Wrapper<T> {
  T object;

  public Wrapper(T object) {
    this.object = object;
  }
}

// @exclude

public class LazyInit {

  public static void main(String[] args) {
    Array<Integer> A = new Array<Integer>(Integer.class, 11);
    Wrapper<Integer> x = new Wrapper<Integer>(0);

    assert (A.read(0, x) == false);
    assert (A.read(1, x) == false);

    A.write(1, 5);
    assert (A.read(1, x) == true && x.object == 5);
    assert (A.read(2, x) == false);

    A.write(2, 27);
    assert (A.read(2, x) == true && x.object == 27);
    assert (A.read(7, x) == false);

    A.write(7, -19);
    assert (A.read(7, x) == true && x.object == -19);
  }
}
