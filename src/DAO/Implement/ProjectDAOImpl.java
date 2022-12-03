package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.ProjectDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl extends DAObase implements ProjectDAO {
    private final String Project_INSERT_SQL="insert into Project(projectIndex,projectType,projectName,subjectNo,mentorNo) values(?,?,?,?,?)";
    private final String Project_DELETE_SQL="delete from Project where projectIndex=?";
    private final String Project_UPDATE_SQL="update Project set projectIndex=?,projectType=?,projectName=?,subjectNo=?,mentorNo=? where projectIndex=?";
    private final String Project_SELECT_SQL="select * from Project where projectIndex=?";
    private static final String searchSQL = "select projectIndex,projectType,projectName,subjectNo,mentorNo from Project where ";
    @Override
    public void addProject(Project project) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Project_INSERT_SQL);
            p.setString(1,project.getProjectIndex());
            p.setString(2,project.getProjectType());
            p.setString(3,project.getProjectName());
            p.setString(4,project.getSubject().getSubjectNo());
            p.setString(5,project.getMentor().getMentorNo());
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
    public void updateProject(Project project) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Project_UPDATE_SQL);
            p.setString(1,project.getProjectIndex());
            p.setString(2,project.getProjectType());
            p.setString(3,project.getProjectName());
            p.setString(4,project.getSubject().getSubjectNo());
            p.setString(5,project.getMentor().getMentorNo());
            p.setString(6,project.getProjectIndex());
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
    public void deleteProject(String projectIndex) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Project_DELETE_SQL);
            p.setString(1,projectIndex);
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
    public Project getProject(String projectIndex) {
        Project project=new Project();
        try{
            PreparedStatement p=getConnection().prepareStatement(Project_SELECT_SQL);
            p.setString(1,projectIndex);
            ResultSet resultSet=p.executeQuery();
            Subject subject=new Subject();
            Mentor mentor=new Mentor();
            while(resultSet.next()){
                project.setProjectIndex(resultSet.getString("projectIndex"));
                project.setProjectType(resultSet.getString("projectType"));
                project.setProjectName(resultSet.getString("projectName"));
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                project.setSubject(subject);
                mentor.setMentorNo(resultSet.getString("mentorNo"));
                project.setMentor(mentor);
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
        return project;
    }
    @Override
    public List<Project> findProjects(Project project) {
        List<Project> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(project.getProjectIndex()!= null){
            sql.append("projectIndex like '%" + DBUtil.fixSqlFieldValue(project.getProjectIndex()) + "%' and ");
        }
        if(project.getProjectType() != null){
            sql.append("projectType like '%" + DBUtil.fixSqlFieldValue(project.getProjectType()) + "%' and ");
        }
        if(project.getProjectName() != null){
            sql.append("projectName like '%" + DBUtil.fixSqlFieldValue(project.getProjectName()) + "%' and ");
        }
        if(project.getSubject().getSubjectNo() != null){
            sql.append("subjectNo like '%" + DBUtil.fixSqlFieldValue(project.getSubject().getSubjectNo()) + "%' and ");
        }
        if(project.getMentor().getMentorNo() != null){
            sql.append("mentorNo like '%" + DBUtil.fixSqlFieldValue(project.getMentor().getMentorNo()) + "%' and ");
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
                Project pro = new Project();
                Subject sub=new Subject();
                Mentor men=new Mentor();

                pro.setProjectIndex(rs.getString("projectIndex"));
                pro.setProjectType(rs.getString("projectType"));
                pro.setProjectName(rs.getString("projectName"));
                sub.setSubjectNo(rs.getString("subjectNo"));
                pro.setSubject(sub);
                men.setMentorNo(rs.getString("mentorNo"));
                pro.setMentor(men);

                result.add(pro);
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
