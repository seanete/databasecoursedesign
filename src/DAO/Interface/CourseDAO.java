package DAO.Interface;

import DAO.Entity.Course;

import java.util.List;

public interface CourseDAO{
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(String courseNo);
    Course getCourse(String courseNo);
    List<Course> findCourses(Course course);
}
