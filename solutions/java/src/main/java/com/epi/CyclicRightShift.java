package com.epi;

public class CyclicRightShift {
  // @include
  public static NodeT<Integer> cyclicallyRightShiftList(NodeT<Integer> l, int k) {
    if (l == null) {
      return l;
    }

    // Computes n, the length of l.
    NodeT<Integer> temp = l;
    int n = 0;
    while (temp != null) {
      ++n;
      temp = temp.next;
    }
    // We only need to rotate right k % n nodes.
    k %= n;

    // No need to rotate if k == 0.
    if (k == 0) {
      return l;
    }

    // last advances k steps first.
    NodeT<Integer> preLast = null;
    NodeT<Integer> last = l;
    while (k-- != 0) {
      preLast = last;
      last = last.next;
    }

    // Advances both p and last.
    NodeT<Integer> preP = null;
    NodeT<Integer> p = l;
    while (last != null) {
      preLast = last;
      last = last.next;
      preP = p;
      p = p.next;
    }
    preP.next = null;
    preLast.next = l;
    return p;
  }

  // @exclude

  public static void main(String[] args) {
    NodeT<Integer> L;
    L = new NodeT<Integer>(1,
        new NodeT<Integer>(2, new NodeT<Integer>(3, null)));
    NodeT<Integer> res = cyclicallyRightShiftList(L, 2);
    assert (res.data.equals(2) && res.next.data.equals(3)
        && res.next.next.data.equals(1) && res.next.next.next == null);
    while (res != null) {
      System.out.println(res.data);
      res = res.next;
    }

  }
}
