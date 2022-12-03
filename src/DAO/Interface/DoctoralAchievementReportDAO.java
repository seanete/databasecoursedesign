package DAO.Interface;

import DAO.Entity.Graduate;
import DAO.Entity.Report;
import DAO.Entity.DoctoralAchievementReport;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface DoctoralAchievementReportDAO {
    void addDoctoralAchievementReport(DoctoralAchievementReport doctoralAchievementReport);
    void updateDoctoralAchievementReport(DoctoralAchievementReport doctoralAchievementReport);
    void deleteDoctoralAchievementReport(Graduate graduate, Report report);
    DoctoralAchievementReport getDoctoralAchievementReport(Graduate graduate, Report report);
    List<DoctoralAchievementReport> findDoctoralAchievementReports(DoctoralAchievementReport doctoralAchievementReport);
}