package com.epi.utils;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Pair<A, B> {
  private A first;
  private B second;

  public Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  public A getFirst() { return first; }

  public void setFirst(A first) { this.first = first; }

  public B getSecond() { return second; }

  public void setSecond(B second) { this.second = second; }

  public String toString() { return first + " " + second; }

  public static <T extends Comparable<T>> Pair<T, T> minmax(T a, T b) {
    return b.compareTo(a) < 0 ? new Pair<>(b, a) : new Pair<>(a, b);
  }
}
