package DAO.Interface;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface AcademicExchangeActivitiyDAO {
    void addAcademicExchangeActivitiy(AcademicExchangeActivitiy academicExchangeActivitiy);
    void updateAcademicExchangeActivitiy(AcademicExchangeActivitiy academicExchangeActivitiy);
    void deleteAcademicExchangeActivitiy(String activityId);
    AcademicExchangeActivitiy getAcademicExchangeActivitiy(String activityId);
    List<AcademicExchangeActivitiy> findAcademicExchangeActivitiys(AcademicExchangeActivitiy academicExchangeActivitiy);
}
