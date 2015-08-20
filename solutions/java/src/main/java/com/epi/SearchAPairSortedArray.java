// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

package com.epi;

import java.util.*;

public class SearchAPairSortedArray {
  // @include
  private static class IndexPair {
    public Integer index1;
    public Integer index2;

    public IndexPair(Integer index1, Integer index2) {
      this.index1 = index1;
      this.index2 = index2;
    }
  }
  public static IndexPair findPairSumK(List<Integer> A, int k) {
    IndexPair result = findPosNegPair(A, k);
    if (result.index1 == -1 && result.index2 == -1) {
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

  private static IndexPair findPairUsingComp(List<Integer> A, int k,
                                             Comparator<Integer> comp) {
    IndexPair result = new IndexPair(0, A.size() - 1);
    while (result.index1 < result.index2 &&
           comp.compare(A.get(result.index1), 0) == 0) {
      result.index1 = result.index1 + 1;
    }
    while (result.index1 < result.index2 &&
           comp.compare(A.get(result.index2), 0) == 0) {
      result.index2 = result.index2 - 1;
    }

    while (result.index1 < result.index2) {
      if (A.get(result.index1) + A.get(result.index2) == k) {
        return result;
      } else if (comp.compare(A.get(result.index1) + A.get(result.index2), k) ==
                 0) {
        do {
          result.index1 = result.index1 + 1;
        } while (result.index1 < result.index2 &&
                 comp.compare(A.get(result.index1), 0) == 0);
      } else {
        do {
          result.index2 = result.index2 - 1;
        } while (result.index1 < result.index2 &&
                 comp.compare(A.get(result.index2), 0) == 0);
      }
    }
    return new IndexPair(-1, -1); // No answer.
  }

  private static IndexPair findPosNegPair(List<Integer> A, int k) {
    // result.first for positive, and result.second for negative.
    IndexPair result = new IndexPair(A.size() - 1, A.size() - 1);
    // Find the last positive or zero.
    while (result.index1 >= 0 && A.get(result.index1) < 0) {
      result.index1 = result.index1 - 1;
    }

    // Find the last negative.
    while (result.index2 >= 0 && A.get(result.index2) >= 0) {
      result.index2 = result.index2 - 1;
    }

    while (result.index1 >= 0 && result.index2 >= 0) {
      if (A.get(result.index1) + A.get(result.index2) == k) {
        return result;
      } else if (A.get(result.index1) + A.get(result.index2) > k) {
        do {
          result.index1 = result.index1 - 1;
        } while (result.index1 >= 0 && A.get(result.index1) < 0);
      } else { // A[result.first] + A[result.second] < k.
        do {
          result.index2 = result.index2 - 1;
        } while (result.index2 >= 0 && A.get(result.index2) >= 0);
      }
    }
    return new IndexPair(-1, -1); // No answer.
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
      IndexPair ans = findPairSumK(A, k);
      if (ans.index1 != -1 && ans.index2 != -1) {
        assert(A.get(ans.index1) + A.get(ans.index2) == k);
        System.out.println(A.get(ans.index1) + "+" + A.get(ans.index2) + "=" + k);
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
