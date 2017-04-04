import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class StackWithMax {
  // @include
  private static class ElementWithCachedMax {
    public Integer element;
    public Integer max;

    public ElementWithCachedMax(Integer element, Integer max) {
      this.element = element;
      this.max = max;
    }
  }

  public static class Stack {
    // Stores (element, cached maximum) pair.
    private Deque<ElementWithCachedMax> elementWithCachedMax
        = new LinkedList<>();

    public boolean empty() { return elementWithCachedMax.isEmpty(); }

    public Integer max() {
      if (empty()) {
        throw new IllegalStateException("max(): empty stack");
      }
      return elementWithCachedMax.peek().max;
    }

    public Integer pop() {
      if (empty()) {
        throw new IllegalStateException("pop(): empty stack");
      }
      return elementWithCachedMax.removeFirst().element;
    }

    public void push(Integer x) {
      elementWithCachedMax.addFirst(
          new ElementWithCachedMax(x, Math.max(x, empty() ? x : max())));
    }
  }
  // @exclude

  public static void check(boolean condition, String msg) {
    if (!condition) {
      System.err.println(msg);
      System.exit(-1);
    }
  }

  public static void missedMaxException() {
    System.err.println("Should have seen an exception, max() on empty stack!");
    System.exit(-1);
  }

  public static void missedPopException() {
    System.err.println("Should have seen an exception, pop() on empty stack!");
    System.exit(-1);
  }

  public static void main(String[] args) {
    Stack s = new Stack();
    s.push(1);
    s.push(2);
    check(s.max() == 2,
          "failed max() call with stack created by push 1, push 2");
    System.out.println(s.max()); // 2
    System.out.println(s.pop()); // 2
    check(s.max() == 1,
          "failed max() call with stack created by push 1, push 2, pop");
    System.out.println(s.max()); // 1
    s.push(3);
    s.push(2);
    check(
        s.max() == 3,
        "failed max() call with stack created by push 1, push 2, pop, push 3, push 2");
    System.out.println(s.max()); // 3
    s.pop();
    check(
        s.max() == 3,
        "failed max() call with stack created by push 1, push 2, pop, push 3, push 2, pop");
    System.out.println(s.max()); // 3
    s.pop();
    check(
        s.max() == 1,
        "failed max() call with stack created by push 1, push 2, pop, push 3, push 2, pop, pop");
    System.out.println(s.max()); // 1
    s.pop();

    try {
      s.max();
      missedMaxException();
    } catch (RuntimeException e) {
      System.out.println(
          "Got expected exception calling max() on an empty stack:"
          + e.getMessage());
    }

    try {
      s.pop();
      missedPopException();
    } catch (RuntimeException e) {
      System.out.println(
          "Got expected exception calling pop() on an empty stack:"
          + e.getMessage());
    }
  }
}
