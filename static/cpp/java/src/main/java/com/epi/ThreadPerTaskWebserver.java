import java.net.*;
import java.io.*;

public class ThreadPerTaskWebserver {
  // @include
  public static class ConcurrentWebServer {
    private static final int SERVERPORT = 8080;
    public static void main(String[] args) throws IOException {
      final ServerSocket serversocket = new ServerSocket(SERVERPORT);
      while (true) {
        final Socket connection = serversocket.accept();
        Runnable task = new Runnable() {
          public void run() { Worker.handleRequest(connection); }
        };
        new Thread(task).start();
      }
    }
  }
  // @exclude
}
