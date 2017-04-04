package com.epi;

// @include
public class S1 extends SpellCheckService {
  static String wLast = null;
  static String[] closestToLastWord = null;

  public static void service(ServiceRequest req, ServiceResponse resp) {
    String w = req.extractWordToCheckFromRequest();
    if (!w.equals(wLast)) {
      wLast = w;
      closestToLastWord = Spell.closestInDictionary(w);
    }
    resp.encodeIntoResponse(closestToLastWord);
  }
  // @exclude

  static class Comm {
    String s;
    String[] result = {"dummycomm"};
  }

  static class T implements Runnable {
    Comm comm = null;
    public T(Comm c) { this.comm = c; }
    public void run() {
      synchronized (comm) {
        ServiceResponse resp = new ServiceResponse();
        ServiceRequest req = new ServiceRequest(comm.s);
        SpellCheckService.service(req, resp);
        comm.result = resp.response;
        System.out.println(comm.s + " = " + comm.result);
      }
    }
  }

  public static void main(String[] args) {
    Comm acomm = new Comm();
    T a = new T(acomm);
    Thread A = new Thread(a);
    int i = 0;
    while (true) {
      synchronized (acomm) { acomm.s = "req:" + i++; }
      try {
        Thread.sleep(100, 300);
      } catch (Exception e) {
        e.printStackTrace();
      }
      synchronized (acomm) { System.out.println(acomm.s + acomm.result[0]); }
      A.run();
    }
  }
  // @include
}
//@exclude
