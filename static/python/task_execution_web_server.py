import socket
import concurrent.futures


def process_req(sock):
    while True:
        data = sock.recv(1024)
        if not data:
            break
        print(data)
        sock.sendall(data)


# @include
SERVERPORT = 8080
NTHREADS = 2


executor = concurrent.futures.ThreadPoolExecutor(max_workers=NTHREADS)
serversock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serversock.bind(('', SERVERPORT))
serversock.listen(5)
while True:
    sock, addr = serversock.accept()
    executor.submit(process_req, sock)
# @exclude
