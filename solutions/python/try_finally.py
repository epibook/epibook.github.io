import json


# @include
def get_value(filename, key):
    handle = open(filename)
    try:
        file_contents = handle.read()
        js_text = json.loads(file_contents)
        return js_text[key]
    finally:
        # Prevent resource leak if there is an error parsing file_contents.
        handle.close()
# @exclude

import unittest


class TestTryFinally(unittest.TestCase):
    def test_valid_json(self):
        self.assertEqual(get_value("data-good.json", "foo"), "bar")
        # Need a way to check if file is still open as part of testcase
        #f = open('data-good.json')
        #self.assertFalse(f.closed)

    def test_invalid_json(self):
        with self.assertRaises(ValueError):
            get_value("data.json", "foo")
        #f = open('data.json')
        #self.assertFalse(f.closed)


if __name__ == '__main__':
    unittest.main()
