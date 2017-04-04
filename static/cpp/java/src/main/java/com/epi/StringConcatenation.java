package com.epi;

import java.util.List;

public class StringConcatenation {
  // @include
  public static String concat(List<String> strings) {
    String result = "";
    for (String s : strings) {
      result += s;
    }
    return result;
  }
  // @exclude
}
