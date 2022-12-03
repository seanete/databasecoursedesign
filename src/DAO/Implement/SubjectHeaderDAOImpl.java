package DAO.Implement;

import DAO.Entity.Subject;
import DAO.Entity.SubjectHeader;
import DAO.Interface.SubjectHeaderDAO;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectHeaderDAOImpl extends DAObase implements SubjectHeaderDAO {
    private final String SubjectHeader_INSERT_SQL="insert into SubjectHeader(headerNo,headerName,subjectNo) values(?,?,?)";
    private final String SubjectHeader_DELETE_SQL="delete from SubjectHeader where headerNo=? ";
    private final String SubjectHeader_UPDATE_SQL="update SubjectHeader set headerNo=?,headerName=?,subjectNo=? where headerNo=?";
    private final String SubjectHeader_SELECT_SQL="select * from SubjectHeader where headerNo=?";
    private static final String searchSQL = "select headerNo,headerName,subjectNo from SubjectHeader where ";
    @Override
    public void addSubjectHeader(SubjectHeader subjectHeader) {
        try{
            PreparedStatement p=getConnection().prepareStatement(SubjectHeader_INSERT_SQL);
            p.setString(1,subjectHeader.getHeaderNo());
            p.setString(2,subjectHeader.getHeaderName());
            p.setString(3,subjectHeader.getSubject().getSubjectNo());
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
    public void updateSubjectHeader(SubjectHeader subjectHeader) {
        try{
            PreparedStatement p=getConnection().prepareStatement(SubjectHeader_UPDATE_SQL);
            p.setString(1,subjectHeader.getHeaderNo());
            p.setString(2,subjectHeader.getHeaderName());
            p.setString(3,subjectHeader.getSubject().getSubjectNo());
            p.setString(4,subjectHeader.getHeaderNo());
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
    public void deleteSubjectHeader(String headerNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(SubjectHeader_DELETE_SQL);
            p.setString(1,headerNo);
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
    public SubjectHeader getSubjectHeader(String headerNo) {
        SubjectHeader subjectHeader=new SubjectHeader();
        try{
            PreparedStatement p=getConnection().prepareStatement(SubjectHeader_SELECT_SQL);
            p.setString(1,headerNo);
            ResultSet resultSet=p.executeQuery();
            Subject subject=new Subject();
            while(resultSet.next()){
                subjectHeader.setHeaderNo(resultSet.getString("headerNo"));
                subjectHeader.setHeaderName(resultSet.getString("headerName"));
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                subjectHeader.setSubject(subject);
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
        return subjectHeader;
    }

    @Override
    public List<SubjectHeader> findMentors(SubjectHeader subjectHeader) {
        List<SubjectHeader> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(subjectHeader.getHeaderNo()!= null){
            sql.append("headerNo like '%" + DBUtil.fixSqlFieldValue(subjectHeader.getHeaderNo()) + "%' and ");
        }
        if(subjectHeader.getHeaderName() != null){
            sql.append("headerName like '%" + DBUtil.fixSqlFieldValue(subjectHeader.getHeaderName()) + "%' and ");
        }
        if(subjectHeader.getSubject().getSubjectNo() != null){
            sql.append("subjectNo like '%" + DBUtil.fixSqlFieldValue(subjectHeader.getSubject().getSubjectNo()) + "%' and ");
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
                SubjectHeader sub = new SubjectHeader();
                Subject sub1=new Subject();
                sub.setHeaderNo(rs.getString("headerNo"));
                sub.setHeaderName(rs.getString("headerName"));
                sub1.setSubjectNo(rs.getString("subjectNo"));
                sub.setSubject(sub1);

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
