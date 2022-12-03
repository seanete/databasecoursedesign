package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Platform;
import DAO.Entity.MasterAchievementPlatform;
import DAO.Interface.MasterAchievementPlatformDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterAchievementPlatformDAOImpl extends DAObase implements MasterAchievementPlatformDAO {
    private final String MasterAchievementPlatform_INSERT_SQL="insert into MasterAchievementPlatform(studentNo,platformId) values(?,?)";
    private final String MasterAchievementPlatform_DELETE_SQL="delete from MasterAchievementPlatform where studentNo=? and platformId=? ";
    private final String MasterAchievementPlatform_UPDATE_SQL="update MasterAchievementPlatform set studentNo=?,platformId=? where studentNo=? and platformId=?";
    private final String MasterAchievementPlatform_SELECT_SQL="select * from MasterAchievementPlatform where studentNo=? and platformId=?";
    private static final String searchSQL = "select studentNo,platformId from MasterAchievementPlatform where ";

    @Override
    public void addMasterAchievementPlatform(MasterAchievementPlatform masterAchievementPlatform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPlatform_INSERT_SQL);
            p.setString(1,masterAchievementPlatform.getGraduate().getStudentNo());
            p.setString(2,masterAchievementPlatform.getPlatform().getPlatformId());

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
    public void updateMasterAchievementPlatform(MasterAchievementPlatform masterAchievementPlatform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPlatform_UPDATE_SQL);
            p.setString(1,masterAchievementPlatform.getGraduate().getStudentNo());
            p.setString(2,masterAchievementPlatform.getPlatform().getPlatformId());
            p.setString(3,masterAchievementPlatform.getGraduate().getStudentNo());
            p.setString(4,masterAchievementPlatform.getPlatform().getPlatformId());

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
    public void deleteMasterAchievementPlatform(Graduate graduate, Platform platform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPlatform_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,platform.getPlatformId());

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
    public MasterAchievementPlatform getMasterAchievementPlatform(Graduate graduate, Platform platform) {
        MasterAchievementPlatform masterAchievementPlatform=new MasterAchievementPlatform();
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPlatform_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,platform.getPlatformId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Platform platform1=new Platform();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                masterAchievementPlatform.setGraduate(graduate1);
                platform1.setPlatformId(resultSet.getString("platformId"));
                masterAchievementPlatform.setPlatform(platform1);
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
        return masterAchievementPlatform;
    }

    @Override
    public List<MasterAchievementPlatform> findMasterAchievementPlatforms(MasterAchievementPlatform masterAchievementPlatform) {
        List<MasterAchievementPlatform> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(masterAchievementPlatform.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(masterAchievementPlatform.getGraduate().getStudentNo()) + "%' and ");
        }
        if(masterAchievementPlatform.getPlatform().getPlatformId()!= null){
            sql.append("platformId like '%" + DBUtil.fixSqlFieldValue(masterAchievementPlatform.getPlatform().getPlatformId()) + "%' and ");
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
                MasterAchievementPlatform mas = new MasterAchievementPlatform();
                Graduate gra1=new Graduate();
                Platform pla=new Platform();

                gra1.setStudentNo(rs.getString("studentNo"));
                mas.setGraduate(gra1);
                pla.setPlatformId(rs.getString("platformId"));
                mas.setPlatform(pla);

                result.add(mas);
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
