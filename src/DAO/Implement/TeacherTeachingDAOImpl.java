package DAO.Implement;

import DAO.Entity.Course;
import DAO.Entity.GraduateTutorVolunteer;
import DAO.Entity.Teacher;
import DAO.Entity.TeacherTeaching;
import DAO.Interface.TeacherTeachingDAO;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherTeachingDAOImpl extends DAObase implements TeacherTeachingDAO {
    private final String TeacherTeaching_INSERT_SQL="insert into TeacherTeaching(teacherNo,courseNo,teachingTarget,teachingNumber,teachingTime,tutorStudentNo) values(?,?,?,?,?,?)";
    private final String TeacherTeaching_DELETE_SQL="delete from TeacherTeaching where teacherNo=? and courseNo=? ";
    private final String TeacherTeaching_UPDATE_SQL="update TeacherTeaching set teacherNo=?,courseNo=?,teachingTarget=?,teachingNumber=?,teachingTime=?,tutorStudentNo=? where teacherNo=? and courseNo=?";
    private final String TeacherTeaching_SELECT_SQL="select * from TeacherTeaching where teacherNo=? and courseNo=?";
    private static final String searchSQL = "select teacherNo,courseNo,teachingTarget,teachingNumber,teachingTime,tutorStudentNo from TeacherTeaching where ";
    @Override
    public void addTeacherTeaching(TeacherTeaching teacherTeaching) {
        try{
            PreparedStatement p=getConnection().prepareStatement(TeacherTeaching_INSERT_SQL);
            p.setString(1,teacherTeaching.getTeacher().getTeacherNo());
            p.setString(2,teacherTeaching.getCourse().getCourseNo());
            p.setString(3,teacherTeaching.getTeachingTarget());
            p.setString(4,teacherTeaching.getTeachingNumber());
            p.setString(5,teacherTeaching.getTeachingTarget());
            p.setString(6,teacherTeaching.getGraduateTutorVolunteer().getStudentNo());
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
    public void updateTeacherTeaching(TeacherTeaching teacherTeaching) {
        try{
            PreparedStatement p=getConnection().prepareStatement(TeacherTeaching_UPDATE_SQL);
            p.setString(1,teacherTeaching.getTeacher().getTeacherNo());
            p.setString(2,teacherTeaching.getCourse().getCourseNo());
            p.setString(3,teacherTeaching.getTeachingTarget());
            p.setString(4,teacherTeaching.getTeachingNumber());
            p.setString(5,teacherTeaching.getTeachingTime());
            p.setString(6,teacherTeaching.getGraduateTutorVolunteer().getStudentNo());
            p.setString(7,teacherTeaching.getTeacher().getTeacherNo());
            p.setString(8,teacherTeaching.getCourse().getCourseNo());
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
    public void deleteTeacherTeaching(Teacher teacher, Course course) {
        try{
            PreparedStatement p=getConnection().prepareStatement(TeacherTeaching_DELETE_SQL);
            p.setString(1,teacher.getTeacherNo());
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
    public TeacherTeaching getTeacherTeaching(Teacher teacher, Course course) {
        TeacherTeaching teacherTeaching=new TeacherTeaching();
        try{
            PreparedStatement p=getConnection().prepareStatement(TeacherTeaching_SELECT_SQL);
            p.setString(1,teacher.getTeacherNo());
            p.setString(2,course.getCourseNo());
            ResultSet resultSet=p.executeQuery();
            Teacher teacher1=new Teacher();
            Course course1=new Course();
            GraduateTutorVolunteer graduateTutorVolunteer=new GraduateTutorVolunteer();
            while(resultSet.next()){
                teacher1.setTeacherNo(teacher.getTeacherNo());
                teacherTeaching.setTeacher(teacher1);
                course1.setCourseNo(course.getCourseNo());
                teacherTeaching.setCourse(course1);
                teacherTeaching.setTeachingTarget(resultSet.getString("teachingTarget"));
                teacherTeaching.setTeachingNumber(resultSet.getString("teachingNumber"));
                teacherTeaching.setTeachingTime(resultSet.getString("teachingTime"));
                graduateTutorVolunteer.setStudentNo(resultSet.getString("tutorStudentNo"));
                teacherTeaching.setGraduateTutorVolunteer(graduateTutorVolunteer);
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
        return teacherTeaching;
    }
    @Override
    public List<TeacherTeaching> findTeacherTeachings(TeacherTeaching teacherTeaching) {
        List<TeacherTeaching> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(teacherTeaching.getTeacher().getTeacherNo()!=null){
            sql.append("teacherNo like '%" + DBUtil.fixSqlFieldValue(teacherTeaching.getTeacher().getTeacherNo()) + "%' and ");
        }
        if(teacherTeaching.getCourse().getCourseNo() != null){
            sql.append("courseNo like '%" + DBUtil.fixSqlFieldValue(teacherTeaching.getCourse().getCourseNo() ) + "%' and ");
        }
        if(teacherTeaching.getTeachingTarget()!=null){
            sql.append("teachingTarget like '%" + DBUtil.fixSqlFieldValue(teacherTeaching.getTeachingTarget()) + "%' and ");
        }
        if(teacherTeaching.getTeachingNumber() != null){
            sql.append("teachingNumber like '%" + DBUtil.fixSqlFieldValue(teacherTeaching.getTeachingNumber() ) + "%' and ");
        }
        if(teacherTeaching.getTeachingTime()!=null){
            sql.append("teachingTime like '%" + DBUtil.fixSqlFieldValue(teacherTeaching.getTeachingTime()) + "%' and ");
        }
        if(teacherTeaching.getGraduateTutorVolunteer().getStudentNo()!= null){
            sql.append("tutorStudentNo like '%" + DBUtil.fixSqlFieldValue(teacherTeaching.getGraduateTutorVolunteer().getStudentNo() ) + "%' and ");
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
                TeacherTeaching tea = new TeacherTeaching();
                Teacher tea1=new Teacher();
                GraduateTutorVolunteer gra1=new GraduateTutorVolunteer();
                Course cor1=new Course();

                tea1.setTeacherNo(rs.getString("teacherNo"));
                tea.setTeacher(tea1);
                cor1.setCourseNo(rs.getString("courseNo"));
                tea.setCourse(cor1);
                tea.setTeachingTarget(rs.getString("teachingTarget"));
                tea.setTeachingNumber(rs.getString("teachingNumber"));
                tea.setTeachingTime(rs.getString("teachingTime"));
                gra1.setStudentNo(rs.getString("tutorStudentNo"));
                tea.setGraduateTutorVolunteer(gra1);

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
