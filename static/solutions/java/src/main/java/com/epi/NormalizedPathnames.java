package com.epi;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class NormalizedPathnames {
  // @include
  public static String shortestEquivalentPath(String path) {
    if (path.equals("")) {
      throw new IllegalArgumentException("Empty string is not a legal path.");
    }

    Deque<String> pathNames = new LinkedList<>();
    // Special case: starts with "/", which is an absolute path.
    if (path.startsWith("/")) {
      pathNames.addFirst("/");
    }

    for (String token : path.split("/")) {
      if (token.equals("..")) {
        if (pathNames.isEmpty() || pathNames.peekFirst().equals("..")) {
          pathNames.addFirst(token);
        } else {
          if (pathNames.peekFirst().equals("/")) {
            throw new IllegalArgumentException(
                "Path error, trying to go up root " + path);
          }
          pathNames.removeFirst();
        }
      } else if (!token.equals(".") && !token.isEmpty()) { // Must be a name.
        pathNames.addFirst(token);
      }
    }

    StringBuilder result = new StringBuilder();
    if (!pathNames.isEmpty()) {
      Iterator<String> it = pathNames.descendingIterator();
      String prev = it.next();
      result.append(prev);
      while (it.hasNext()) {
        if (!prev.equals("/")) {
          result.append("/");
        }
        prev = it.next();
        result.append(prev);
      }
    }
    return result.toString();
  }
  // @exclude

  public static void main(String[] args) {
    assert(shortestEquivalentPath("123/456").equals("123/456"));
    assert(shortestEquivalentPath("/123/456").equals("/123/456"));
    assert(shortestEquivalentPath("usr/lib/../bin/gcc").equals("usr/bin/gcc"));
    assert(shortestEquivalentPath("./../").equals(".."));
    assert(shortestEquivalentPath("../../local").equals("../../local"));
    assert(shortestEquivalentPath("./.././../local").equals("../../local"));
    assert(shortestEquivalentPath("/foo/../foo/./../").equals("/"));
    try {
      shortestEquivalentPath("/..");
      assert(false);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
    try {
      shortestEquivalentPath("");
      assert(false);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
    try {
      shortestEquivalentPath("/cpp_name/bin/");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assert(false);
    }
    assert(shortestEquivalentPath("scripts//./../scripts/awkscripts/././")
               .equals("scripts/awkscripts"));
    if (args.length == 1) {
      System.out.println(shortestEquivalentPath(args[0]));
    }
  }
}
