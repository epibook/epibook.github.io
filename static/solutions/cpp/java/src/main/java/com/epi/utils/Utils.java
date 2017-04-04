package com.epi.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Utils {
  private static Random random;

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

  /**
   * Fills the range [from, to) with sequentially increasing values, starting
   * with value and repetitively evaluating ++value.
   */
  public static void iota(int[] array, int from, int to, int value) {
    for (int i = from; i < to; ++i) {
      array[i] = value++;
    }
  }

  /**
   * Fills the list with sequentially increasing values, starting with value and
   * repetitively evaluating ++value.
   */
  public static void iota(List<Integer> list, int numOfElements, int value) {
    for (int i = 1; i <= numOfElements; ++i) {
      list.add(value++);
    }
  }

  /**
   * Fills the list with sequentially increasing values, starting with value and
   * repetitively evaluating ++value.
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

  public static <T> ObjectInputStream objectInputStreamFromList(List<T> list) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ByteArrayInputStream sin = null;
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(baos);
      for (T s : list) {
        oos.writeObject(s);
      }
      oos.close();
      sin = new ByteArrayInputStream(baos.toByteArray());
      return new ObjectInputStream(sin);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
    return null;
  }

  public static void closeSilently(Closeable... closeables) {
    if (closeables != null) {
      for (Closeable closeable : closeables) {
        if (closeable != null) {
          try {
            closeable.close();
          } catch (Exception e) {
            // We dont' care.
          }
        }
      }
    }
  }
}
