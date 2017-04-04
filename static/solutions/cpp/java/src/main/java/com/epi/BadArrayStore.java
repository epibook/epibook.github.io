package com.epi;

public class BadArrayStore {
  public static void main(String[] args) {
    // @include
    Object[] numArray = new Integer[2];
    numArray[0] = 42;
    numArray[1] = "Hello World!";
    // @exclude
  }
}
