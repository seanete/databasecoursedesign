package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Mentor;
import DAO.Entity.MentorLeadGraduate;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MentorLeadGraduateDAO {
    void addMentorLeadGraduate(MentorLeadGraduate mentorLeadGraduate);
    void updateMentorLeadGraduate(MentorLeadGraduate mentorLeadGraduate);
    void deleteMentorLeadGraduate(Mentor mentor, Graduate graduate);
    MentorLeadGraduate getMentorLeadGraduate(Mentor mentor, Graduate graduate);
    List<MentorLeadGraduate> findMentorLeadGraduates(MentorLeadGraduate mentorLeadGraduate);
}
