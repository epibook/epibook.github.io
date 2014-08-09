// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Ivan Sharov

package com.epi;

import java.util.Random;

class CopyingPostingsList {

  // @include
  public static PNode<Integer> copyPostingsList(PNode<Integer> l) {
    // Return empty list if l is nullptr.
    if (l == null) {
      return null;
    }

    // 1st stage: Copy the nodes from l.
    PNode<Integer> p = l;
    while (p != null) {
      PNode<Integer> temp = new PNode<>(p.data, p.next, null);
      p.next = temp;
      p = temp.next;
    }

    // 2nd stage: Update the jump field.
    p = l;
    while (p != null) {
      if (p.jump != null) {
        p.next.jump = p.jump.next;
      }
      p = p.next.next;
    }

    // 3rd stage: Restore the next field.
    p = l;
    PNode<Integer> copied = p.next;
    while (p.next != null) {
      PNode<Integer> temp = p.next;
      p.next = temp.next;
      p = temp;
    }
    return copied;
  }
  // @exclude

  public static <T> void checkPostingsListEqual(PNode<T> a, PNode<T> b) {
    while (a != null && b != null) {
      System.out.print(a.data + " ");
      assert (a.data == b.data);
      assert (a.jump == null && b.jump == null || (a.jump != null
          && b.jump != null && a.jump.data == b.jump.data));
      if (a.jump != null) {
        System.out.print(a.jump.data);
      }
      System.out.println("");
      a = a.next;
      b = b.next;
    }

    assert (a == null && b == null);
  }

  public static void main(String[] args) {
    Random gen = new Random();
    for (int times = 0; times < 1000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = gen.nextInt(1000) + 1;
      }

      PNode<Integer> L = null;
      PNode<Integer> curr = L;
      for (int i = 0; i < n; ++i) {
        PNode<Integer> temp = new PNode<>(i, null, null);
        if (L != null) {
          curr.next = temp;
          curr = temp;
        } else {
          curr = L = temp;
        }

        // Randomly assigned a jump node.
        int jumpNum = (i > 0) ? gen.nextInt(i) : 0;
        PNode<Integer> jump = L;
        while (jumpNum-- != 0) {
          jump = jump.next;
        }
        temp.jump = jump;
      }

      PNode<Integer> copied = copyPostingsList(L);
      checkPostingsListEqual(L, copied);
    }
  }
}
