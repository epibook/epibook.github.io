// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class MergeContacts {
  // @include
  public static List<ContactList> mergeContactLists(
      List<ContactList> contacts) {
    return new ArrayList<>(new HashSet(contacts));
  }

  public static class ContactList {
    public List<String> names;

    ContactList(List<String> names) { this.names = names; }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof ContactList)) {
        return false;
      }
      return this == obj
          ? true
          : new HashSet(names).equals(new HashSet(((ContactList)obj).names));
    }

    @Override
    public int hashCode() {
      return new HashSet(names).hashCode();
    }
  }
  //@exclude

  public static void main(String[] args) {
    List<ContactList> contacts
        = Arrays.asList(new ContactList(Arrays.asList("a", "b", "c")),
                        new ContactList(Arrays.asList("a", "c", "b")),
                        new ContactList(Arrays.asList("b", "c", "d")));
    List<ContactList> result = mergeContactLists(contacts);
    assert(result.size() == 2);
  }
}
