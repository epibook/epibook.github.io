import time
import threading


# @include
class SpellCheckService:
    w_last = closest_to_last_word = None
    lock = threading.Lock()

    @staticmethod
    def service(req, resp):
        w = req.extract_word_to_check_from_request()
        result = None
        with SpellCheckService.lock:
            if w == SpellCheckService.w_last:
                result = SpellCheckService.closest_to_last_word.copy()
        if result is None:
            result = closest_in_dictionary(w)
            with SpellCheckService.lock:
                SpellCheckService.w_last = w
                SpellCheckService.closest_to_last_word = result
        resp.encode_into_response(result)
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

    def __init__(self, data):
        super().__init__()
        self.data = data

    def run(self):
        start_time = time.time()
        req = ServiceRequest(self.data)
        resp = ServiceResponse()
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
