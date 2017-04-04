import socketserver


class WebServerHandler(socketserver.BaseRequestHandler):

    def handle(self):
        while True:
            data = self.request.recv(1024)
            if not data:
                break
            print(data)
            self.request.sendall(data)


SERVERPORT = 8080


def main():
    server = socketserver.TCPServer(('', SERVERPORT), WebServerHandler)
    server.serve_forever()


if __name__ == '__main__':
    main()
