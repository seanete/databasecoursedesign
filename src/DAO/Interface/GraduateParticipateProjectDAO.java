package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.GraduateParticipateProject;
import DAO.Entity.Project;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface GraduateParticipateProjectDAO {
    void addGraduateParticipateProject(GraduateParticipateProject graduateParticipateProject);
    void updateGraduateParticipateProject(GraduateParticipateProject graduateParticipateProject);
    void deleteGraduateParticipateProject(Graduate graduate, Project project);
    GraduateParticipateProject getGraduateParticipateProject(Graduate graduate, Project project);
    List<GraduateParticipateProject> findGraduateParticipateProjects(GraduateParticipateProject graduateParticipateProject);
}
