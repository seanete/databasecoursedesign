package DAO.Interface;


import DAO.Entity.Project;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface ProjectDAO {
    void addProject(Project project);
    void updateProject(Project project);
    void deleteProject(String projectIndex);
    Project getProject(String projectIndex);
    List<Project> findProjects(Project project);
}
