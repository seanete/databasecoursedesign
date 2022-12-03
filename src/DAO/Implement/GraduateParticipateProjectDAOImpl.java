package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.GraduateParticipateActivitiyDAO;
import DAO.Interface.GraduateParticipateProjectDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraduateParticipateProjectDAOImpl extends DAObase implements GraduateParticipateProjectDAO {
    private final String GraduateParticipateProject_INSERT_SQL="insert into GraduateParticipateProject(studentNo,projectIndex,participateTime,takeOnWork,budget,mentorSign,hostSign) values(?,?,?,?,?,?,?)";
    private final String GraduateParticipateProject_DELETE_SQL="delete from GraduateParticipateProject where studentNo=? and projectIndex=?";
    private final String GraduateParticipateProject_UPDATE_SQL="update GraduateParticipateProject set studentNo=?,projectIndex=?,participateTime=?,takeOnWork=?,budget=?,mentorSign=?,hostSign=? where studentNo=? and projectIndex=?";
    private final String GraduateParticipateProject_SELECT_SQL="select * from GraduateParticipateProject where studentNo=? and projectIndex=?";

    private static final String searchSQL = "select studentNo,projectIndex,participateTime,takeOnWork，budget,mentorSign,hostSign from GraduateParticipateProject where ";
    @Override
    public void addGraduateParticipateProject(GraduateParticipateProject graduateParticipateProject) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateProject_INSERT_SQL);
            p.setString(1,graduateParticipateProject.getGraduate().getStudentNo());
            p.setString(2,graduateParticipateProject.getProject().getProjectIndex());
            p.setString(3,graduateParticipateProject.getParticipateTime());
            p.setString(4,graduateParticipateProject.getTakeOnWork());
            p.setString(5,graduateParticipateProject.getBudget());
            p.setString(6,graduateParticipateProject.getMentorSign());
            p.setString(7,graduateParticipateProject.getHostSign());
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
    public void updateGraduateParticipateProject(GraduateParticipateProject graduateParticipateProject) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateProject_UPDATE_SQL);
            p.setString(1,graduateParticipateProject.getGraduate().getStudentNo());
            p.setString(2,graduateParticipateProject.getProject().getProjectIndex());
            p.setString(3,graduateParticipateProject.getParticipateTime());
            p.setString(4,graduateParticipateProject.getTakeOnWork());
            p.setString(5,graduateParticipateProject.getBudget());
            p.setString(6,graduateParticipateProject.getMentorSign());
            p.setString(7,graduateParticipateProject.getHostSign());
            p.setString(8,graduateParticipateProject.getGraduate().getStudentNo());
            p.setString(9,graduateParticipateProject.getProject().getProjectIndex());
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
    public void deleteGraduateParticipateProject(Graduate graduate, Project project) {
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateProject_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,project.getProjectIndex());
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
    public GraduateParticipateProject getGraduateParticipateProject(Graduate graduate, Project project) {
        GraduateParticipateProject graduateParticipateProject=new GraduateParticipateProject();
        try{
            PreparedStatement p=getConnection().prepareStatement(GraduateParticipateProject_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,project.getProjectIndex());
            Graduate graduate1=new Graduate();
            Project project1=new Project();
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                graduateParticipateProject.setGraduate(graduate1);
                project1.setProjectIndex(resultSet.getString("projectIndex"));
                graduateParticipateProject.setProject(project1);
                graduateParticipateProject.setParticipateTime(resultSet.getString("participateTime"));
                graduateParticipateProject.setTakeOnWork(resultSet.getString("takeOnWork"));
                graduateParticipateProject.setBudget(resultSet.getString("budget"));
                graduateParticipateProject.setMentorSign(resultSet.getString("mentorSign"));
                graduateParticipateProject.setHostSign(resultSet.getString("hostSign"));
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
        return graduateParticipateProject;
    }

    @Override
    public List<GraduateParticipateProject> findGraduateParticipateProjects(GraduateParticipateProject graduateParticipateProject) {
        List<GraduateParticipateProject> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(graduateParticipateProject.getGraduate().getStudentNo()!= null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(graduateParticipateProject.getGraduate().getStudentNo()) + "%' and ");
        }
        if(graduateParticipateProject.getProject().getProjectIndex() != null){
            sql.append("projectIndex like '%" + DBUtil.fixSqlFieldValue(graduateParticipateProject.getProject().getProjectIndex()) + "%' and ");
        }
        if(graduateParticipateProject.getParticipateTime()!= null){
            sql.append("participateTime like '%" + DBUtil.fixSqlFieldValue(graduateParticipateProject.getParticipateTime()) + "%' and ");
        }
        if(graduateParticipateProject.getTakeOnWork()!= null){
            sql.append("takeOnWork like '%" + DBUtil.fixSqlFieldValue(graduateParticipateProject.getTakeOnWork()) + "%' and ");
        }
        if(graduateParticipateProject.getBudget()!= null){
            sql.append("budget like '%" + DBUtil.fixSqlFieldValue(graduateParticipateProject.getBudget()) + "%' and ");
        }
        if(graduateParticipateProject.getMentorSign()!= null){
            sql.append("mentorSign like '%" + DBUtil.fixSqlFieldValue(graduateParticipateProject.getMentorSign()) + "%' and ");
        }
        if(graduateParticipateProject.getHostSign()!= null){
            sql.append("hostSign like '%" + DBUtil.fixSqlFieldValue(graduateParticipateProject.getHostSign()) + "%' and ");
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
                GraduateParticipateProject gra = new GraduateParticipateProject();
                Graduate gra1=new Graduate();
                Project pro1=new Project();

                gra1.setStudentNo(rs.getString("studentNo"));
                gra.setGraduate(gra1);
                pro1.setProjectIndex(rs.getString("projectIndex"));
                gra.setProject(pro1);
                gra.setParticipateTime(rs.getString("participateTime"));
                gra.setTakeOnWork(rs.getString("takeOnWork"));
                gra.setBudget(rs.getString("budget"));
                gra.setMentorSign(rs.getString("mentorSign"));
                gra.setHostSign(rs.getString("hostSign"));

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
