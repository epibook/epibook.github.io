import time
import threading


# @include
class SpellCheckService:
    w_last = closest_to_last_word = None

    @staticmethod
    def service(req, resp):
        w = req.extract_word_to_check_from_request()
        if w != SpellCheckService.w_last:
            SpellCheckService.w_last = w
            SpellCheckService.closest_to_last_word = closest_in_dictionary(w)
        resp.encode_into_response(SpellCheckService.closest_to_last_word)
# @exclude


class ServiceRequest:

    def __init__(self, s):
        self.request = s

    def extract_word_to_check_from_request(self):
        return self.request


class ServiceResponse:
    response = None

    def encode_into_response(self, s):
        self.response = s


def closest_in_dictionary(w):
    time.sleep(0.2)
    return [w + '_result']


class ServiceThread(threading.Thread):

    lock = threading.Lock()

    def __init__(self, data):
        super().__init__()
        self.data = data

    def run(self):
        start_time = time.time()
        req = ServiceRequest(self.data)
        resp = ServiceResponse()
        with ServiceThread.lock:
            SpellCheckService.service(req, resp)
            print(self.data, '->', resp.response, '(%.3f sec)' %
                  (time.time() - start_time))


def main():
    i = 0
    while True:
        ServiceThread('req:%d' % (i + 1)).start()
        if i > 0:
            # while req:i+1 is computed we could return req:i from the cache
            ServiceThread('req:%d' % i).start()
        time.sleep(0.5)
        i += 1


if __name__ == '__main__':
    main()
