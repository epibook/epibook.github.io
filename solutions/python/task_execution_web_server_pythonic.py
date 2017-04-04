import concurrent.futures
import socketserver


class WebServerHandler(socketserver.BaseRequestHandler):

    def handle(self):
        while True:
            data = self.request.recv(1024)
            if not data:
                break
            print(data)
            self.request.sendall(data)


class ThreadPoolMixIn:

    def __init__(self, n_threads, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.executor = concurrent.futures.ThreadPoolExecutor(
            max_workers=n_threads)

    def process_request_thread(self, request, client_address):
        try:
            self.finish_request(request, client_address)
            self.shutdown_request(request)
        except:
            self.handle_error(request, client_address)
            self.shutdown_request(request)

    def process_request(self, request, client_address):
        self.executor.submit(self.process_request_thread, request,
                             client_address)


class ThreadPoolSocketServer(ThreadPoolMixIn, socketserver.TCPServer):
    pass


SERVERPORT = 8080
NTHREADS = 2


def main():
    server = ThreadPoolSocketServer(NTHREADS,
                                    ('', SERVERPORT), WebServerHandler)
    server.serve_forever()


if __name__ == '__main__':
    main()
