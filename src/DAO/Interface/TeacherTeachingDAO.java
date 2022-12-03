package DAO.Interface;

import DAO.Entity.Course;
import DAO.Entity.Teacher;
import DAO.Entity.TeacherTeaching;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface TeacherTeachingDAO {
    void addTeacherTeaching(TeacherTeaching teacherTeaching);
    void updateTeacherTeaching(TeacherTeaching teacherTeaching);
    void deleteTeacherTeaching(Teacher teacher, Course course);
    TeacherTeaching getTeacherTeaching(Teacher teacher, Course course);
    List<TeacherTeaching> findTeacherTeachings(TeacherTeaching teacherTeaching);
}
