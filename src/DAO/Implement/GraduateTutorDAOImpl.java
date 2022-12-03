package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.GraduateTutorDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraduateTutorDAOImpl extends DAObase implements GraduateTutorDAO {

    private final String GraduateTutor_INSERT_SQL="insert into GraduateTutor(studentNo,courseNo,teacherNo,jobReadme,teacherEvaluation,studentSign,studentSignTime,teacherSign,teacherSignTime) values(?,?,?,?,?,?,?,?,?)";
    private final String GraduateTutor_DELETE_SQL="delete from GraduateTutor where studentNo=? and courseNo=?";
    private final String GraduateTutor_UPDATE_SQL="update GraduateTutor set studentNo=?,courseNo=?,teacherNo=?,jobReadme=?,teacherEvaluation=?,studentSign=?,studentSignTime=?,teacherSign=?,teacherSignTime=? where studentNo=? and courseNo=?";
    private final String GraduateTutor_SELECT_SQL="select * from GraduateTutor where studentNo=? and courseNo=?";

    private static final String searchSQL = "select studentNo,courseNo,teacherNo,jobReadme,teacherEvaluation,studentSign,studentSignTime,teacherSign,teacherSignTime from GraduateTutor where ";
    @Override
    public void addGraduateTutor(GraduateTutor graduateTutor) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutor_INSERT_SQL);
            p.setString(1,graduateTutor.getGraduate().getStudentNo());
            p.setString(2,graduateTutor.getCourse().getCourseNo());
            p.setString(3,graduateTutor.getTeacher().getTeacherNo());
            p.setString(4,graduateTutor.getJobReadme());
            p.setString(5,graduateTutor.getTeacherEvaluation());
            p.setString(6,graduateTutor.getStudentSign());
            p.setString(7,graduateTutor.getStudentSignTime());
            p.setString(8,graduateTutor.getTeacherSign());
            p.setString(9,graduateTutor.getTeacherSignTime());
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
    public void updateGraduateTutor(GraduateTutor graduateTutor) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutor_UPDATE_SQL);
            p.setString(1,graduateTutor.getGraduate().getStudentNo());
            p.setString(2,graduateTutor.getCourse().getCourseNo());
            p.setString(3,graduateTutor.getTeacher().getTeacherNo());
            p.setString(4,graduateTutor.getJobReadme());
            p.setString(5,graduateTutor.getTeacherEvaluation());
            p.setString(6,graduateTutor.getStudentSign());
            p.setString(7,graduateTutor.getStudentSignTime());
            p.setString(8,graduateTutor.getTeacherSign());
            p.setString(9,graduateTutor.getTeacherSignTime());
            p.setString(10,graduateTutor.getGraduate().getStudentNo());
            p.setString(11,graduateTutor.getCourse().getCourseNo());
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
    public void deleteGraduateTutor(Graduate graduate,Course course) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutor_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,course.getCourseNo());
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
    public GraduateTutor getGraduateTutor(Graduate graduate,Course course1) {
        GraduateTutor graduateTutor=new GraduateTutor();
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutor_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,course1.getCourseNo());
            ResultSet resultSet=p.executeQuery();
            Course course=new Course();
            Teacher teacher=new Teacher();
            while(resultSet.next()){
                graduateTutor.setGraduate(graduate);
                course.setCourseNo(resultSet.getString("courseNo"));
                graduateTutor.setCourse(course);
                teacher.setTeacherNo(resultSet.getString("teacherNo"));
                graduateTutor.setTeacher(teacher);
                graduateTutor.setJobReadme(resultSet.getString("jobReadme"));
                graduateTutor.setTeacherEvaluation(resultSet.getString("teacherEvaluation"));
                graduateTutor.setStudentSign(resultSet.getString("studentSign"));
                graduateTutor.setStudentSignTime(resultSet.getString("studentSignTime"));
                graduateTutor.setTeacherSign(resultSet.getString("teacherSign"));
                graduateTutor.setTeacherSignTime(resultSet.getString("teacherSignTime"));
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
        return graduateTutor;
    }

    @Override
    public List<GraduateTutor> findGraduateTutors(GraduateTutor graduateTutor) {
        List<GraduateTutor> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(graduateTutor.getGraduate().getStudentNo()!= null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getGraduate().getStudentNo()) + "%' and ");
        }
        if(graduateTutor.getCourse().getCourseNo() != null){
            sql.append("courseNo like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getCourse().getCourseNo()) + "%' and ");
        }
        if(graduateTutor.getTeacher().getTeacherNo()!= null){
            sql.append("teacherNo like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getTeacher().getTeacherNo()) + "%' and ");
        }
        if(graduateTutor.getJobReadme()!= null){
            sql.append("jobReadme like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getJobReadme()) + "%' and ");
        }
        if(graduateTutor.getTeacherEvaluation()!= null){
            sql.append("teacherEvaluation like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getTeacherEvaluation()) + "%' and ");
        }
        if(graduateTutor.getStudentSign()!= null){
            sql.append("studentSign like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getStudentSign()) + "%' and ");
        }
        if(graduateTutor.getStudentSignTime()!= null){
            sql.append("studentSignTime like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getStudentSignTime()) + "%' and ");
        }
        if(graduateTutor.getTeacherSign()!= null){
            sql.append("teacherSign like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getTeacherSign()) + "%' and ");
        }
        if(graduateTutor.getTeacherSignTime()!= null){
            sql.append("teacherSignTime like '%" + DBUtil.fixSqlFieldValue(graduateTutor.getTeacherSignTime()) + "%' and ");
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
                GraduateTutor gra = new GraduateTutor();
                Graduate gra1=new Graduate();
                Course cor1=new Course();
                Teacher tea1=new Teacher();

                gra1.setStudentNo(rs.getString("studentNo"));
                gra.setGraduate(gra1);
                cor1.setCourseNo(rs.getString("courseNo"));
                gra.setCourse(cor1);
                tea1.setTeacherNo(rs.getString("teacherNo"));
                gra.setTeacher(tea1);
                gra.setJobReadme(rs.getString("jobReadme"));
                gra.setTeacherEvaluation(rs.getString("teacherEvaluation"));
                gra.setStudentSign(rs.getString("studentSign"));
                gra.setStudentSignTime(rs.getString("studentSignTime"));
                gra.setTeacherSign(rs.getString("teacherSign"));
                gra.setTeacherSignTime(rs.getString("teacherSignTime"));

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
