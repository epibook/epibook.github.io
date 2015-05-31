package com.epi;

import com.epi.utils.Ref;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingSkylines {
  // @include
  public static class Rectangle {
    public int left, right, height;

    public Rectangle(int left, int right, int height) {
      this.left = left;
      this.right = right;
      this.height = height;
    }
  }

  public static List<Rectangle> drawingSkylines(List<Rectangle> buildings) {
    return computeSkylineInInterval(buildings, 0, buildings.size());
  }

  private static List<Rectangle> computeSkylineInInterval(
      List<Rectangle> buildings, int leftEndpoint, int rightEndpoint) {
    if (rightEndpoint - leftEndpoint <= 1) { // 0 or 1 skyline, just copy it.
      return new ArrayList<>(buildings.subList(leftEndpoint, rightEndpoint));
    }
    int mid = leftEndpoint + ((rightEndpoint - leftEndpoint) / 2);
    List<Rectangle> leftSkyline =
        computeSkylineInInterval(buildings, leftEndpoint, mid);
    List<Rectangle> rightSkyline =
        computeSkylineInInterval(buildings, mid, rightEndpoint);
    return mergeSkylines(leftSkyline, rightSkyline);
  }

  private static List<Rectangle> mergeSkylines(List<Rectangle> leftSkyline,
                                               List<Rectangle> rightSkyline) {
    int i = 0, j = 0;
    List<Rectangle> merged = new ArrayList<>();

    while (i < leftSkyline.size() && j < rightSkyline.size()) {
      if (leftSkyline.get(i).right < rightSkyline.get(j).left) {
        merged.add(leftSkyline.get(i++));
      } else if (rightSkyline.get(j).right < leftSkyline.get(i).left) {
        merged.add(rightSkyline.get(j++));
      } else if (leftSkyline.get(i).left <= rightSkyline.get(j).left) {
        Ref<Integer> iWrapper = new Ref<>(i);
        Ref<Integer> jWrapper = new Ref<>(j);
        mergeIntersectSkylines(merged, leftSkyline.get(i), iWrapper,
                               rightSkyline.get(j), jWrapper);
        i = iWrapper.value;
        j = jWrapper.value;
      } else { // leftSkyline.get(i).left > rightSkyline.get(j).left.
        Ref<Integer> iWrapper = new Ref<>(i);
        Ref<Integer> jWrapper = new Ref<>(j);
        mergeIntersectSkylines(merged, rightSkyline.get(j), jWrapper,
                               leftSkyline.get(i), iWrapper);
        i = iWrapper.value;
        j = jWrapper.value;
      }
    }
    merged.addAll(leftSkyline.subList(i, leftSkyline.size()));
    merged.addAll(rightSkyline.subList(j, rightSkyline.size()));
    return merged;
  }

  private static void mergeIntersectSkylines(List<Rectangle> merged, Rectangle a,
                                             Ref<Integer> aIdx, Rectangle b,
                                             Ref<Integer> bIdx) {
    if (a.right <= b.right) {
      if (a.height > b.height) {
        if (b.right != a.right) {
          merged.add(a);
          aIdx.value = aIdx.value + 1;
          b.left = a.right;
        } else {
          bIdx.value = bIdx.value + 1;
        }
      } else if (a.height == b.height) {
        b.left = a.left;
        aIdx.value = aIdx.value + 1;
      } else { // a->height < b->height.
        if (a.left != b.left) {
          merged.add(new Rectangle(a.left, b.left, a.height));
        }
        aIdx.value = aIdx.value + 1;
      }
    } else { // a.right > b.right.
      if (a.height >= b.height) {
        bIdx.value = bIdx.value + 1;
      } else {
        if (a.left != b.left) {
          merged.add(new Rectangle(a.left, b.left, a.height));
        }
        a.left = b.right;
        merged.add(b);
        bIdx.value = bIdx.value + 1;
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 2000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        n = r.nextInt(5000) + 1;
      }
      List<Rectangle> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        int left = r.nextInt(1000);
        int right = r.nextInt(201) + left;
        int height = r.nextInt(100);
        A.add(new Rectangle(left, right, height));
      }
      List<Rectangle> ans = drawingSkylines(A);
      System.out.println("n = " + n);
      // Just check there is no overlap.
      for (int i = 0; i < ans.size(); ++i) {
        assert(ans.get(i).left <= ans.get(i).right);
        if (i > 0) {
          assert(ans.get(i - 1).right <= ans.get(i).left);
          assert(ans.get(i - 1).right != ans.get(i).left ||
                 ans.get(i - 1).height != ans.get(i).height);
        }
      }
    }
  }
}
