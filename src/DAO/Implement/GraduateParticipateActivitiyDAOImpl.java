package DAO.Implement;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.Entity.Graduate;
import DAO.Entity.GraduateParticipateActivitiy;
import DAO.Entity.Mentor;
import DAO.Interface.AcademicExchangeActivitiyDAO;
import DAO.Interface.GraduateParticipateActivitiyDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraduateParticipateActivitiyDAOImpl extends DAObase implements GraduateParticipateActivitiyDAO {
    private final String GraduateParticipateActivitiy_INSERT_SQL="insert into GraduateParticipateActivitiy(studentNo,activityId,reportNameChineseEnglish,pictureAttendence,remark，mentorSign) values(?,?,?,?,?,?)";
    private final String GraduateParticipateActivitiy_DELETE_SQL="delete from GraduateParticipateActivitiy where studentNo=? and activityId=?";
    private final String GraduateParticipateActivitiy_UPDATE_SQL="update GraduateParticipateActivitiy set studentNo=?,activityId=?,reportNameChineseEnglish=?,pictureAttendence=?,remark=?,mentorSign=? where studentNo=? and activityId=?";
    private final String GraduateParticipateActivitiy_SELECT_SQL="select * from GraduateParticipateActivitiy where studentNo=? and activityId=?";

    private static final String searchSQL = "select studentNo,activityId,reportNameChineseEnglish,pictureAttendence,remark,mentorSign from GraduateParticipateActivitiy where ";
    @Override
    public void addGraduateParticipateActivitiy(GraduateParticipateActivitiy graduateParticipateActivitiy) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateActivitiy_INSERT_SQL);
            p.setString(1,graduateParticipateActivitiy.getGraduate().getStudentNo());
            p.setString(2,graduateParticipateActivitiy.getAcademicExchangeActivitiy().getActivityId());
            p.setString(3,graduateParticipateActivitiy.getReportNameChineseEnglish());
            p.setString(4,graduateParticipateActivitiy.getPictureAttendence());
            p.setString(5,graduateParticipateActivitiy.getRemark());
            p.setString(6,graduateParticipateActivitiy.getMentorSign());
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
    public void updateGraduateParticipateActivitiy(GraduateParticipateActivitiy graduateParticipateActivitiy) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateActivitiy_UPDATE_SQL);
            p.setString(1,graduateParticipateActivitiy.getGraduate().getStudentNo());
            p.setString(2,graduateParticipateActivitiy.getAcademicExchangeActivitiy().getActivityId());
            p.setString(3,graduateParticipateActivitiy.getReportNameChineseEnglish());
            p.setString(4,graduateParticipateActivitiy.getPictureAttendence());
            p.setString(5,graduateParticipateActivitiy.getRemark());
            p.setString(6,graduateParticipateActivitiy.getMentorSign());
            p.setString(7,graduateParticipateActivitiy.getGraduate().getStudentNo());
            p.setString(8,graduateParticipateActivitiy.getAcademicExchangeActivitiy().getActivityId());
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
    public void deleteGraduateParticipateActivitiy(Graduate graduate, AcademicExchangeActivitiy academicExchangeActivitiy) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateActivitiy_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,academicExchangeActivitiy.getActivityId());
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
    public GraduateParticipateActivitiy getGraduateParticipateActivitiy(Graduate graduate, AcademicExchangeActivitiy academicExchangeActivitiy) {
        GraduateParticipateActivitiy graduateParticipateActivitiy=new GraduateParticipateActivitiy();
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateActivitiy_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,academicExchangeActivitiy.getActivityId());
            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            AcademicExchangeActivitiy academicExchangeActivitiy1=new AcademicExchangeActivitiy();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                graduateParticipateActivitiy.setGraduate(graduate1);
                academicExchangeActivitiy1.setActivityId(resultSet.getString("activityId"));
                graduateParticipateActivitiy.setAcademicExchangeActivitiy(academicExchangeActivitiy1);
                graduateParticipateActivitiy.setReportNameChineseEnglish(resultSet.getString("reportNameChineseEnglish"));
                graduateParticipateActivitiy.setPictureAttendence(resultSet.getString("pictureAttendence"));
                graduateParticipateActivitiy.setRemark(resultSet.getString("remark"));
                graduateParticipateActivitiy.setRemark(resultSet.getString("mentorSign"));
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
        return graduateParticipateActivitiy;
    }
    @Override
    public List<GraduateParticipateActivitiy> findGraduateParticipateActivitiys(GraduateParticipateActivitiy graduateParticipateActivitiy) {
        List<GraduateParticipateActivitiy> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(graduateParticipateActivitiy.getGraduate().getStudentNo()!= null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(graduateParticipateActivitiy.getGraduate().getStudentNo()) + "%' and ");
        }
        if(graduateParticipateActivitiy.getAcademicExchangeActivitiy().getActivityId() != null){
            sql.append("activityId like '%" + DBUtil.fixSqlFieldValue(graduateParticipateActivitiy.getAcademicExchangeActivitiy().getActivityId()) + "%' and ");
        }
        if(graduateParticipateActivitiy.getReportNameChineseEnglish()!= null){
            sql.append("reportNameChineseEnglish like '%" + DBUtil.fixSqlFieldValue(graduateParticipateActivitiy.getReportNameChineseEnglish()) + "%' and ");
        }
        if(graduateParticipateActivitiy.getPictureAttendence() != null){
            sql.append("pictureAttendence like '%" + DBUtil.fixSqlFieldValue(graduateParticipateActivitiy.getPictureAttendence()) + "%' and ");
        }
        if(graduateParticipateActivitiy.getRemark()!= null){
            sql.append("remark like '%" + DBUtil.fixSqlFieldValue(graduateParticipateActivitiy.getRemark()) + "%' and ");
        }
        if(graduateParticipateActivitiy.getMentorSign()!= null){
            sql.append("mentorSign like '%" + DBUtil.fixSqlFieldValue(graduateParticipateActivitiy.getMentorSign()) + "%' and ");
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
                GraduateParticipateActivitiy gra = new GraduateParticipateActivitiy();
                Graduate gra1=new Graduate();
                AcademicExchangeActivitiy aca1=new AcademicExchangeActivitiy();

                gra1.setStudentNo(rs.getString("studentNo"));
                graduateParticipateActivitiy.setGraduate(gra1);
                aca1.setActivityId(rs.getString("activityId"));
                graduateParticipateActivitiy.setAcademicExchangeActivitiy(aca1);
                graduateParticipateActivitiy.setReportNameChineseEnglish(rs.getString("reportNameChineseEnglish"));
                graduateParticipateActivitiy.setPictureAttendence(rs.getString("pictureAttendence"));
                graduateParticipateActivitiy.setRemark(rs.getString("remark"));
                graduateParticipateActivitiy.setMentorSign(rs.getString("mentorSign"));

                result.add(gra);
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
