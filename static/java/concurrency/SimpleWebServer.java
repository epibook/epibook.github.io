// @include
  public class SimpleWebServer {
    final static int PORT = 8080;
    public static void main (String [] args) throws IOException {
      ServerSocket serversock = new ServerSocket(PORT);
      for (;;) {
        Socket sock = serversock.accept();
        ProcessReq(sock);
      }
    }
  }
// @exclude
