package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.ReportDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImpl extends DAObase implements ReportDAO {
    private final String Report_INSERT_SQL="insert into Report(reportId,reportName,reportType,serviceUnit,reportTime,supportMaterial,contributionRank) values(?,?,?,?,?,?,?)";
    private final String Report_DELETE_SQL="delete from Report where reportId=?";
    private final String Report_UPDATE_SQL="update Report set reportId=?,reportName=?,reportType=?,serviceUnit=?,reportTime=?,supportMaterial=?,contributionRank=? where reportId=?";
    private final String Report_SELECT_SQL="select * from Report where reportId=?";
    private static final String searchSQL = "select reportId,reportName,reportType,serviceUnit,reportTime,supportMaterial,contributionRank from Report where ";

    @Override
    public void addReport(Report report) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Report_INSERT_SQL);
            p.setString(1,report.getReportId());
            p.setString(2,report.getReportName());
            p.setString(3,report.getReportType());
            p.setString(4,report.getServiceUnit());
            p.setString(5,report.getReportTime());
            p.setString(6,report.getSupportMaterial());
            p.setString(6,report.getContributionRank());

            p.executeUpdate();
            p.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                getConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateReport(Report report) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Report_UPDATE_SQL);
            p.setString(1,report.getReportId());
            p.setString(2,report.getReportName());
            p.setString(3,report.getReportType());
            p.setString(4,report.getServiceUnit());
            p.setString(5,report.getReportTime());
            p.setString(6,report.getSupportMaterial());
            p.setString(7,report.getContributionRank());
            p.setString(8,report.getReportId());

            p.executeUpdate();
            p.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                getConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteReport(String reportId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Report_DELETE_SQL);
            p.setString(1,reportId);

            p.executeUpdate();
            p.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                getConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Report getReport(String reportId) {
        Report report=new Report();
        try{
            PreparedStatement p=getConnection().prepareStatement(Report_SELECT_SQL);
            p.setString(1,reportId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                report.setReportId(resultSet.getString("reportId"));
                report.setReportName(resultSet.getString("reportName"));
                report.setReportType(resultSet.getString("reportType"));
                report.setServiceUnit(resultSet.getString("serviceUnit"));
                report.setReportTime(resultSet.getString("reportTime"));
                report.setSupportMaterial(resultSet.getString("supportMaterial"));
                report.setContributionRank(resultSet.getString("contributionRank"));
            }
            p.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                getConnection().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return report;
    }

    @Override
    public List<Report> findReports(Report report) {
        List<Report> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(report.getReportId()!= null){
            sql.append("reportId like '%" + DBUtil.fixSqlFieldValue(report.getReportId()) + "%' and ");
        }
        if(report.getReportName()!= null){
            sql.append("reportName like '%" + DBUtil.fixSqlFieldValue(report.getReportName()) + "%' and ");
        }
        if(report.getReportType() != null){
            sql.append("reportType like '%" + DBUtil.fixSqlFieldValue(report.getReportType()) + "%' and ");
        }
        if(report.getServiceUnit()!= null){
            sql.append("serviceUnit like '%" + DBUtil.fixSqlFieldValue(report.getServiceUnit()) + "%' and ");
        }
        if(report.getReportTime()!= null){
            sql.append("reportTime like '%" + DBUtil.fixSqlFieldValue(report.getReportTime()) + "%' and ");
        }
        if(report.getSupportMaterial()!= null){
            sql.append("supportMaterial like '%" + DBUtil.fixSqlFieldValue(report.getSupportMaterial()) + "%' and ");
        }
        if(report.getContributionRank()!= null){
            sql.append("contributionRank like '%" + DBUtil.fixSqlFieldValue(report.getContributionRank()) + "%' and ");
        }

        if(sql.substring(sql.length()-5).equals(" and ")){
            sql.delete(sql.length()-5,sql.length()-1);
        }
        if(sql.substring(sql.length()-7).equals(" where ")){
            sql.delete(sql.length()-7,sql.length()-1);
        }
        Connection conn = getConnection();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql.toString());
            while(rs.next()){
                Report rep = new Report();
                rep.setReportId(rs.getString("reportId"));
                rep.setReportName(rs.getString("reportName"));
                rep.setReportType(rs.getString("reportType"));
                rep.setServiceUnit(rs.getString("serviceUnit"));
                rep.setReportTime(rs.getString("reportTime"));
                rep.setSupportMaterial(rs.getString("supportMaterial"));
                rep.setContributionRank(rs.getString("contributionRank"));

                result.add(rep);
            }
            rs.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
