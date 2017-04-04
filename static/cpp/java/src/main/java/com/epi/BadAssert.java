package com.epi;

public class BadAssert {
  public static void main(String[] args) {
    // @include
    assert((0.37 - 0.23) == 0.14);
    // @exclude
  }
}
