package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.AwardDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AwardDAOImpl extends DAObase implements AwardDAO {
    private final String Award_INSERT_SQL="insert into Award(awardId,awardLevel,getAwardLevel,rank,awardTime,supportMaterial) values(?,?,?,?,?,?)";
    private final String Award_DELETE_SQL="delete from Award where awardID=?";
    private final String Award_UPDATE_SQL="update Award set awardId=?,awardLevel=?,getAwardLevel=?,rank=?,awardTime=?,supportMaterial=? where awardId=?";
    private final String Award_SELECT_SQL="select * from Award where awardID=?";
    private static final String searchSQL = "select awardId,awardLevel,getAwardLevel,rank,awardTime,supportMaterial from Award where ";

    @Override
    public void addAward(Award award) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Award_INSERT_SQL);
            p.setString(1,award.getAwardId());
            p.setString(2,award.getAwardLevel());
            p.setString(3,award.getGetAwardLevel());
            p.setString(4,award.getRank());
            p.setString(5,award.getAwardTime());
            p.setString(6,award.getSupportMaterial());

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
    public void updateAward(Award award) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Award_UPDATE_SQL);
            p.setString(1,award.getAwardId());
            p.setString(2,award.getAwardLevel());
            p.setString(3,award.getGetAwardLevel());
            p.setString(4,award.getRank());
            p.setString(5,award.getAwardTime());
            p.setString(6,award.getSupportMaterial());
            p.setString(7,award.getAwardId());

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
    public void deleteAward(String awardId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Award_DELETE_SQL);
            p.setString(1,awardId);

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
    public Award getAward(String awardId) {
        Award award=new Award();
        try{
            PreparedStatement p=getConnection().prepareStatement(Award_SELECT_SQL);
            p.setString(1,awardId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                award.setAwardId(resultSet.getString("awardId"));
                award.setAwardLevel(resultSet.getString("awardLevel"));
                award.setGetAwardLevel(resultSet.getString("getAwardLevel"));
                award.setRank(resultSet.getString("rank"));
                award.setAwardTime(resultSet.getString("awardTime"));
                award.setSupportMaterial(resultSet.getString("supportMaterial"));
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
        return award;
    }

    @Override
    public List<Award> findAwards(Award award) {
        List<Award> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(award.getAwardId()!= null){
            sql.append("awardId like '%" + DBUtil.fixSqlFieldValue(award.getAwardId()) + "%' and ");
        }
        if(award.getAwardLevel()!= null){
            sql.append("awardLevel like '%" + DBUtil.fixSqlFieldValue(award.getAwardLevel()) + "%' and ");
        }
        if(award.getGetAwardLevel() != null){
            sql.append("getAwardLevel like '%" + DBUtil.fixSqlFieldValue(award.getGetAwardLevel()) + "%' and ");
        }
        if(award.getRank()!= null){
            sql.append("rank like '%" + DBUtil.fixSqlFieldValue(award.getRank()) + "%' and ");
        }
        if(award.getAwardTime()!= null){
            sql.append("awardTime like '%" + DBUtil.fixSqlFieldValue(award.getAwardTime()) + "%' and ");
        }
        if(award.getSupportMaterial()!= null){
            sql.append("supportMaterial like '%" + DBUtil.fixSqlFieldValue(award.getSupportMaterial()) + "%' and ");
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
                Award awa = new Award();

                awa.setAwardId(rs.getString("awardId"));
                awa.setAwardLevel(rs.getString("awardLevel"));
                awa.setGetAwardLevel(rs.getString("getAwardLevel"));
                awa.setRank(rs.getString("rank"));
                awa.setAwardTime(rs.getString("awardTime"));
                awa.setSupportMaterial(rs.getString("supportMaterial"));

                result.add(awa);
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
