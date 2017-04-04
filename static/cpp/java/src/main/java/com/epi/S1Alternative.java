package com.epi;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;

public class S1Alternative {
  // @include
  public static class UnsafeSpellCheckService extends SpellCheckService {
    private static final int MAX_ENTRIES = 3;

    private static LinkedHashMap<String, String[]> cachedClosestStrings
        = new LinkedHashMap<String, String[]>() {
            protected boolean removeEldestEntry(Map.Entry eldest) {
              return size() > MAX_ENTRIES;
            }
          };

    public static void service(ServiceRequest req, ServiceResponse resp) {
      String w = req.extractWordToCheckFromRequest();
      if (cachedClosestStrings.containsKey(w)) {
        resp.encodeIntoResponse(cachedClosestStrings.get(w));
        return;
      }
      String[] closestToLastWord = Spell.closestInDictionary(w);
      cachedClosestStrings.put(w, closestToLastWord);
    }
  }
  // @exclude

  static class Comm {
    String s;
    String[] result = {"dummycomm"};
    Comm(int i) {
      this.s = "" + i;
      this.result = new String[] {"" + i, "" + (i + 1)};
    }
    @Override
    public String toString() {
      return s + ":" + Arrays.toString(result);
    }
  }

  static class T implements Runnable {
    Comm comm = null;
    public T(Comm comm) { this.comm = comm; }
    public void run() {
      synchronized (comm) {
        ServiceResponse resp = new ServiceResponse();
        ServiceRequest req = new ServiceRequest(comm.s);
        SpellCheckService.service(req, resp);
        comm.result = resp.response;
        System.out.println(comm.s + " = " + Arrays.toString(comm.result));
      }
    }
  }

  public static void main(String[] args) {
    Comm acomm = new Comm(0);
    Comm bcomm = new Comm(1);
    T a = new T(acomm);
    T b = new T(bcomm);
    Thread A = new Thread(a);
    Thread B = new Thread(b);
    int i = 0;
    while (true) {
      synchronized (acomm) { acomm.s = "req:" + i++; }
      synchronized (bcomm) { bcomm.s = "req:" + i++; }
      try {
        Thread.sleep(100, 300);
      } catch (Exception e) {
        e.printStackTrace();
      }
      synchronized (acomm) {
        System.out.println(acomm.s + Arrays.toString(acomm.result));
      }
      synchronized (bcomm) {
        System.out.println(bcomm.s + Arrays.toString(bcomm.result));
      }
      A.run();
      B.run();
    }
  }
}

class SpellCheckService {
  public static void service(ServiceRequest req, ServiceResponse resp){};
}

class ServiceRequest {
  String request = "dummy";
  public String extractWordToCheckFromRequest() { return request; }
  public ServiceRequest(String s) { request = s; }
}

class ServiceResponse {
  String[] response = {"dummy"};
  public void encodeIntoResponse(String[] s) { response = s; }
}

class Spell {
  public static String[] closestInDictionary(String w) {
    try {
      Thread.sleep(100, 300);
    } catch (Exception e) {
      e.printStackTrace();
    };
    return new String[] {w};
  }
}
