package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Report;
import DAO.Entity.MasterAchievementReport;
import DAO.Interface.MasterAchievementReportDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterAchievementReportDAOImpl extends DAObase implements MasterAchievementReportDAO {
    private final String MasterAchievementReport_INSERT_SQL="insert into MasterAchievementReport(studentNo,reportId) values(?,?)";
    private final String MasterAchievementReport_DELETE_SQL="delete from MasterAchievementReport where studentNo=? and reportId=? ";
    private final String MasterAchievementReport_UPDATE_SQL="update MasterAchievementReport set studentNo=?,reportId=? where studentNo=? and reportId=?";
    private final String MasterAchievementReport_SELECT_SQL="select * from MasterAchievementReport where studentNo=? and reportId=?";
    private static final String searchSQL = "select studentNo,reportId from MasterAchievementReport where ";

    @Override
    public void addMasterAchievementReport(MasterAchievementReport masterAchievementReport) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementReport_INSERT_SQL);
            p.setString(1,masterAchievementReport.getGraduate().getStudentNo());
            p.setString(2,masterAchievementReport.getReport().getReportId());

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
    public void updateMasterAchievementReport(MasterAchievementReport masterAchievementReport) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementReport_UPDATE_SQL);
            p.setString(1,masterAchievementReport.getGraduate().getStudentNo());
            p.setString(2,masterAchievementReport.getReport().getReportId());
            p.setString(3,masterAchievementReport.getGraduate().getStudentNo());
            p.setString(4,masterAchievementReport.getReport().getReportId());

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
    public void deleteMasterAchievementReport(Graduate graduate, Report report) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementReport_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,report.getReportId());

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
    public MasterAchievementReport getMasterAchievementReport(Graduate graduate, Report report) {
        MasterAchievementReport masterAchievementReport=new MasterAchievementReport();
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementReport_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,report.getReportId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Report report1=new Report();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                masterAchievementReport.setGraduate(graduate1);
                report1.setReportId(resultSet.getString("reportId"));
                masterAchievementReport.setReport(report1);
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
        return masterAchievementReport;
    }

    @Override
    public List<MasterAchievementReport> findMasterAchievementReports(MasterAchievementReport masterAchievementReport) {
        List<MasterAchievementReport> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(masterAchievementReport.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(masterAchievementReport.getGraduate().getStudentNo()) + "%' and ");
        }
        if(masterAchievementReport.getReport().getReportId()!= null){
            sql.append("reportId like '%" + DBUtil.fixSqlFieldValue(masterAchievementReport.getReport().getReportId()) + "%' and ");
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
                MasterAchievementReport mas = new MasterAchievementReport();
                Graduate gra1=new Graduate();
                Report rep=new Report();

                gra1.setStudentNo(rs.getString("studentNo"));
                mas.setGraduate(gra1);
                rep.setReportId(rs.getString("reportId"));
                mas.setReport(rep);

                result.add(mas);
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
