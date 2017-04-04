# @include
class Student(object):
    def __init__(self, name, grade_point_average):
        self.name = name
        self.grade_point_average = grade_point_average

    def __lt__(self, other):
        return self.name < other.name


students = [
    Student('A', 4.0), Student('C', 3.0), Student('B', 2.0), Student('D', 3.2)
]

# Sort according to __lt__ defined in Student. students remained unchanged.
students_sort_by_name = sorted(students)
# @exclude
assert all(a.name <= b.name
           for a, b in zip(students_sort_by_name, students_sort_by_name[1:]))
# @include

# Sort students in-place by grade_point_average.
students.sort(key=lambda student: student.grade_point_average)
# @exclude
assert all(a.grade_point_average <= b.grade_point_average
           for a, b in zip(students, students[1:]))
