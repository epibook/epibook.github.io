package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.epi.utils.Pair;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SearchAPairSortedArrayTemplate {
  // @include
  public static Pair<Integer, Integer> findPairUsingComp(List<Integer> A,
      int k, Comparator<Integer> comp) {
    Pair<Integer, Integer> ret = new Pair<Integer, Integer>(0, A.size() - 1);
    while (ret.getFirst() < ret.getSecond()
        && comp.compare(A.get(ret.getFirst()), 0) == 0) {
      ret.setFirst(ret.getFirst() + 1);
    }
    while (ret.getFirst() < ret.getSecond()
        && comp.compare(A.get(ret.getSecond()), 0) == 0) {
      ret.setSecond(ret.getSecond() - 1);
    }

    while (ret.getFirst() < ret.getSecond()) {
      if (A.get(ret.getFirst()) + A.get(ret.getSecond()) == k) {
        return ret;
      } else if (comp
          .compare(A.get(ret.getFirst()) + A.get(ret.getSecond()), k) == 0) {
        do {
          ret.setFirst(ret.getFirst() + 1);
        } while (ret.getFirst() < ret.getSecond()
            && comp.compare(A.get(ret.getFirst()), 0) == 0);
      } else {
        do {
          ret.setSecond(ret.getSecond() - 1);
        } while (ret.getFirst() < ret.getSecond()
            && comp.compare(A.get(ret.getSecond()), 0) == 0);
      }
    }
    return new Pair<Integer, Integer>(-1, -1); // no answer.
  }

  public static Pair<Integer, Integer> findPosNegPair(List<Integer> A, int k) {
    // ret.first for positive, and ret.second for negative.
    Pair<Integer, Integer> ret = new Pair<Integer, Integer>(A.size() - 1,
        A.size() - 1);
    // Find the last positive or zero.
    while (ret.getFirst() >= 0 && A.get(ret.getFirst()) < 0) {
      ret.setFirst(ret.getFirst() - 1);
    }

    // Find the last negative.
    while (ret.getSecond() >= 0 && A.get(ret.getSecond()) >= 0) {
      ret.setSecond(ret.getSecond() - 1);
    }

    while (ret.getFirst() >= 0 && ret.getSecond() >= 0) {
      if (A.get(ret.getFirst()) + A.get(ret.getSecond()) == k) {
        return ret;
      } else if (A.get(ret.getFirst()) + A.get(ret.getSecond()) > k) {
        do {
          ret.setFirst(ret.getFirst() - 1);
        } while (ret.getFirst() >= 0 && A.get(ret.getFirst()) < 0);
      } else { // A[ret.first] + A[ret.second] < k.
        do {
          ret.setSecond(ret.getSecond() - 1);
        } while (ret.getSecond() >= 0 && A.get(ret.getSecond()) >= 0);
      }
    }
    return new Pair<Integer, Integer>(-1, -1); // no answer.
  }

  public static Pair<Integer, Integer> findPairSumK(List<Integer> A, int k) {
    Pair<Integer, Integer> ret = findPosNegPair(A, k);
    if (ret.getFirst() == -1 && ret.getSecond() == -1) {
      return k >= 0 ? findPairUsingComp(A, k, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          if (o1 < o2) {
            return 0;
          }
          return o1.compareTo(o2);
        }
      }) : findPairUsingComp(A, k, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          if (o1 >= o2) {
            return 0;
          }
          return o1.compareTo(o2);
        }
      });
    }
    return ret;
  }

  // @exclude

  public static void main(String[] args) {
    Random rand = new Random();
    for (int times = 0; times < 10000; ++times) {
      int n;
      if (args.length >= 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = rand.nextInt(10000) + 1;
      }
      ArrayList<Integer> A = new ArrayList<Integer>();
      for (int i = 0; i < n; ++i) {
        A.add(rand.nextInt(19999) - 9999);
      }
      Collections.sort(A, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return Integer.valueOf(Math.abs(o1)).compareTo(Math.abs(o2));
        }
      });
      int k = rand.nextInt(19999) - 9999;
      // System.out.println(A);
      // System.out.println(k);
      Pair<Integer, Integer> ans = findPairSumK(A, k);
      if (ans.getFirst() != -1 && ans.getSecond() != -1) {
        assert (A.get(ans.getFirst()) + A.get(ans.getSecond()) == k);
        System.out.println(A.get(ans.getFirst()) + "+" + A.get(ans.getSecond())
            + "=" + k);
      } else {
        Collections.sort(A);
        int l = 0, r = A.size() - 1;
        boolean found = false;
        while (l < r) {
          if (A.get(l) + A.get(r) == k) {
            System.out.println(A.get(l) + "+" + A.get(r) + "=" + k);
            Collections.sort(A, new Comparator<Integer>() {
              @Override
              public int compare(Integer o1, Integer o2) {
                return Integer.valueOf(Math.abs(o1)).compareTo(Math.abs(o2));
              }
            });
            ans = findPairSumK(A, k);
            found = true;
            break;
          } else if (A.get(l) + A.get(r) < k) {
            ++l;
          } else {
            --r;
          }
        }
        System.out.println("no answer");
        assert (!found);
      }
    }
  }
}
