package com.epi.utils;

// used to implement pass-by-reference argument passing
// http://stackoverflow.com/questions/7884581/how-can-i-simulate-pass-by-reference-in-java
public class Ref<T> {
  public T value;

  public Ref(T v) { value = v; }
}
