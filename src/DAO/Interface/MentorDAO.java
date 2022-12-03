package DAO.Interface;

import DAO.Entity.Mentor;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MentorDAO {
    void addMentor(Mentor mentor);
    void updateMentor(Mentor mentor);
    void deleteMentor(String mentorNo);
    Mentor getMentor(String mentorNo);
    List<Mentor> findMentors(Mentor mentor);
}
