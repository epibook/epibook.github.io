import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class TaskExecutionWebServer {
  // @include
  public static class ThreadPoolWebServer {
    private static final int NTHREADS = 100;
    private static final int SERVERPORT = 8080;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
      ServerSocket serversocket = new ServerSocket(SERVERPORT);
      while (true) {
        final Socket connection = serversocket.accept();
        Runnable task = new Runnable() {
          public void run() { Worker.handleRequest(connection); }
        };
        exec.execute(task);
      }
    }
  }
  // @exclude
}

class Worker {
  static void handleRequest(Socket connection) {}
}
