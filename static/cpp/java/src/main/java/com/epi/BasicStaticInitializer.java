package com.epi;

import java.util.HashMap;
import java.util.Map;

public class BasicStaticInitializer {
  // @include
  private static final Map<String, Integer> monthToOrdinal = new HashMap<>();

  // Static initializer block. Executed once, when class is first acccessed.
  static {
    int ordinal = 0;
    String[] months = {"January",   "February", "March",    "April",
                       "May",       "June",     "July",     "August",
                       "September", "October",  "November", "December"};
    for (String month : months) {
      monthToOrdinal.put(month, ordinal++);
    }
  }
  // @exclude
}
