package com.epi;

import java.util.List;

public class StringConcatenationStringBuilder {
  // @include
  public static String concat(List<String> strings) {
    StringBuilder result = new StringBuilder();
    for (String s : strings) {
      result.append(s);
    }
    return result.toString();
  }
  // @exclude
}
