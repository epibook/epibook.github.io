import csv


# @include
def get_col_sum(filename, col):
    # May raise IOError.
    csv_file = open(filename)
    csv_reader = csv.reader(csv_file)
    running_sum = 0
    for row in csv_reader:
        value = row[col]
        running_sum += int(value)
    csv_file.close()
    print("Sum = " + str(running_sum))
# @exclude

get_col_sum("data.csv", 0)
get_col_sum("datanone.csv", 0)
