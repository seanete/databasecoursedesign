package DAO.Implement;

import DAO.Entity.Mentor;
import DAO.Entity.Subject;
import DAO.Interface.SubjectDAO;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl extends DAObase implements SubjectDAO {
    private final String Subject_INSERT_SQL="insert into Subject(subjectNo,subjectName) values(?,?)";
    private final String Subject_DELETE_SQL="delete from Subject where subjectNo=? ";
    private final String Subject_UPDATE_SQL="update Subject set subjectNo=?,subjectName=? where subjectNo=?";
    private final String Subject_SELECT_SQL="select * from Subject where subjectNo=?";
    private static final String searchSQL = "select subjectNo,subjectName from Subject where ";
    @Override
    public void addSubject(Subject subject) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Subject_INSERT_SQL);
            p.setString(1,subject.getSubjectNo());
            p.setString(2,subject.getSubjectName());
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
    public void updateSubject(Subject subject) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Subject_UPDATE_SQL);
            p.setString(1,subject.getSubjectNo());
            p.setString(2,subject.getSubjectName());
            p.setString(3,subject.getSubjectNo());
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
    public void deleteSubject(String subjectNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Subject_DELETE_SQL);
            p.setString(1,subjectNo);
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
    public Subject getSubject(String subjectNo) {
        Subject subject=new Subject();
        try{
            PreparedStatement p=getConnection().prepareStatement(Subject_SELECT_SQL);
            p.setString(1,subjectNo);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                subject.setSubjectName(resultSet.getString("subjectName"));
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
        return subject;
    }

    @Override
    public List<Subject> findMentors(Subject subject) {
        List<Subject> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(subject.getSubjectNo()!= null){
            sql.append("subjectNo like '%" + DBUtil.fixSqlFieldValue(subject.getSubjectNo()) + "%' and ");
        }
        if(subject.getSubjectName() != null){
            sql.append("subjectName like '%" + DBUtil.fixSqlFieldValue(subject.getSubjectName()) + "%' and ");
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
                Subject sub = new Subject();
                sub.setSubjectNo(rs.getString("subjectNo"));
                sub.setSubjectName(rs.getString("subjectName"));

                result.add(sub);
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
