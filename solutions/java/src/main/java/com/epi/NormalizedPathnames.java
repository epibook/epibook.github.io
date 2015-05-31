package com.epi;

import java.util.Iterator;
import java.util.LinkedList;

public class NormalizedPathnames {
  // @include
  public static String ShortestEquivalentPath(String path) {
    LinkedList<String> pathNames = new LinkedList<>();
    // Special case: starts with "/", which is an absolute path.
    if (path.startsWith("/")) {
      pathNames.push("/");
    }

    for (String token : path.split("/")) {
      System.out.println(token);
      if (token.equals("..")) {
        if (pathNames.isEmpty() || pathNames.peek().equals("..")) {
          pathNames.push(token);
        } else {
          if (pathNames.peek().equals("/")) {
            throw new RuntimeException("Path error");
          }
          pathNames.pop();
        }
      } else if (!token.equals(".") && !token.isEmpty()) { // Must be a name.
        pathNames.push(token);
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
    assert(ShortestEquivalentPath("123/456").equals("123/456"));
    assert(ShortestEquivalentPath("/123/456").equals("/123/456"));
    assert(ShortestEquivalentPath("usr/lib/../bin/gcc").equals("usr/bin/gcc"));
    assert(ShortestEquivalentPath("./../").equals(".."));
    assert(ShortestEquivalentPath("../../local").equals("../../local"));
    assert(ShortestEquivalentPath("./.././../local").equals("../../local"));
    try {
      ShortestEquivalentPath("/..");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      ShortestEquivalentPath("/cpp_name/bin/");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    assert(ShortestEquivalentPath("scripts//./../scripts/awkscripts/././")
               .equals("scripts/awkscripts"));
    if (args.length == 1) {
      System.out.println(ShortestEquivalentPath(args[0]));
    }
  }
}
