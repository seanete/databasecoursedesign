package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Report;
import DAO.Entity.DoctoralAchievementReport;
import DAO.Interface.DoctoralAchievementReportDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctoralAchievementReportDAOImpl extends DAObase implements DoctoralAchievementReportDAO {
    private final String DoctoralAchievementReport_INSERT_SQL="insert into DoctoralAchievementReport(studentNo,reportId) values(?,?)";
    private final String DoctoralAchievementReport_DELETE_SQL="delete from DoctoralAchievementReport where studentNo=? and reportId=? ";
    private final String DoctoralAchievementReport_UPDATE_SQL="update DoctoralAchievementReport set studentNo=?,reportId=? where studentNo=? and reportId=?";
    private final String DoctoralAchievementReport_SELECT_SQL="select * from DoctoralAchievementReport where studentNo=? and reportId=?";
    private static final String searchSQL = "select studentNo,mentorNo from DoctoralAchievementReport where ";

    @Override
    public void addDoctoralAchievementReport(DoctoralAchievementReport doctoralAchievementReport) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementReport_INSERT_SQL);
            p.setString(1,doctoralAchievementReport.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementReport.getReport().getReportId());

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
    public void updateDoctoralAchievementReport(DoctoralAchievementReport doctoralAchievementReport) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementReport_UPDATE_SQL);
            p.setString(1,doctoralAchievementReport.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementReport.getReport().getReportId());
            p.setString(3,doctoralAchievementReport.getGraduate().getStudentNo());
            p.setString(4,doctoralAchievementReport.getReport().getReportId());

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
    public void deleteDoctoralAchievementReport(Graduate graduate, Report report) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementReport_DELETE_SQL);
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
    public DoctoralAchievementReport getDoctoralAchievementReport(Graduate graduate, Report report) {
        DoctoralAchievementReport doctoralAchievementReport=new DoctoralAchievementReport();
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementReport_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,report.getReportId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Report report1=new Report();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                doctoralAchievementReport.setGraduate(graduate1);
                report1.setReportId(resultSet.getString("reportId"));
                doctoralAchievementReport.setReport(report1);
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
        return doctoralAchievementReport;
    }

    @Override
    public List<DoctoralAchievementReport> findDoctoralAchievementReports(DoctoralAchievementReport doctoralAchievementReport) {
        List<DoctoralAchievementReport> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(doctoralAchievementReport.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementReport.getGraduate().getStudentNo()) + "%' and ");
        }
        if(doctoralAchievementReport.getReport().getReportId()!= null){
            sql.append("reportId like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementReport.getReport().getReportId()) + "%' and ");
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
                DoctoralAchievementReport doc = new DoctoralAchievementReport();
                Graduate gra1=new Graduate();
                Report rep=new Report();

                gra1.setStudentNo(rs.getString("studentNo"));
                doc.setGraduate(gra1);
                rep.setReportId(rs.getString("reportId"));
                doc.setReport(rep);

                result.add(doc);
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
