package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Mentor;
import DAO.Entity.MentorLeadGraduate;
import DAO.Interface.MentorLeadGraduateDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MentorLeadGraduateDAOImpl extends DAObase implements MentorLeadGraduateDAO {
    private final String MentorLeadGraduate_INSERT_SQL="insert into MentorLeadGraduate(mentorNo,studentNo) values(?,?)";
    private final String MentorLeadGraduate_DELETE_SQL="delete from MentorLeadGraduate where mentorNo=? and studentNo=? ";
    private final String MentorLeadGraduate_UPDATE_SQL="update MentorLeadGraduate set mentorNo=?,studentNo=? where mentorNo=? and studentNo=?";
    private final String MentorLeadGraduate_SELECT_SQL="select * from MentorLeadGraduate where mentorNo=? and studentNo=?";
    private static final String searchSQL = "select mentorNo,studentNo from MentorLeadGraduate where ";
    @Override
    public void addMentorLeadGraduate(MentorLeadGraduate mentorLeadGraduate) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MentorLeadGraduate_INSERT_SQL);
            p.setString(1,mentorLeadGraduate.getMentor().getMentorNo());
            p.setString(2,mentorLeadGraduate.getGraduate().getStudentNo());
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
    public void updateMentorLeadGraduate(MentorLeadGraduate mentorLeadGraduate) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MentorLeadGraduate_UPDATE_SQL);
            p.setString(1,mentorLeadGraduate.getMentor().getMentorNo());
            p.setString(2,mentorLeadGraduate.getGraduate().getStudentNo());
            p.setString(3,mentorLeadGraduate.getMentor().getMentorNo());
            p.setString(4,mentorLeadGraduate.getGraduate().getStudentNo());
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
    public void deleteMentorLeadGraduate(Mentor mentor, Graduate graduate) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MentorLeadGraduate_DELETE_SQL);
            p.setString(1,mentor.getMentorNo());
            p.setString(2,graduate.getStudentNo());
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
    public MentorLeadGraduate getMentorLeadGraduate(Mentor mentor, Graduate graduate) {
        MentorLeadGraduate mentorLeadGraduate=new MentorLeadGraduate();
        try{
            PreparedStatement p=getConnection().prepareStatement(MentorLeadGraduate_SELECT_SQL);
            p.setString(1,mentor.getMentorNo());
            p.setString(2,graduate.getStudentNo());
            ResultSet resultSet=p.executeQuery();
            Mentor mentor1=new Mentor();
            Graduate graduate1=new Graduate();
            while(resultSet.next()){
                mentor1.setMentorNo(resultSet.getString("mentorNo"));
                mentorLeadGraduate.setMentor(mentor1);
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                mentorLeadGraduate.setGraduate(graduate1);
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
        return mentorLeadGraduate;
    }

    @Override
    public List<MentorLeadGraduate> findMentorLeadGraduates(MentorLeadGraduate mentorLeadGraduate) {
        List<MentorLeadGraduate> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(mentorLeadGraduate.getMentor().getMentorNo()!= null){
            sql.append("mentorNo like '%" + DBUtil.fixSqlFieldValue(mentorLeadGraduate.getMentor().getMentorNo()) + "%' and ");
        }
        if(mentorLeadGraduate.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(mentorLeadGraduate.getGraduate().getStudentNo()) + "%' and ");
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
                MentorLeadGraduate men = new MentorLeadGraduate();
                Mentor men1=new Mentor();
                Graduate gra1=new Graduate();

                men1.setMentorNo(rs.getString("mentorNo"));
                men.setMentor(men1);
                gra1.setStudentNo(rs.getString("studentNo"));
                men.setGraduate(gra1);

                result.add(men);
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
