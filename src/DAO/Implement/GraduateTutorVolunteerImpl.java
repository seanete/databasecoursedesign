package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.GraduateTutorVolunteerDAO;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraduateTutorVolunteerImpl extends DAObase implements GraduateTutorVolunteerDAO {
    private final String GraduateTutorVolunteer_INSERT_SQL="insert into GraduateTutorVolunteer(studentNo,volunteer1CourseNo,volunteer1TeacherNo,volunteer2CourseNo,volunteer2TeacherNo) values(?,?,?,?,?)";
    private final String GraduateTutorVolunteer_DELETE_SQL="delete from GraduateTutorVolunteer where studentNo=?";
    private final String GraduateTutorVolunteer_UPDATE_SQL="update GraduateTutorVolunteer set studentNo=?,volunteer1CourseNo=?,volunteer1TeacherNo=?,volunteer2CourseNo=?,volunteer2TeacherNo=? where studentNo=?";
    private final String GraduateTutorVolunteer_SELECT_SQL="select * from GraduateTutorVolunteer where studentNo=?";
    private static final String searchSQL = "select studentNo,volunteer1CourseNo,volunteer1TeacherNo,volunteer2CourseNo,volunteer2TeacherNo from GraduateTutorVolunteer where ";
    @Override
    public void addGraduateTutorVolunteer(GraduateTutorVolunteer graduateTutorVolunteer) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutorVolunteer_INSERT_SQL);
            p.setString(1,graduateTutorVolunteer.getStudentNo());
            p.setString(2,graduateTutorVolunteer.getVolunteer1Course().getCourseNo());
            p.setString(3,graduateTutorVolunteer.getVolunteer1Teacher().getTeacherNo());
            p.setString(4,graduateTutorVolunteer.getVolunteer2Course().getCourseNo());
            p.setString(5,graduateTutorVolunteer.getVolunteer2Teacher().getTeacherNo());
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
    public void updateGraduateTutorVolunteer(GraduateTutorVolunteer graduateTutorVolunteer) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutorVolunteer_UPDATE_SQL);
            p.setString(1,graduateTutorVolunteer.getStudentNo());
            p.setString(2,graduateTutorVolunteer.getVolunteer1Course().getCourseNo());
            p.setString(3,graduateTutorVolunteer.getVolunteer1Teacher().getTeacherNo());
            p.setString(4,graduateTutorVolunteer.getVolunteer2Course().getCourseNo());
            p.setString(5,graduateTutorVolunteer.getVolunteer2Teacher().getTeacherNo());
            p.setString(6,graduateTutorVolunteer.getStudentNo());
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
    public void deleteGraduateTutorVolunteer(String studentNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutorVolunteer_DELETE_SQL);
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
    public GraduateTutorVolunteer getGraduateTutorVolunteer(String studentNo) {
        GraduateTutorVolunteer graduateTutorVolunteer=new GraduateTutorVolunteer();
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateTutorVolunteer_SELECT_SQL);
            p.setString(1, studentNo);
            ResultSet resultSet=p.executeQuery();
            Course course1=new Course();
            Teacher teacher1=new Teacher();
            Course course2=new Course();
            Teacher teacher2=new Teacher();
            while(resultSet.next()){
                graduateTutorVolunteer.setStudentNo(studentNo);
                course1.setCourseNo(resultSet.getString("volunteer1CourseNo"));
                graduateTutorVolunteer.setVolunteer1Course(course1);
                teacher1.setTeacherNo(resultSet.getString("volunteer1TeacherNo"));
                graduateTutorVolunteer.setVolunteer1Teacher(teacher1);

                course2.setCourseNo(resultSet.getString("volunteer2CourseNo"));
                graduateTutorVolunteer.setVolunteer2Course(course2);
                teacher2.setTeacherNo(resultSet.getString("volunteer2TeacherNo"));
                graduateTutorVolunteer.setVolunteer2Teacher(teacher2);
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
        return graduateTutorVolunteer;
    }

    @Override
    public List<GraduateTutorVolunteer> findGraduateTutorVolunteers(GraduateTutorVolunteer graduateTutorVolunteer) {
        List<GraduateTutorVolunteer> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(graduateTutorVolunteer.getStudentNo()!= null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(graduateTutorVolunteer.getStudentNo()) + "%' and ");
        }
        if(graduateTutorVolunteer.getVolunteer1Course().getCourseNo() != null){
            sql.append("volunteer1CourseNo like '%" + DBUtil.fixSqlFieldValue(graduateTutorVolunteer.getVolunteer1Course().getCourseNo()) + "%' and ");
        }
        if(graduateTutorVolunteer.getVolunteer1Teacher().getTeacherNo()!= null){
            sql.append("volunteer1TeacherNo like '%" + DBUtil.fixSqlFieldValue(graduateTutorVolunteer.getVolunteer1Teacher().getTeacherNo()) + "%' and ");
        }
        if(graduateTutorVolunteer.getVolunteer2Course().getCourseNo()!= null){
            sql.append("volunteer2CourseNo like '%" + DBUtil.fixSqlFieldValue(graduateTutorVolunteer.getVolunteer2Course().getCourseNo()) + "%' and ");
        }
        if(graduateTutorVolunteer.getVolunteer2Teacher().getTeacherNo()!= null){
            sql.append("volunteer2TeacherNo like '%" + DBUtil.fixSqlFieldValue(graduateTutorVolunteer.getVolunteer2Teacher().getTeacherNo()) + "%' and ");
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
                GraduateTutorVolunteer gra = new GraduateTutorVolunteer();
                Graduate gra1=new Graduate();
                Course cor1=new Course();
                Course cor2=new Course();
                Teacher tea1=new Teacher();
                Teacher tea2=new Teacher();

                gra.setStudentNo(rs.getString("studentNo"));
                cor1.setCourseNo(rs.getString("volunteer1CourseNo"));
                gra.setVolunteer1Course(cor1);
                tea1.setTeacherNo(rs.getString("volunteer1TeacherNo"));
                gra.setVolunteer1Teacher(tea1);

                cor2.setCourseNo(rs.getString("volunteer2CourseNo"));
                gra.setVolunteer2Course(cor2);
                tea2.setTeacherNo(rs.getString("volunteer2TeacherNo"));
                gra.setVolunteer2Teacher(tea2);

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
