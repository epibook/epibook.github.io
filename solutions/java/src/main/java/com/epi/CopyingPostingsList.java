// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.Random;

public class CopyingPostingsList {
  // @include
  public static PostingListNode copyPostingsList(PostingListNode L) {
    if (L == null) {
      return null;
    }

    // Stage 1: Makes a copy of the original list without assigning the jump
    //          field, and creates the mapping for each node in the original
    //          list to the copied list.
    PostingListNode iter = L;
    while (iter != null) {
      PostingListNode newNode
          = new PostingListNode(iter.order, iter.next, null);
      iter.next = newNode;
      iter = newNode.next;
    }

    // Stage 2: Assigns the jump field in the copied list.
    iter = L;
    while (iter != null) {
      if (iter.jump != null) {
        iter.next.jump = iter.jump.next;
      }
      iter = iter.next.next;
    }

    // Stage 3: Reverts the original list, and assigns the next field of
    //          the copied list.
    iter = L;
    PostingListNode newListHead = iter.next;
    while (iter.next != null) {
      PostingListNode temp = iter.next;
      iter.next = temp.next;
      iter = temp;
    }
    return newListHead;
  }
  // @exclude

  public static void checkPostingsListEqual(PostingListNode a,
                                            PostingListNode b) {
    while (a != null && b != null) {
      System.out.print(a.order + " ");
      assert(a.order == b.order);
      assert(a.jump == null && b.jump == null
             || (a.jump != null && b.jump != null
                 && a.jump.order == b.jump.order));
      if (a.jump != null) {
        System.out.print(a.jump.order);
      }
      System.out.println("");
      a = a.next;
      b = b.next;
    }

    assert(a == null && b == null);
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

      PostingListNode L = null;
      PostingListNode curr = L;
      for (int i = 0; i < n; ++i) {
        PostingListNode temp = new PostingListNode(i, null, null);
        if (L != null) {
          curr.next = temp;
          curr = temp;
        } else {
          curr = L = temp;
        }

        // Randomly assigned a jump node.
        int jumpNum = (i > 0) ? gen.nextInt(i) : 0;
        PostingListNode jump = L;
        while (jumpNum-- != 0) {
          jump = jump.next;
        }
        temp.jump = jump;
      }

      PostingListNode copied = copyPostingsList(L);
      checkPostingsListEqual(L, copied);
    }
  }
}
