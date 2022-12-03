package DAO.Interface;

import DAO.Entity.Course;
import DAO.Entity.Graduate;
import DAO.Entity.GraduateTutor;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface GraduateTutorDAO {
    void addGraduateTutor(GraduateTutor graduateTutor);
    void updateGraduateTutor(GraduateTutor graduateTutor);
    void deleteGraduateTutor(Graduate graduate, Course course);
    GraduateTutor getGraduateTutor(Graduate graduate,Course course);
    List<GraduateTutor> findGraduateTutors(GraduateTutor graduateTutor);
}
