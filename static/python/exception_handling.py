import csv


# @include
class ColSumCsvParseException(Exception):
    def __init__(self, *args):
        Exception.__init__(self, *args)
        self.line_number = args[1]


def get_col_sum(filename, col):
    # may raise IOError, will propagate to caller
    csv_file = open(filename)
    csv_reader = csv.reader(csv_file)
    running_sum = line_number = 0
    try:
        for row in csv_reader:
            if col >= len(row):
                raise IndexError("Not enough entries in row " + str(row))
            value = row[col]
            # We will skip rows for which the corresponding columns cannot be
            # parsed to an int, logging the fact.
            try:
                running_sum += int(value)
            except ValueError:
                print("Cannot convert " + value + " to int, ignoring")
            line_number += 1
    except csv.Error:
        # Programs should raise exceptions appropriate to their level of
        # abstraction, so we propagate the csv.Error upwards as a
        # ColSumCsvParseException.
        print("In csv.Error handler")
        raise ColSumCsvParseException("Error processing csv", line_number)
    else:
        print("Sum = " + str(running_sum))
    finally:
        # Ensure there is no resource leak.
        csv_file.close()
        return running_sum
# @exclude

# try:
#     get_col_sum("datanone.csv", 0)
# except ColSumCsvParseException as e:
#     print("caught ColSumCsvParseException")
# else:
#     raise Exception("expected exception not seen")

import unittest


class TestCsv(unittest.TestCase):
    def test_1(self):
        val = get_col_sum("data.csv", 0)
        self.assertEquals(579, val)

    def test_2(self):
        val = get_col_sum("data.csv", 1)
        self.assertEquals(666, val)


if __name__ == '__main__':
    unittest.main()
