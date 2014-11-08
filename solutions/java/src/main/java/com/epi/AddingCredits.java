package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class AddingCredits {
  // @include
  public static class ClientsCreditsInfo {
    private int offset = 0;
    private Map<String, Integer> credits = new HashMap<>();
    private NavigableMap<Integer, Set<String>> inverseCredits = new TreeMap<>();

    public void insert(String s, int c) {
      remove(s);
      credits.put(s, c - offset);
      Set<String> set = inverseCredits.get(c - offset);
      if (set == null) {
        set = new HashSet<>();
        inverseCredits.put(c - offset, set);
      }
      set.add(s);
    }

    public boolean remove(String s) {
      Integer creditsIt = credits.get(s);
      if (creditsIt != null) {
        inverseCredits.get(creditsIt).remove(s);
        if (inverseCredits.get(creditsIt).isEmpty()) {
          inverseCredits.remove(creditsIt);
        }
        credits.remove(s);
        return true;
      }
      return false;
    }

    public int lookup(String s) {
      Integer it = credits.get(s);
      return it == null ? -1 : it + offset;
    }

    public void addAll(int C) {
      offset += C;
    }

    public String max() {
      return inverseCredits.isEmpty() ? "" : inverseCredits.lastEntry()
          .getValue().iterator().next();
    }
  }
  // @exclude

  public static void main(String[] args) {
    ClientsCreditsInfo a = new ClientsCreditsInfo();
    assert (a.max().isEmpty());
    assert (!a.remove("foo"));
    a.insert("foo", 10);
    a.insert("foo", 1);
    a.insert("bar", 2);
    a.addAll(5);
    a.insert("widget", 3);
    a.addAll(5);
    a.insert("dothis", 4);
    assert (11 == a.lookup("foo"));
    assert (12 == a.lookup("bar"));
    assert (8 == a.lookup("widget"));
    assert (4 == a.lookup("dothis"));
    assert (a.remove("foo"));
    assert (-1 == a.lookup("foo"));
    assert (a.max().equals("bar"));
    a.insert("xyz", 13);
    assert (a.max().equals("xyz"));
    a.insert("dd", 15);
    assert (a.max().equals("dd"));
  }
}
