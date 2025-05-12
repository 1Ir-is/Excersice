package bai_tap_them.student_teacher_management.repositories;

import bai_tap_them.student_teacher_management.models.Teacher;
import bai_tap_them.student_teacher_management.utils.SaveFileUtils;


import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements IPersonRepository<Teacher> {
    private static final String FILE_PATH = "bai_tap_them/student_teacher_management/datas/persons.csv";

    @Override
    public List<Teacher> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(FILE_PATH);
        List<Teacher> teachers = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("Teacher,")) {
                teachers.add(Teacher.fromCSV(line.substring(8)));
            }
        }
        return teachers;
    }

    @Override
    public void save(List<Teacher> data) {
        List<String> result = new ArrayList<>();
        List<String> lines = SaveFileUtils.readFromFile(FILE_PATH);
        for (String line : lines) {
            if (!line.startsWith("Teacher,")) {
                result.add(line);
            }
        }
        for (Teacher teacher : data) {
            result.add("Teacher," + teacher.toCSV());
        }
        SaveFileUtils.writeToFile(FILE_PATH, result, false);
    }

    @Override
    public void add(Teacher teacher) {
        List<Teacher> current = findAll();
        current.add(teacher);
        save(current);
    }

    @Override
    public void delete(String id) {
        List<Teacher> current = findAll();
        for (int i = 0; i < current.size(); i++) {
            if (current.get(i).getId().equalsIgnoreCase(id)) {
                current.remove(i);
                break;
            }
        }
        save(current);
    }

    @Override
    public List<Teacher> searchByName(String keyword) {
        List<Teacher> result = new ArrayList<>();
        List<Teacher> current = findAll();
        for (Teacher t : current) {
            if (t.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public List<Teacher> sortByName() {
        List<Teacher> list = findAll();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getName().compareToIgnoreCase(list.get(j).getName()) > 0) {
                    Teacher temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        return list;
    }

    @Override
    public void edit(String id, Teacher updated) {
        List<Teacher> current = findAll();
        for (int i = 0; i < current.size(); i++) {
            if (current.get(i).getId().equalsIgnoreCase(id)) {
                current.set(i, updated);
                break;
            }
        }
        save(current);
    }
}

