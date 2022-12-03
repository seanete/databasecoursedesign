package DAO.Interface;

import DAO.Entity.Subject;

import java.util.List;

public interface SubjectDAO {
    void addSubject(Subject subject);
    void updateSubject(Subject subject);
    void deleteSubject(String subjectNo);
    Subject getSubject(String subjectNo);
    List<Subject> findMentors(Subject subject);
}
