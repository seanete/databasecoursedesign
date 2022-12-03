package DAO.Implement;

import DAO.Entity.Mentor;
import DAO.Entity.Project;
import DAO.Entity.Subject;
import DAO.Entity.Teacher;
import DAO.Interface.TeacherDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl extends DAObase implements TeacherDAO{
    private final String Teacher_INSERT_SQL="insert into Teacher(teacherNo,teacherName,subjectNo) values(?,?,?)";
    private final String Teacher_DELETE_SQL="delete from Teacher where teacherNo=?";
    private final String Teacher_UPDATE_SQL="update Teacher set teacherName=? ,subjectNo=? where teacherNo=?";
    private final String Teacher_SELECT_SQL="select * from Teacher where teacherNo=?";
    private static final String searchSQL = "select teacherNo,teacherName,subjectNo from Teacher where ";
    @Override
    public void addTeacher(Teacher teacher) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Teacher_INSERT_SQL);
            p.setString(1,teacher.getTeacherNo());
            p.setString(2,teacher.getTeacherName());
            p.setString(2,teacher.getSubject().getSubjectNo());
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
    public void updateTeacher(Teacher teacher) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Teacher_UPDATE_SQL);
            p.setString(1,teacher.getTeacherName());
            p.setString(2,teacher.getSubject().getSubjectNo());
            p.setString(3,teacher.getTeacherNo());
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
    public void deleteTeacher(String teacherNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Teacher_DELETE_SQL);
            p.setString(1,teacherNo);
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
    public Teacher getTeacher(String teacherNo) {
        Teacher teacher=new Teacher();
        try{
            PreparedStatement p=getConnection().prepareStatement(Teacher_SELECT_SQL);
            p.setString(1,teacherNo);
            ResultSet resultSet=p.executeQuery();
            Mentor mentor=new Mentor();
            Subject subject=new Subject();
            while(resultSet.next()){
                teacher.setTeacherNo(resultSet.getString("teacherNo"));
                teacher.setTeacherName(resultSet.getString("teacherName"));
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                teacher.setSubject(subject);
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
        return teacher;
    }

    @Override
    public List<Teacher> findTeachers(Teacher teacher) {
        List<Teacher> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(teacher.getTeacherNo()!=null){
            sql.append("teacherNo like '%" + DBUtil.fixSqlFieldValue(teacher.getTeacherNo()) + "%' and ");
        }
        if(teacher.getTeacherName() != null){
            sql.append("teacherName like '%" + DBUtil.fixSqlFieldValue(teacher.getTeacherName()) + "%' and ");
        }
        if(teacher.getSubject().getSubjectNo() != null){
            sql.append("subjectNo like '%" + DBUtil.fixSqlFieldValue(teacher.getSubject().getSubjectNo()) + "%' and ");
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
                Teacher tea = new Teacher();
                Subject sub =new Subject();

                tea.setTeacherNo(rs.getString("teacherNo"));
                tea.setTeacherName(rs.getString("teacherName"));
                sub.setSubjectNo(rs.getString("subjectNo"));
                tea.setSubject(sub);

                result.add(tea);
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