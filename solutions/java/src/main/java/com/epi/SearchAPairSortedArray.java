// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import com.epi.utils.Pair;

import java.util.*;

public class SearchAPairSortedArray {
  // @include
  public static Pair<Integer, Integer> findPairSumK(List<Integer> A, int k) {
    Pair<Integer, Integer> result = findPosNegPair(A, k);
    if (result.getFirst() == -1 && result.getSecond() == -1) {
      return k >= 0
          ? findPairUsingComp(A, k,
                              new Comparator<Integer>() {
                                @Override
                                public int compare(Integer o1, Integer o2) {
                                  if (o1 < o2) {
                                    return 0;
                                  }
                                  return o1.compareTo(o2);
                                }
                              })
          : findPairUsingComp(A, k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
              if (o1 >= o2) {
                return 0;
              }
              return o1.compareTo(o2);
            }
          });
    }
    return result;
  }

  private static Pair<Integer, Integer> findPairUsingComp(
      List<Integer> A, int k, Comparator<Integer> comp) {
    Pair<Integer, Integer> result = new Pair<>(0, A.size() - 1);
    while (result.getFirst() < result.getSecond() &&
           comp.compare(A.get(result.getFirst()), 0) == 0) {
      result.setFirst(result.getFirst() + 1);
    }
    while (result.getFirst() < result.getSecond() &&
           comp.compare(A.get(result.getSecond()), 0) == 0) {
      result.setSecond(result.getSecond() - 1);
    }

    while (result.getFirst() < result.getSecond()) {
      if (A.get(result.getFirst()) + A.get(result.getSecond()) == k) {
        return result;
      } else if (comp.compare(
                     A.get(result.getFirst()) + A.get(result.getSecond()), k) ==
                 0) {
        do {
          result.setFirst(result.getFirst() + 1);
        } while (result.getFirst() < result.getSecond() &&
                 comp.compare(A.get(result.getFirst()), 0) == 0);
      } else {
        do {
          result.setSecond(result.getSecond() - 1);
        } while (result.getFirst() < result.getSecond() &&
                 comp.compare(A.get(result.getSecond()), 0) == 0);
      }
    }
    return new Pair<>(-1, -1); // No answer.
  }

  private static Pair<Integer, Integer> findPosNegPair(List<Integer> A, int k) {
    // result.first for positive, and result.second for negative.
    Pair<Integer, Integer> result = new Pair<>(A.size() - 1, A.size() - 1);
    // Find the last positive or zero.
    while (result.getFirst() >= 0 && A.get(result.getFirst()) < 0) {
      result.setFirst(result.getFirst() - 1);
    }

    // Find the last negative.
    while (result.getSecond() >= 0 && A.get(result.getSecond()) >= 0) {
      result.setSecond(result.getSecond() - 1);
    }

    while (result.getFirst() >= 0 && result.getSecond() >= 0) {
      if (A.get(result.getFirst()) + A.get(result.getSecond()) == k) {
        return result;
      } else if (A.get(result.getFirst()) + A.get(result.getSecond()) > k) {
        do {
          result.setFirst(result.getFirst() - 1);
        } while (result.getFirst() >= 0 && A.get(result.getFirst()) < 0);
      } else { // A[result.first] + A[result.second] < k.
        do {
          result.setSecond(result.getSecond() - 1);
        } while (result.getSecond() >= 0 && A.get(result.getSecond()) >= 0);
      }
    }
    return new Pair<>(-1, -1); // No answer.
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
      List<Integer> A = new ArrayList<>();
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
        assert(A.get(ans.getFirst()) + A.get(ans.getSecond()) == k);
        System.out.println(A.get(ans.getFirst()) + "+" + A.get(ans.getSecond()) +
                           "=" + k);
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
        assert(!found);
      }
    }
  }
}
