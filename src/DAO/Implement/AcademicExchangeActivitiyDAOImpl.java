package DAO.Implement;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.Entity.Mentor;
import DAO.Interface.AcademicExchangeActivitiyDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcademicExchangeActivitiyDAOImpl extends DAObase implements AcademicExchangeActivitiyDAO {
    private final String AcademicExchangeActivitiy_INSERT_SQL="insert into AcademicExchangeActivitiy(activityId,activityName,activityTime,activityLocation) values(?,?,?,?)";
    private final String AcademicExchangeActivitiy_DELETE_SQL="delete from AcademicExchangeActivitiy where activityId=? ";
    private final String AcademicExchangeActivitiy_UPDATE_SQL="update AcademicExchangeActivitiy set activityId=?,activityName=?,activityTime=?,activityLocation=? where activityId=?";
    private final String AcademicExchangeActivitiy_SELECT_SQL="select * from AcademicExchangeActivitiy where activityId=?";

    private static final String searchSQL = "select activityId,activityName,activityTime,activityLocation from AcademicExchangeActivitiy where ";
    @Override
    public void addAcademicExchangeActivitiy(AcademicExchangeActivitiy academicExchangeActivitiy) {
        try{
            PreparedStatement p=getConnection().prepareStatement(AcademicExchangeActivitiy_INSERT_SQL);
            p.setString(1,academicExchangeActivitiy.getActivityId());
            p.setString(2,academicExchangeActivitiy.getActivityName());
            p.setString(3,academicExchangeActivitiy.getActivityTime());
            p.setString(4,academicExchangeActivitiy.getActivityLocation());
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
    public void updateAcademicExchangeActivitiy(AcademicExchangeActivitiy academicExchangeActivitiy) {
        try{
            PreparedStatement p=getConnection().prepareStatement(AcademicExchangeActivitiy_UPDATE_SQL);
            p.setString(1,academicExchangeActivitiy.getActivityId());
            p.setString(2,academicExchangeActivitiy.getActivityName());
            p.setString(3,academicExchangeActivitiy.getActivityTime());
            p.setString(4,academicExchangeActivitiy.getActivityLocation());
            p.setString(5,academicExchangeActivitiy.getActivityId());
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
    public void deleteAcademicExchangeActivitiy(String activityId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(AcademicExchangeActivitiy_DELETE_SQL);
            p.setString(1,activityId);
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
    public AcademicExchangeActivitiy getAcademicExchangeActivitiy(String activityId) {
        AcademicExchangeActivitiy academicExchangeActivitiy=new AcademicExchangeActivitiy();
        try{
            PreparedStatement p=getConnection().prepareStatement(AcademicExchangeActivitiy_SELECT_SQL);
            p.setString(1,activityId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                academicExchangeActivitiy.setActivityId(resultSet.getString("activityId"));
                academicExchangeActivitiy.setActivityName(resultSet.getString("activityName"));
                academicExchangeActivitiy.setActivityTime(resultSet.getString("activityTime"));
                academicExchangeActivitiy.setActivityLocation(resultSet.getString("activityLocation"));
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
        return academicExchangeActivitiy;
    }

    @Override
    public List<AcademicExchangeActivitiy> findAcademicExchangeActivitiys(AcademicExchangeActivitiy academicExchangeActivitiy) {
        List<AcademicExchangeActivitiy> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(academicExchangeActivitiy.getActivityId() != null){
            sql.append("activityId like '%" + DBUtil.fixSqlFieldValue(academicExchangeActivitiy.getActivityId()) + "%' and ");
        }
        if(academicExchangeActivitiy.getActivityName() != null){
            sql.append("activityName like '%" + DBUtil.fixSqlFieldValue(academicExchangeActivitiy.getActivityName()) + "%' and ");
        }
        if(academicExchangeActivitiy.getActivityTime() != null){
            sql.append("activityTime like '%" + DBUtil.fixSqlFieldValue(academicExchangeActivitiy.getActivityTime()) + "%' and ");
        }
        if(academicExchangeActivitiy.getActivityLocation() != null){
            sql.append("activityLocation like '%" + DBUtil.fixSqlFieldValue(academicExchangeActivitiy.getActivityLocation()) + "%' and ");
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
                AcademicExchangeActivitiy ac = new AcademicExchangeActivitiy();
                ac.setActivityId(rs.getString("activityId"));
                ac.setActivityName(rs.getString("activityName"));
                ac.setActivityTime(rs.getString("activityTime"));
                ac.setActivityLocation(rs.getString("activityLocation"));
                result.add(ac);
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
