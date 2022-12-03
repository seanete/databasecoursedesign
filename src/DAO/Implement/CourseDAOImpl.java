package DAO.Implement;

import DAO.Entity.Subject;
import DAO.Interface.CourseDAO;
import DAO.Entity.Course;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl extends DAObase implements CourseDAO {
    private final String COURSE_INSERT_SQL="insert into Course(courseNo,courseName,properties,subjectNo,priority,credit) values(?,?,?,?,?,?)";
    private final String COURSE_DELETE_SQL="delete from Course where courseNo=?";
    private final String COURSE_UPDATE_SQL="update Course set courseNo=?,courseName=?,properties=?,subjectNo=?,priority=?,credit=? where courseNo=?";
    private final String COURSE_SELECT_SQL="select * from Course where courseNo=?";

    private static final String searchSQL = "select courseNo,courseName,properties,subjectNo,priority,credit from Course where ";

    public void addCourse(Course course) {
        try{
            PreparedStatement p=getConnection().prepareStatement(COURSE_INSERT_SQL);
            p.setString(1,course.getCourseNo());
            p.setString(2,course.getCourseName());
            p.setString(3,course.getProperties());
            p.setString(4,course.getSubject().getSubjectNo());
            p.setString(5,course.getPriority());
            p.setString(6,course.getCredit());
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
    public void updateCourse(Course course) {
        try{
            PreparedStatement p=getConnection().prepareStatement(COURSE_UPDATE_SQL);
            p.setString(1,course.getCourseNo());
            p.setString(2,course.getCourseName());
            p.setString(3,course.getProperties());
            p.setString(4,course.getSubject().getSubjectNo());
            p.setString(5,course.getPriority());
            p.setString(6,course.getCredit());
            p.setString(7,course.getCourseNo());
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
    public void deleteCourse(String courseNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(COURSE_DELETE_SQL);
            p.setString(1,courseNo);
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
    public Course getCourse(String courseNo) {
        Course course=new Course();
        try{
            PreparedStatement p=getConnection().prepareStatement(COURSE_SELECT_SQL);
            p.setString(1,courseNo);
            ResultSet resultSet=p.executeQuery();
            Subject subject=new Subject();
            while(resultSet.next()){
                course.setCourseNo(resultSet.getString("courseNo"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setProperties(resultSet.getString("properties"));
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                course.setSubject(subject);
                course.setPriority(resultSet.getString("priority"));
                course.setCredit(resultSet.getString("credit"));
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
        return course;
    }
    @Override
    public List<Course> findCourses(Course course) {
        List<Course> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(course.getCourseNo() != null){
            sql.append("courseNo like '%" + DBUtil.fixSqlFieldValue(course.getCourseNo()) + "%' and ");
        }
        if(course.getCourseName() != null){
            sql.append("courseName like '%" + DBUtil.fixSqlFieldValue(course.getCourseName()) + "%' and ");
        }
        if(course.getProperties() != null){
            sql.append("properties like '%" + DBUtil.fixSqlFieldValue(course.getProperties()) + "%' and ");
        }
        if(course.getSubject().getSubjectNo() != null){
            sql.append("subjectNo like '%" + DBUtil.fixSqlFieldValue(course.getSubject().getSubjectNo()) + "%' and ");
        }
        if(course.getPriority() != null){
            sql.append("priority like '%" + DBUtil.fixSqlFieldValue(course.getPriority()) + "%' and ");
        }
        if(course.getCredit() != null){
            sql.append("credit like '%" + DBUtil.fixSqlFieldValue(course.getCredit()) + "%' and ");
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
                Course cou = new Course();
                Subject sub=new Subject();
                cou.setCourseNo(rs.getString("courseNo"));
                cou.setCourseName(rs.getString("courseName"));
                cou.setProperties(rs.getString("properties"));
                sub.setSubjectNo(rs.getString("subjectNo"));
                cou.setSubject(sub);
                cou.setPriority(rs.getString("priority"));
                cou.setCredit(rs.getString("credit"));
                result.add(cou);
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
