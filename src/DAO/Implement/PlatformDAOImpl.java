package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.PlatformDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatformDAOImpl extends DAObase implements PlatformDAO {
    private final String Platform_INSERT_SQL="insert into Platform(platformId,platformName,serviceUnit,platformTime,contributionRank,supportMaterial) values(?,?,?,?,?,?)";
    private final String Platform_DELETE_SQL="delete from Platform where platformId=?";
    private final String Platform_UPDATE_SQL="update Paper set platformId=?,platformName=?,serviceUnit=?,platformTime=?,contributionRank=?,supportMaterial=? where platformId=?";
    private final String Platform_SELECT_SQL="select * from Platform where platformId=?";
    private static final String searchSQL = "select platformId,platformName,serviceUnit,platformTime,contributionRank,supportMaterial from Platform where ";

    @Override
    public void addPlatform(Platform platform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Platform_INSERT_SQL);
            p.setString(1,platform.getPlatformId());
            p.setString(2,platform.getPlatformName());
            p.setString(3,platform.getServiceUnit());
            p.setString(4,platform.getPlatformTime());
            p.setString(5,platform.getContributionRank());
            p.setString(6,platform.getSupportMaterial());

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
    public void updatePlatform(Platform platform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Platform_UPDATE_SQL);
            p.setString(1,platform.getPlatformId());
            p.setString(2,platform.getPlatformName());
            p.setString(3,platform.getServiceUnit());
            p.setString(4,platform.getPlatformTime());
            p.setString(5,platform.getContributionRank());
            p.setString(6,platform.getSupportMaterial());
            p.setString(7,platform.getPlatformId());

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
    public void deletePlatform(String platformId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Platform_DELETE_SQL);
            p.setString(1,platformId);

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
    public Platform getPlatform(String platformId) {
        Platform platform=new Platform();
        try{
            PreparedStatement p=getConnection().prepareStatement(Platform_SELECT_SQL);
            p.setString(1,platformId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                platform.setPlatformId(resultSet.getString("platformId"));
                platform.setPlatformName(resultSet.getString("platformName"));
                platform.setServiceUnit(resultSet.getString("serviceUnit"));
                platform.setPlatformTime(resultSet.getString("platformTime"));
                platform.setContributionRank(resultSet.getString("contributionRank"));
                platform.setSupportMaterial(resultSet.getString("supportMaterial"));
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
        return platform;
    }

    @Override
    public List<Platform> findPlatforms(Platform platform) {
        List<Platform> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(platform.getPlatformId()!= null){
            sql.append("platformId like '%" + DBUtil.fixSqlFieldValue(platform.getPlatformId()) + "%' and ");
        }
        if(platform.getPlatformName()!= null){
            sql.append("platformName like '%" + DBUtil.fixSqlFieldValue(platform.getPlatformName()) + "%' and ");
        }
        if(platform.getPlatformName() != null){
            sql.append("serviceUnit like '%" + DBUtil.fixSqlFieldValue(platform.getPlatformName()) + "%' and ");
        }
        if(platform.getPlatformTime()!= null){
            sql.append("platformTime like '%" + DBUtil.fixSqlFieldValue(platform.getPlatformTime()) + "%' and ");
        }
        if(platform.getContributionRank()!= null){
            sql.append("contributionRank like '%" + DBUtil.fixSqlFieldValue(platform.getContributionRank()) + "%' and ");
        }
        if(platform.getSupportMaterial()!= null){
            sql.append("supportMaterial like '%" + DBUtil.fixSqlFieldValue(platform.getSupportMaterial()) + "%' and ");
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
                Platform pla = new Platform();

                pla.setPlatformId(rs.getString("platformId"));
                pla.setPlatformName(rs.getString("platformName"));
                pla.setServiceUnit(rs.getString("serviceUnit"));
                pla.setPlatformTime(rs.getString("platformTime"));
                pla.setContributionRank(rs.getString("contributionRank"));
                pla.setSupportMaterial(rs.getString("supportMaterial"));

                result.add(pla);
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
