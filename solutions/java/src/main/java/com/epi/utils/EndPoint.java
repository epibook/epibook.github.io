package com.epi.utils;

public class EndPoint implements Comparable<EndPoint> {
  public Interval ptr;
  public boolean isLeft;

  public EndPoint(Interval i, boolean il) {
    ptr = i;
    isLeft = il;
  }

  public int compareTo(EndPoint that) {
    int a = isLeft ? ptr.left : ptr.right, b = that.isLeft ? that.ptr.left
                                                           : that.ptr.right;
    if (a < b) {
      return -1;
    }
    if (a == b) {
      return 0;
    }
    return 1;
  }
}
