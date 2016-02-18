// @exclude
import java.net.*;
import java.io.*;
// @include
  class ThreadPerTaskWebServer {
    private static final int SERVERPORT = 8080;
    public static void main(String [] args) throws IOException {
     final ServerSocket serversocket = new ServerSocket(SERVERPORT);
      while (true) {
        final Socket connection = serversocket.accept();
        Runnable task = new Runnable() {
          public void run() {
            Worker.handleRequest(connection);
          }
        };
        new Thread(task).start();
      }
    }
  }
// @exclude
