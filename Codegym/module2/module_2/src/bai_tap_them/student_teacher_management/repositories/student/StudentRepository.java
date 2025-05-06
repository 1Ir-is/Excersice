package bai_tap_them.student_teacher_management.repositories.student;

import bai_tap_them.student_teacher_management.models.Student;
import bai_tap_them.student_teacher_management.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {

    private static final String STUDENT_FILE = "bai_tap_them/student_teacher_management/datas/students.csv";

    @Override
    public void add(Student student) {
        List<String> dataLines = new ArrayList<>();
        dataLines.add(student.toCSV());
        SaveFileUtils.writeToFile(STUDENT_FILE, dataLines, true);
    }

    @Override
    public void saveAll(List<Student> students) {
        List<String> dataLines = new ArrayList<>();
        for (Student student : students) {
            dataLines.add(student.toCSV());
        }
        SaveFileUtils.writeToFile(STUDENT_FILE, dataLines, false);
    }

    @Override
    public ArrayList<Student> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(STUDENT_FILE);
        ArrayList<Student> students = new ArrayList<>();
        for (String line : lines) {
            students.add(Student.fromCSV(line));
        }
        return students;
    }
}
