package DAO.Interface;

import DAO.Entity.SubjectHeader;

import java.util.List;

public interface SubjectHeaderDAO {
    void addSubjectHeader(SubjectHeader subjectHeader);
    void updateSubjectHeader(SubjectHeader subjectHeader);
    void deleteSubjectHeader(String leaderNo);
    SubjectHeader getSubjectHeader(String leaderNo);
    List<SubjectHeader> findMentors(SubjectHeader subjectHeader);
}
