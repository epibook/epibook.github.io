import socket
import threading


def process_req(sock):
    while True:
        data = sock.recv(1024)
        if not data:
            break
        print(data)
        sock.sendall(data)


# @include
SERVERPORT = 8080


serversock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serversock.bind(('', SERVERPORT))
serversock.listen(5)
while True:
    sock, addr = serversock.accept()
    threading.Thread(target=process_req, args=(sock, )).start()
# @exclude
