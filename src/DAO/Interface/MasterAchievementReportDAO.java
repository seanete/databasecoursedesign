package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Report;
import DAO.Entity.MasterAchievementReport;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface MasterAchievementReportDAO {
    void addMasterAchievementReport(MasterAchievementReport masterAchievementReport);
    void updateMasterAchievementReport(MasterAchievementReport masterAchievementReport);
    void deleteMasterAchievementReport(Graduate graduate, Report report);
    MasterAchievementReport getMasterAchievementReport(Graduate graduate, Report report);
    List<MasterAchievementReport> findMasterAchievementReports(MasterAchievementReport masterAchievementReport);
}