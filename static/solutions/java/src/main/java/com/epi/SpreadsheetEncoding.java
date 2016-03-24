package com.epi;

/*

@slug spreadsheet-column-encoding

@summary
Spreadsheets often use an alphabetical encoding of the
successive columns. Specifically, columns are identified by
``A'', ``B'', ``C'', \dots, ``X'', ``Y'', ``Z'', ``AA'', ``AB'', \dots, ``ZZ'',
``AAA'', ``AAB'', \dots.

@problem
Implement a function that converts a spreadsheet column id to the corresponding
integer,
with ``A'' corresponding to $1$. For example, you should return $4$ for ``D'',
$27$ for ``AA'', $702$ for ``ZZ'', etc.
How would you test your code?

*/

import java.util.Random;

public class SpreadsheetEncoding {
  private static String randString(int len) {
    Random r = new Random();
    StringBuilder ret = new StringBuilder();
    while (len-- != 0) {
      ret.append((char)(r.nextInt('Z' - 'A' + 1) + 'A'));
    }
    return ret.toString();
  }

  // @include
  public static int ssDecodeColID(final String col) {
    int ret = 0;
    for (int i = 0; i < col.length(); i++) {
      char c = col.charAt(i);
      ret = ret * 26 + c - 'A' + 1;
    }
    return ret;
  }
  // @exclude

  private static void simpleTest() {
    assert(1 == ssDecodeColID("A"));
    assert(2 == ssDecodeColID("B"));
    assert(26 == ssDecodeColID("Z"));
    assert(27 == ssDecodeColID("AA"));
  }

  public static void main(String[] args) {
    simpleTest();
    Random r = new Random();
    if (args.length == 1) {
      System.out.println(args[0] + " " + ssDecodeColID(args[0]));
    } else {
      String s = randString(r.nextInt(5) + 1);
      System.out.println(s + " " + ssDecodeColID(s));
    }
  }
}
