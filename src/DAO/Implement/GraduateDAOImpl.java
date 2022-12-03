package DAO.Implement;

import DAO.Entity.Course;
import DAO.Entity.Graduate;
import DAO.Entity.Mentor;
import DAO.Entity.Subject;
import DAO.Interface.GraduateDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraduateDAOImpl extends DAObase implements GraduateDAO {

    private final String Graduate_INSERT_SQL="insert into Graduate(studentNo,studentName,subjectNo,type,mentorNo) values(?,?,?,?,?)";
    private final String Graduate_DELETE_SQL="delete from Graduate where studentNo=?";
    private final String Graduate_UPDATE_SQL="update Graduate set studentNo=?,studentName=?,subjectNo=?,type=?,mentorNo=? where studentNo=?";
    private final String Graduate_SELECT_SQL="select * from Graduate where studentNo=?";

    private static final String searchSQL = "select studentNo,studentName,subjectNo,type,mentorNo from Graduate where ";

    @Override
    public void addGraduate(Graduate graduate) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Graduate_INSERT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,graduate.getStudentName());
            p.setString(3,graduate.getSubject().getSubjectNo());
            p.setString(4,graduate.getType());
            p.setString(5,graduate.getMentor().getMentorNo());

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
    public void updateGraduate(Graduate graduate) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Graduate_UPDATE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,graduate.getStudentName());
            p.setString(3,graduate.getSubject().getSubjectNo());
            p.setString(4,graduate.getType());
            p.setString(5,graduate.getMentor().getMentorNo());
            p.setString(6,graduate.getStudentNo());
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
    public void deleteGraduate(String studentNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Graduate_DELETE_SQL);
            p.setString(1,studentNo);
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
    public Graduate getGraduate(String studentNo) {
        Graduate graduate=new Graduate();
        try{
            PreparedStatement p=getConnection().prepareStatement(Graduate_SELECT_SQL);
            p.setString(1,studentNo);
            ResultSet resultSet=p.executeQuery();
            Mentor mentor=new Mentor();
            Subject subject=new Subject();
            while(resultSet.next()){
                graduate.setStudentNo(resultSet.getString("studentNo"));
                graduate.setStudentName(resultSet.getString("studentName"));
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                graduate.setSubject(subject);
                graduate.setType(resultSet.getString("type"));
                mentor.setMentorNo(resultSet.getString("mentorNo"));
                graduate.setMentor(mentor);
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
        return graduate;
    }

    @Override
    public List<Graduate> findGraduates(Graduate graduate) {
        List<Graduate> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(graduate.getStudentNo()!= null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(graduate.getStudentNo()) + "%' and ");
        }
        if(graduate.getStudentName() != null){
            sql.append("studentName like '%" + DBUtil.fixSqlFieldValue(graduate.getStudentName()) + "%' and ");
        }
        if(graduate.getSubject().getSubjectNo() != null){
            sql.append("subjectNo like '%" + DBUtil.fixSqlFieldValue(graduate.getSubject().getSubjectNo()) + "%' and ");
        }
        if(graduate.getType() != null){
            sql.append("type like '%" + DBUtil.fixSqlFieldValue(graduate.getType()) + "%' and ");
        }
        if(graduate.getMentor().getMentorNo() != null){
            sql.append("mentorNo like '%" + DBUtil.fixSqlFieldValue(graduate.getMentor().getMentorNo()) + "%' and ");
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
                Graduate gra = new Graduate();
                Mentor men=new Mentor();
                Subject sub=new Subject();
                gra.setStudentNo(rs.getString("studentNo"));
                gra.setStudentName(rs.getString("studentName"));
                sub.setSubjectNo(rs.getString("subjectNo"));
                gra.setSubject(sub);
                gra.setType(rs.getString("type"));
                men.setMentorNo(rs.getString("mentorNo"));
                gra.setMentor(men);
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
