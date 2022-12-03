package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.GraduateTutorVolunteer;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface GraduateTutorVolunteerDAO {
    void addGraduateTutorVolunteer(GraduateTutorVolunteer graduateTutorVolunteer);
    void updateGraduateTutorVolunteer(GraduateTutorVolunteer graduateTutorVolunteer);
    void deleteGraduateTutorVolunteer(String studentNo);
    GraduateTutorVolunteer getGraduateTutorVolunteer(String studentNo);
    List<GraduateTutorVolunteer> findGraduateTutorVolunteers(GraduateTutorVolunteer graduateTutorVolunteer);
}
