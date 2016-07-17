package com.epi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServer {
  // @include
  public static class SingleThreadWebServer {
    public static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
      ServerSocket serversock = new ServerSocket(PORT);
      for (;;) {
        Socket sock = serversock.accept();
        processReq(sock);
      }
    }
    // @exclude
    static void processReq(Socket sock) { return; }
    // @include
  }
  // @exclude
}
