import bisect
import collections

# @include
Student = collections.namedtuple('Student', ('name', 'grade_point_average'))


def comp_gpa(student):
    return (-student.grade_point_average, student.name)


def search_student(students, target, comp_gpa):
    i = bisect.bisect_left([comp_gpa(s) for s in students], comp_gpa(target))
    return 0 <= i < len(students) and students[i] == target
# @exclude


def main():
    students = [
        Student('A', 4.0), Student('C', 3.0), Student('B', 2.0), Student('D',
                                                                         3.2)
    ]
    students.sort(key=comp_gpa)
    assert search_student(students, Student('A', 4.0), comp_gpa)
    assert not search_student(students, Student('A', 3.0), comp_gpa)
    assert not search_student(students, Student('B', 3.0), comp_gpa)
    assert search_student(students, Student('D', 3.2), comp_gpa)


if __name__ == '__main__':
    main()
