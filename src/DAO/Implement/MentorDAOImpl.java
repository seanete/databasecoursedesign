package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.MentorDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MentorDAOImpl extends DAObase implements MentorDAO {
    private final String Mentor_INSERT_SQL="insert into Mentor(mentorNo,mentorName,subjectNo) values(?,?,?)";
    private final String Mentor_DELETE_SQL="delete from Mentor where mentorNo=? ";
    private final String Mentor_UPDATE_SQL="update Mentor set mentorNo=?,mentorName=?,subjectNo=? where mentorNo=?";
    private final String Mentor_SELECT_SQL="select * from Mentor where mentorNo=?";
    private static final String searchSQL = "select mentorNo,mentorName,subjectNo from Mentor where ";
    @Override
    public void addMentor(Mentor mentor) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Mentor_INSERT_SQL);
            p.setString(1,mentor.getMentorNo());
            p.setString(2,mentor.getMentorName());
            p.setString(3,mentor.getSubject().getSubjectNo());
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
    public void updateMentor(Mentor mentor) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Mentor_UPDATE_SQL);
            p.setString(1,mentor.getMentorNo());
            p.setString(2,mentor.getMentorName());
            p.setString(3,mentor.getSubject().getSubjectNo());
            p.setString(4,mentor.getMentorNo());
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
    public void deleteMentor(String mentorNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Mentor_DELETE_SQL);
            p.setString(1,mentorNo);
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
    public Mentor getMentor(String mentorNo) {
        Mentor mentor=new Mentor();
        try{
            PreparedStatement p=getConnection().prepareStatement(Mentor_SELECT_SQL);
            p.setString(1,mentorNo);
            ResultSet resultSet=p.executeQuery();
            Subject subject=new Subject();
            while(resultSet.next()){
                mentor.setMentorNo(resultSet.getString("mentorNo"));
                mentor.setMentorName(resultSet.getString("mentorName"));
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                mentor.setSubject(subject);
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
        return mentor;
    }
    @Override
    public List<Mentor> findMentors(Mentor mentor) {
        List<Mentor> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(mentor.getMentorNo()!= null){
            sql.append("mentorNo like '%" + DBUtil.fixSqlFieldValue(mentor.getMentorNo()) + "%' and ");
        }
        if(mentor.getMentorName() != null){
            sql.append("mentorName like '%" + DBUtil.fixSqlFieldValue(mentor.getMentorName()) + "%' and ");
        }
        if(mentor.getSubject().getSubjectNo() != null){
            sql.append("subjectNo like '%" + DBUtil.fixSqlFieldValue(mentor.getSubject().getSubjectNo()) + "%' and ");
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
                Mentor men = new Mentor();
                Subject sub=new Subject();

                men.setMentorNo(rs.getString("mentorNo"));
                men.setMentorName(rs.getString("mentorName"));
                sub.setSubjectNo(rs.getString("subjectNo"));
                men.setSubject(sub);

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
