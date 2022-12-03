package DAO.Interface;

import DAO.Entity.Teacher;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface TeacherDAO {
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(String teacherNo);
    Teacher getTeacher(String teacherNo);
    List<Teacher> findTeachers(Teacher teacher);
}
