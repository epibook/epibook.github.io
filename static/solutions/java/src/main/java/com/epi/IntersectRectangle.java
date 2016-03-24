package com.epi;

import java.util.Random;

public class IntersectRectangle {
  // @include
  public static class Rectangle {
    int x, y, width, height;

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    // @exclude
    public void print(String s) {
      System.out.println(s + x + " " + y + " " + width + " " + height);
    }
    // @include
  }

  public static Rectangle intersectRectangle(Rectangle R1, Rectangle R2) {
    if (isIntersect(R1, R2)) {
      return new Rectangle(
          Math.max(R1.x, R2.x), Math.max(R1.y, R2.y),
          Math.min(R1.x + R1.width, R2.x + R2.width) - Math.max(R1.x, R2.x),
          Math.min(R1.y + R1.height, R2.y + R2.height) - Math.max(R1.y, R2.y));
    }
    return new Rectangle(0, 0, -1, -1); // No intersection.
  }

  public static boolean isIntersect(Rectangle R1, Rectangle R2) {
    return R1.x <= R2.x + R2.width && R1.x + R1.width >= R2.x
        && R1.y <= R2.y + R2.height && R1.y + R1.height >= R2.y;
  }
  // @exclude

  private static void smallTest() {
    Rectangle R1, R2;
    R1 = new Rectangle(0, 0, 2, 2);
    R2 = new Rectangle(1, 1, 3, 3);
    Rectangle result = intersectRectangle(R1, R2);
    assert(result.x == 1 && result.y == 1 && result.width == 1
           && result.height == 1);
    R1 = new Rectangle(0, 0, 1, 1);
    R2 = new Rectangle(1, 1, 3, 3);
    result = intersectRectangle(R1, R2);
    assert(result.x == 1 && result.y == 1 && result.width == 0
           && result.height == 0);
    R1 = new Rectangle(0, 0, 1, 1);
    R2 = new Rectangle(2, 2, 3, 3);
    result = intersectRectangle(R1, R2);
    assert(result.x == 0 && result.y == 0 && result.width == -1
           && result.height == -1);
  }

  public static void main(String[] args) {
    smallTest();
    for (int times = 0; times < 10000; ++times) {
      Rectangle R1, R2;
      if (args.length == 8) {
        R1 = new Rectangle(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                           Integer.parseInt(args[2]),
                           Integer.parseInt(args[3]));
        R2 = new Rectangle(Integer.parseInt(args[4]), Integer.parseInt(args[5]),
                           Integer.parseInt(args[6]),
                           Integer.parseInt(args[7]));
      } else {
        Random r = new Random();
        R1 = new Rectangle(r.nextInt(100) + 1, r.nextInt(100) + 1,
                           r.nextInt(100) + 1, r.nextInt(100) + 1);
        R2 = new Rectangle(r.nextInt(100) + 1, r.nextInt(100) + 1,
                           r.nextInt(100) + 1, r.nextInt(100) + 1);
      }
      // Intersect rectangle.
      boolean res = isIntersect(R1, R2);
      System.out.println(res);
      Rectangle ans = intersectRectangle(R1, R2);
      ans.print("ans: ");
      assert(!res || (ans.width >= 0 && ans.height >= 0));
    }
  }
}
