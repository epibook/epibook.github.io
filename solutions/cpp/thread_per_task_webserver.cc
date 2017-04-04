// Copyright (c) 2015 Elements of Programming Interviews. All rights reserved.

#include <boost/asio.hpp>
#include <iostream>
#include <memory>
#include <thread>

using std::shared_ptr;
using std::cout;
using std::endl;
using std::thread;
using boost::system::error_code;
namespace asio = boost::asio;
using asio::ip::tcp;

void ProcessReq(shared_ptr<tcp::socket> sock) {
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
const unsigned short SERVERPORT = 8080;

int main(int argc, char* argv[]) {
  asio::io_service io_service;
  tcp::acceptor acceptor(io_service, tcp::endpoint(tcp::v4(), SERVERPORT));
  while (true) {
    shared_ptr<tcp::socket> sock(new tcp::socket(io_service));
    acceptor.accept(*sock);
    thread(ProcessReq, sock).detach();
  }
  return 0;
}
// @exclude
