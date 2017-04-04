import json


# @include
def get_value(filename, key):
    handle = open(filename)
    try:
        file_contents = handle.read()
        js_text = json.loads(file_contents)
        return (True, js_text[key])
    except ValueError:
        return (False, )
    finally:
        handle.close()
# @exclude

print(get_value("data.json", "foo"))

import unittest


class TestTryExceptFinally(unittest.TestCase):
    def test_valid_json(self):
        self.assertEqual(
            get_value("try_except.json", "David Gower"), (True, "UK"))

    def test_key_error(self):
        with self.assertRaises(KeyError):
            get_value("try_except.json", "Viv Richards")


if __name__ == '__main__':
    unittest.main()
