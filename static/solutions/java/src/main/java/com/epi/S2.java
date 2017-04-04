package com.epi;

import java.util.Arrays;

// @include
public class S2 extends SpellCheckService {
  static String wLast = null;
  static String[] closestToLastWord = null;

  public static void service(ServiceRequest req, ServiceResponse resp) {
    String w = req.extractWordToCheckFromRequest();
    String[] result = null;
    synchronized (S2.class) {
      if (w.equals(wLast)) {
        result = closestToLastWord;
      }
    }
    if (result == null) {
      result = Spell.closestInDictionary(w);
      synchronized (S2.class) {
        wLast = w;
        closestToLastWord = result;
      }
    }
    resp.encodeIntoResponse(result);
  }
}
// @exclude
