// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <boost/asio.hpp>
#include <boost/thread/sync_bounded_queue.hpp>
#include <functional>
#include <iostream>
#include <memory>
#include <thread>
#include <utility>

using std::move;
using std::unique_ptr;
using std::ref;
using std::cout;
using std::endl;
using std::thread;
using boost::system::error_code;
using boost::sync_bounded_queue;
namespace asio = boost::asio;
using asio::ip::tcp;

typedef sync_bounded_queue<unique_ptr<tcp::socket>> QueueType;

void ProcessReq(unique_ptr<tcp::socket>& sock) {
  asio::streambuf sb;
  while (true) {
    error_code e;
    size_t n = asio::read_until(*sock, sb, '\n', e);
    if (e == asio::error::eof) {
      cout << endl << "connection closed" << endl;
      break;
    }
    asio::write(*sock, sb.data());
    cout << &sb;
  }
}

// @include
void ThreadFunc(QueueType& q) {
  while (true) {
    unique_ptr<tcp::socket> sock;
    q >> sock;
    ProcessReq(sock);
  }
}

const unsigned short kServerPort = 8080;
const int kNThreads = 2;

int main(int argc, char* argv[]) {
  QueueType q(kNThreads);
  for (int i = 0; i < kNThreads; ++i) {
    thread(ThreadFunc, ref(q)).detach();
  }
  asio::io_service io_service;
  tcp::acceptor acceptor(io_service, tcp::endpoint(tcp::v4(), kServerPort));
  while (true) {
    unique_ptr<tcp::socket> sock(new tcp::socket(io_service));
    acceptor.accept(*sock);
    q << move(sock);
  }
  return 0;
}
// @exclude
