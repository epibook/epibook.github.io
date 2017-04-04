import socket


def process_req(sock):
    while True:
        data = sock.recv(1024)
        if not data:
            break
        print(data)
        sock.sendall(data)


# @include
SERVERPORT = 8080


def main():
    serversock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serversock.bind(('', SERVERPORT))
    serversock.listen(5)
    while True:
        sock, addr = serversock.accept()
        process_req(sock)
# @exclude

if __name__ == '__main__':
    main()
