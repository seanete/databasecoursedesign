package DAO.Interface;

import DAO.Entity.Course;
import DAO.Entity.Graduate;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface GraduateDAO {
    void addGraduate(Graduate graduate);
    void updateGraduate(Graduate graduate);
    void deleteGraduate(String studentNo);
    Graduate getGraduate(String studentNo);
    List<Graduate> findGraduates(Graduate graduate);
}
