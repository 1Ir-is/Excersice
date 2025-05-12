package bai_tap_them.student_teacher_management.repositories;

import bai_tap_them.student_teacher_management.models.Student;
import bai_tap_them.student_teacher_management.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IPersonRepository<Student> {
    private static final String FILE_PATH = "bai_tap_them/student_teacher_management/datas/persons.csv";

    @Override
    public List<Student> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(FILE_PATH);
        List<Student> students = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("Student,")) {
                students.add(Student.fromCSV(line.substring(8)));
            }
        }
        return students;
    }

    @Override
    public void save(List<Student> data) {
        List<String> result = new ArrayList<>();
        List<String> lines = SaveFileUtils.readFromFile(FILE_PATH);
        for (String line : lines) {
            if (!line.startsWith("Student,")) {
                result.add(line);
            }
        }
        for (Student student : data) {
            result.add("Student," + student.toCSV());
        }
        SaveFileUtils.writeToFile(FILE_PATH, result, false);
    }

    @Override
    public void add(Student student) {
        List<Student> current = findAll();
        current.add(student);
        save(current);
    }

    @Override
    public void delete(String id) {
        List<Student> current = findAll();
        for (int i = 0; i < current.size(); i++) {
            if (current.get(i).getId().equalsIgnoreCase(id)) {
                current.remove(i);
                break;
            }
        }
        save(current);
    }

    @Override
    public List<Student> searchByName(String keyword) {
        List<Student> result = new ArrayList<>();
        List<Student> current = findAll();
        for (Student s : current) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public List<Student> sortByName() {
        List<Student> list = findAll();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getName().compareToIgnoreCase(list.get(j).getName()) > 0) {
                    Student temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        return list;
    }

    @Override
    public void edit(String id, Student updated) {
        List<Student> current = findAll();
        for (int i = 0; i < current.size(); i++) {
            if (current.get(i).getId().equalsIgnoreCase(id)) {
                current.set(i, updated);
                break;
            }
        }
        save(current);
    }
}

