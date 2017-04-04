public class CheckingCycle {
  // @include
  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
    ListNode<Integer> fast = head, slow = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        // There is a cycle, so now let's calculate the cycle length.
        int cycleLen = 0;
        do {
          ++cycleLen;
          fast = fast.next;
        } while (slow != fast);

        // Finds the start of the cycle.
        ListNode<Integer> cycleLenAdvancedIter = head;
        // cycleLenAdvancedIter pointer advances cycleLen first.
        while (cycleLen-- > 0) {
          cycleLenAdvancedIter = cycleLenAdvancedIter.next;
        }

        ListNode<Integer> iter = head;
        // Both iterators advance in tandem.
        while (iter != cycleLenAdvancedIter) {
          iter = iter.next;
          cycleLenAdvancedIter = cycleLenAdvancedIter.next;
        }
        return iter; // iter is the start of cycle.
      }
    }
    return null; // no cycle.
  }
  // @exclude

  private static void check(ListNode L, ListNode expectedValue) {
    ListNode computedValue = hasCycle(L);
    if (computedValue == null && expectedValue != null
        || computedValue != null && !computedValue.equals(expectedValue)) {
      System.err.println("Your program failed on input " + L);
      System.err.println("Expected " + expectedValue);
      System.err.println("Your value " + computedValue);
      System.exit(-1);
    }
  }

  private static void simpleTest() {
    ListNode<Integer> l0 = new ListNode<>(42, null);
    l0.next = l0;
    check(l0, l0);

    ListNode<Integer> l1 = new ListNode<>(42, null);
    ListNode<Integer> l2 = new ListNode<>(42, null);
    l1.next = l2;
    l2.next = l1;
    check(l1, l1);
    check(l2, l2);

    l2.next = null;
    check(l1, null);
    check(l2, null);
    System.out.println("All tests passed!");
  }

  public static void main(String[] args) {
    simpleTest();
    ListNode<Integer> l3 = new ListNode<>(3, null);
    ListNode<Integer> l2 = new ListNode<>(2, l3);
    ListNode<Integer> l1 = new ListNode<>(1, l2);

    // should output "l1 does not have cycle."
    check(l1, null);
    // make it a cycle
    l3.next = l2;
    check(l1, l2);
    System.out.println("You passed all tests.");
  }
}
