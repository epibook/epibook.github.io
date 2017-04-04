package com.epi;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.Date;

public class LogFileAnalysisTest {
  @Test
  public void tmp0() {
    LogProcessor lp = new LogProcessorFast(1);
    lp.add("foo", 1);
    lp.add("bar", 2);
    lp.add("foo", 3);
    System.out.println(lp);
    List<String> r = lp.getOrderedUrlsInWindow(2);
    assertEquals(r.get(0), "bar:1");
  }

  public static void util0(LogProcessor lp) {
    lp.add("foo", 1);
    lp.add("bar", 2);
    lp.add("foo", 3);
    List<String> r0 = lp.getOrderedUrlsInWindow(2);
    assertEquals(r0.get(0), "foo:2");
    assertEquals(r0.get(1), "bar:1");

    lp.add("widget", 4);
    r0 = lp.getOrderedUrlsInWindow(3);
    assertEquals("bar:1", r0.get(0));
    assertEquals("foo:1", r0.get(1));
    assertEquals("widget:1", r0.get(2));

    lp.add("foo", 4);
    r0 = lp.getOrderedUrlsInWindow(3);
    assertEquals(r0.get(0), "foo:2");
    assertEquals(r0.get(1), "bar:1");
    assertEquals(r0.get(2), "widget:1");
  }

  @Test
  public void t0() {
    LogProcessor lp = new LogProcessorSlow(2);
    util0(lp);
    lp = new LogProcessorFast(2);
    util0(lp);
  }

  public void util1(LogProcessor lp) {
    String[] urls = {"a", "b", "c", "d"};
    for (int i = 0; i < 1000; i++) {
      lp.add(urls[i % urls.length], i);
    }
    List<String> r1 = lp.getOrderedUrlsInWindow(1);
    assertEquals(r1.get(0), "a:1");
    r1 = lp.getOrderedUrlsInWindow(4);
    assertEquals(r1.get(3), "d:1");
  }

  @Test
  public void t1() {
    LogProcessor lp = new LogProcessorSlow(3);
    util1(lp);
    lp = new LogProcessorFast(3);
    util1(lp);
  }

  public void util2(LogProcessor lp, int count) {
    String[] urls = {"a", "b", "c", "d", "c", "d", "d"};
    Random rnd = new Random(0);
    for (int i = 0; i < count; i++) {
      int index = rnd.nextInt(urls.length);
      lp.add(urls[index], i);
    }
    List<String> r2 = lp.getOrderedUrlsInWindow(1);
    assertEquals("d", r2.get(0).substring(0, 1));
    r2 = lp.getOrderedUrlsInWindow(2);
    assertEquals("c", r2.get(1).substring(0, 1));
  }

  @Test(timeout = 1000)
  public void t2() {
    LogProcessor lp = new LogProcessorSlow(Integer.MAX_VALUE);
    util2(lp, 1000);
  }

  @Test(timeout = 10000)
  public void t3() {
    LogProcessor lp = new LogProcessorFast(Integer.MAX_VALUE);
    // AA: using t0 and t1 to benchmark
    // my solution takes about 1000 ms to complete this test
    long t0 = new Date().getTime();
    util2(lp, 1000000);
    long t1 = new Date().getTime();
    System.out.println("t3 with fast processor takes " + (t1 - t0) + " ms");
  }

  @Test(timeout = 10000)
  public void t4() {
    LogProcessor lp = new LogProcessorFast(1000);
    long t0 = new Date().getTime();
    util2(lp, 1000000);
    long t1 = new Date().getTime();
    System.out.println("t3 with fast processor takes " + (t1 - t0) + " ms");
  }

  public void util3(LogProcessor lp) {
    String[] urls = {"a", "b", "c", "d", "d", "d", "d", "d", "e", "b", "b"};
    Random rnd = new Random(0);
    for (int i = 0; i < 1000; i++) {
      lp.add(urls[i % urls.length], rnd.nextInt(1000));
    }
    List<String> r1 = lp.getOrderedUrlsInWindow(3);
    assertEquals(r1.get(0).substring(0, 1), "d");
    assertEquals(r1.get(1).substring(0, 1), "b");
  }

  @Test(timeout = 10000)
  public void t5() {
    LogProcessor lp = new LogProcessorFast(500);
    long t0 = new Date().getTime();
    util3(lp);
    long t1 = new Date().getTime();
    System.out.println("t5 with fast processor takes " + (t1 - t0) + " ms");
  }

  public static void util4(LogProcessor lp) {
    String[] urls = {"a", "b", "c", "d"};
    for (int i = 0; i < 1000; i++) {
      lp.add(urls[i % urls.length], i);
    }
    lp.add("foo", 2000);
    lp.add("bar", 2002);
    lp.add("foo", 2003);
    List<String> r0 = lp.getOrderedUrlsInWindow(2);
    assertEquals(r0.get(0), "foo:2");
    assertEquals(r0.get(1), "bar:1");
    urls = new String[] {"a", "b", "c", "d", "d", "d", "d", "d", "e", "b", "b"};
    for (int i = 2000; i < 3000; i++) {
      lp.add(urls[i % urls.length], i);
    }
    List<String> r1 = lp.getOrderedUrlsInWindow(2);
    assertEquals(r1.get(0).substring(0, 1), "d");
    assertEquals(r1.get(1).substring(0, 1), "b");
  }

  @Test(timeout = 10000)
  public void t6() {
    LogProcessor lp = new LogProcessorFast(500);
    long t0 = new Date().getTime();
    util4(lp);
    long t1 = new Date().getTime();
    System.out.println("t6 with fast processor takes " + (t1 - t0) + " ms");
  }

  @Test(timeout = 10000)
  public void t7() {
    LogProcessor lp = new LogProcessorFast(1000);
    long t0 = new Date().getTime();
    String[] urls = {"a", "b", "c", "d"};
    for (int i = 0; i < 10000; i++) {
      lp.add(urls[i % urls.length], i);
    }
    lp.add("foo", 9500);
    lp.add("bar", 9500);
    lp.add("foo", 9500);
    List<String> r0 = lp.getOrderedUrlsInWindow(6);
    assertEquals(r0.get(4), "foo:2");
    assertEquals(r0.get(5), "bar:1");
    long t1 = new Date().getTime();
    System.out.println("t7 with fast processor takes " + (t1 - t0) + " ms");
  }
}
