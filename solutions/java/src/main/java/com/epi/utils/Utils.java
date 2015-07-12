package com.epi.utils;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Utils {
  private static Random random;

  public static void close(Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (IOException e) {
      // We want to close "closeable" silently
    }
  }

  public static Pair<Integer, Integer> getCanonicalFractional(int a, int b) {
    int gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
    a /= gcd;
    b /= gcd;
    return b < 0 ? new Pair<>(-a, -b) : new Pair<>(a, b);
  }

  public static <T> boolean nullEqual(T first, T second) {
    return first == null ? second == null : first.equals(second);
  }

  public static int find(int[] array, int x) {
    if (array == null || array.length == 0) {
      return -1;
    }

    for (int i = 0; i < array.length; i++) {
      if (array[i] == x) {
        return i;
      }
    }

    return -1;
  }

  public static <T> ListIterator<T> revListIterator(List<T> list) {
    final ListIterator<T> li = list.listIterator();
    while (li.hasNext()) {
      li.next();
    }
    return new ListIterator<T>() {

      @Override
      public boolean hasNext() {
        return li.hasPrevious();
      }

      @Override
      public T next() {
        return li.previous();
      }

      @Override
      public boolean hasPrevious() {
        return li.hasNext();
      }

      @Override
      public T previous() {
        return li.next();
      }

      @Override
      public int nextIndex() {
        return li.previousIndex();
      }

      @Override
      public int previousIndex() {
        return li.nextIndex();
      }

      @Override
      public void remove() {
        li.remove();
      }

      @Override
      public void set(T e) {
        li.set(e);
      }

      @Override
      public void add(T e) {
        li.add(e);
      }
    };
  }

  /**
   * Fills the given {@link StringBuilder} with the given char.
   *
   * @param sb
   * @param c
   */
  public static void fill(StringBuilder sb, char c) {
    if (sb == null) {
      return;
    }

    for (int i = 0; i < sb.length(); i++) {
      sb.setCharAt(i, c);
    }
  }

  public static <T> void partialSum(Iterator<T> listIter,
                                    ListIterator<T> targetIter,
                                    BinaryOperator<T> op) {
    T result = null;

    while (listIter.hasNext()) {
      result = op.apply(result, listIter.next());
      targetIter.next();
      targetIter.set(result);
    }
  }

  public static <T> List<List<T>> copy(List<List<T>> list) {
    List<List<T>> copy = new ArrayList<>(list.size());

    for (List<T> element : list) {
      copy.add(new ArrayList<>(element));
    }

    return copy;
  }

  public static boolean[][] copy(boolean[][] m) {
    boolean[][] copy = new boolean[m.length][];

    for (int i = 0; i < m.length; i++) {
      copy[i] = Arrays.copyOf(m[i], m[i].length);
    }

    return copy;
  }

  public static int[][] copy(int[][] m) {
    int[][] copy = new int[m.length][];

    for (int i = 0; i < m.length; i++) {
      copy[i] = Arrays.copyOf(m[i], m[i].length);
    }

    return copy;
  }

  public static void copy(int[] a1, int from, int to, int[] a2, int start) {
    for (int i = 0; i < to - from; i++) {
      if (start + i < a2.length) {
        a2[start + i] = a1[from + i];
      }
    }
  }

  public static void shuffle(int[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    Random rnd = random;
    if (rnd == null) {
      random = rnd = new Random();
    }

    for (int i = array.length; i > 1; i--) {
      int newIndex = rnd.nextInt(i);
      int tmp = array[i - 1];
      array[i - 1] = array[newIndex];
      array[newIndex] = tmp;
    }
  }

  public static void reverse(char[] array, int start, int stopIndex) {
    if (array == null || array.length == 0 || start >= stopIndex) {
      return;
    }

    int last = stopIndex - 1;
    for (int i = start; i <= start + (last - start) / 2; i++) {
      char tmp = array[i];
      array[i] = array[last - i + start];
      array[last - i + start] = tmp;
    }
  }

  public static void reverse(int[] array, int start, int stopIndex) {
    if (array == null || array.length == 0 || start >= stopIndex) {
      return;
    }

    int last = stopIndex - 1;
    for (int i = start; i <= start + (last - start) / 2; i++) {
      int tmp = array[i];
      array[i] = array[last - i + start];
      array[last - i + start] = tmp;
    }
  }

  public static int find(char[] array, char c, int start) {
    for (int i = start; i < array.length; i++) {
      if (array[i] == c) {
        return i;
      }
    }

    return -1;
  }

  public static <T> void swap(T[] array, int index1, int index2) {
    T element1Copy = array[index1];
    array[index1] = array[index2];
    array[index2] = element1Copy;
  }

  public static void swap(int[] array, int index1, int index2) {
    int element1Copy = array[index1];
    array[index1] = array[index2];
    array[index2] = element1Copy;
  }

  public static <T> void swap(List<T> list, Integer index1, Integer index2) {
    T element1Copy = list.get(index1);
    list.set(index1, list.get(index2));
    list.set(index2, element1Copy);
  }

  /**
   * Adds <code>numOfElements</code> of elements to the list <code>list</code> ,
   * each being a copy of <code>value</code>.
   *
   * @param <T>
   * @param list
   * @param numOfElements
   * @param value
   */
  public static <T> void fill(List<T> list, int numOfElements, T value) {
    for (int i = 1; i <= numOfElements; ++i) {
      list.add(value);
    }
  }

  /**
   * Fills the range [from, to) with sequentially increasing values, starting
   * with value and repetitively evaluating ++value.
   *
   * @param array
   * @param from
   * @param to
   * @param value
   */
  public static void iota(int[] array, int from, int to, int value) {
    for (int i = from; i < to; ++i) {
      array[i] = value++;
    }
  }

  /**
   * Fills the list with sequentially increasing values, starting with value and
   * repetitively evaluating ++value.
   *
   * @param list
   * @param numOfElements
   * @param value
   */
  public static void iota(List<Integer> list, int numOfElements, int value) {
    for (int i = 1; i <= numOfElements; ++i) {
      list.add(value++);
    }
  }

  /**
   * Fills the list with sequentially increasing values, starting with value and
   * repetitively evaluating ++value.
   *
   * @param list
   * @param numOfElements
   * @param value
   */
  public static void iota(List<Double> list, int numOfElements, double value) {
    for (int i = 1; i <= numOfElements; ++i) {
      list.add(value++);
    }
  }

  public static void simplePrint(boolean[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if (i < array.length - 1) {
        System.out.print(" ");
      }
    }
  }

  public static void simplePrint(int[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if (i < array.length - 1) {
        System.out.print(" ");
      }
    }
  }

  public static <T> void simplePrint(Collection<T> collection) {
    if (collection == null || collection.isEmpty()) {
      return;
    }

    for (Iterator<T> iterator = collection.iterator(); iterator.hasNext();) {
      T t = iterator.next();
      System.out.print(t);
      if (iterator.hasNext()) {
        System.out.print(" ");
      }
    }
  }

  public static <T> boolean equal(List<T> list1, List<T> list2) {
    if (list1.size() != list2.size()) {
      return false;
    }

    for (int i = 0; i < list1.size(); i++) {
      if (!list1.get(i).equals(list2.get(i))) {
        return false;
      }
    }

    return true;
  }

  public static <T extends Comparable<T>> int upperBound2(List<T> arr, T key) {
    int len = arr.size();
    int lo = 0;
    int hi = len - 1;
    int mid = (lo + hi) / 2;
    while (true) {
      int cmp = arr.get(mid).compareTo(key);
      if (cmp <= 0) {
        lo = mid + 1;
        if (hi < lo) {
          return mid < len - 1 ? mid + 1 : len;
        }
      } else {
        hi = mid - 1;
        if (hi < lo) {
          return mid;
        }
      }
      mid = (lo + hi) / 2;
    }
  }

  public static Integer nextPositiveInt(Random gen) {
    Random random = gen == null ? Utils.random : gen;
    int next = random.nextInt();
    return next == Integer.MIN_VALUE ? Integer.MAX_VALUE : next;
  }

  public static Integer nextPositiveInt(Random gen, int maxValue) {
    Random random = gen == null ? Utils.random : gen;
    int next = random.nextInt(maxValue);
    return next == Integer.MIN_VALUE ? Integer.MAX_VALUE : next;
  }
}
