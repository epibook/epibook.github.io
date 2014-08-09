package com.epi;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class NormalizedPathnames {
  // @include
  public static String normalizedPathNames(String path) {
    LinkedList<String> s = new LinkedList<>(); // Use LinkedList as a stack.
    // Special case: starts with "/", which is an absolute path.
    if (path.startsWith("/")) {
      s.push("/");
    }

    for (String token : path.split("/")) {
      if (token.equals("..")) {
        if (s.isEmpty() || s.peek().equals("..")) {
          s.push(token);
        } else {
          if (s.peek().equals("/")) {
            throw new RuntimeException("Path error");
          }
          s.pop();
        }
      } else if (!token.equals(".") && !token.isEmpty()) { // Name.
        for (char c : token.toCharArray()) {
          if (c != '.' && !Character.isDigit(c) && !Character.isLetter(c)) {
            throw new RuntimeException("Invalid directory name");
          }
        }
        s.push(token);
      }
    }

    StringBuilder normalizedPath = new StringBuilder();
    if (!s.isEmpty()) {
      Iterator<String> it = s.descendingIterator();
      String prev = it.next();
      normalizedPath.append(prev);
      while (it.hasNext()) {
        if (!prev.equals("/")) {
          normalizedPath.append("/");
        }
        prev = it.next();
        normalizedPath.append(prev);
      }
    }
    return normalizedPath.toString();
  }
  // @exclude

  public static void main(String[] args) {
    assert (normalizedPathNames("123/456").equals("123/456"));
    assert (normalizedPathNames("/123/456").equals("/123/456"));
    assert (normalizedPathNames("usr/lib/../bin/gcc").equals("usr/bin/gcc"));
    assert (normalizedPathNames("./../").equals(".."));
    assert (normalizedPathNames("../../local").equals("../../local"));
    assert (normalizedPathNames("./.././../local").equals("../../local"));
    try {
      normalizedPathNames("/..");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      normalizedPathNames("/cpp_name/bin/");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    assert (normalizedPathNames("scripts//./../scripts/awkscripts/././")
        .equals("scripts/awkscripts"));
    if (args.length == 1) {
      System.out.println(normalizedPathNames(args[0]));
    }
  }
}
