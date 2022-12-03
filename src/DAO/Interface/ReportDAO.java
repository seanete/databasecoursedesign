package DAO.Interface;

import DAO.Entity.Report;
import DAO.SearchCriteria.SearchCriteria;

import java.util.List;

public interface ReportDAO {
    void addReport(Report report);
    void updateReport(Report report);
    void deleteReport(String reportID);
    Report getReport(String reportID);
    List<Report> findReports(Report report);
}
