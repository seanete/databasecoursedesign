package DAO.Interface;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.Entity.Graduate;
import DAO.Entity.GraduateParticipateActivitiy;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface GraduateParticipateActivitiyDAO {
    void addGraduateParticipateActivitiy(GraduateParticipateActivitiy graduateParticipateActivitiy);
    void updateGraduateParticipateActivitiy(GraduateParticipateActivitiy graduateParticipateActivitiy);
    void deleteGraduateParticipateActivitiy(Graduate graduate, AcademicExchangeActivitiy academicExchangeActivitiy);
    GraduateParticipateActivitiy getGraduateParticipateActivitiy(Graduate graduate, AcademicExchangeActivitiy academicExchangeActivitiy);
    List<GraduateParticipateActivitiy> findGraduateParticipateActivitiys(GraduateParticipateActivitiy graduateParticipateActivitiy);
}
