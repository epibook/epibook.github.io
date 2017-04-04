import time
import threading
import ctypes


# @include
class Requestor:
    # @exclude

    def __init__(self, request, delay):
        self.req = request
        self.delay = delay

    def finished(self):
        return 'response:' + self.req

    def error(self):
        return 'response:' + self.req + ':TIMEDOUT'

# @include

    def execute(self):
        try:
            # simulate the time taken to perform a computation
            time.sleep(self.delay)
        except SystemExit:
            return self.error()
        return self.finished()
# @exclude

    def process_response(self, response):
        print('ProcessResponse:' + response)

# @include

    def dispatch(self):
        def task():
            def actual_task():
                response = self.execute()
                self.process_response(response)

            inner_thread = threading.Thread(target=actual_task)
            inner_thread.start()
            inner_thread.join(TIMEOUT)
            if inner_thread.is_alive():
                ctypes.pythonapi.PyThreadState_SetAsyncExc(
                    inner_thread.ident, ctypes.py_object(SystemExit))

        threading.Thread(target=task).start()


# @exclude

TIMEOUT = 0.5


def main():
    Requestor('t1', 1).dispatch()
    Requestor('t2', 0.1).dispatch()
    Requestor('t3', 0.01).dispatch()
    Requestor('t4', 0.001).dispatch()
    Requestor('t5', 0.2).dispatch()


if __name__ == '__main__':
    main()
