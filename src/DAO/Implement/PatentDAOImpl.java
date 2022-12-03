package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.PatentDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatentDAOImpl extends DAObase implements PatentDAO {
    private final String Patent_INSERT_SQL="insert into Patent(patentId,patentName,patentType,patentIndex,patentTime,patentStatus,contributionRank,supportMaterial) values(?,?,?,?,?,?,?,?)";
    private final String Patent_DELETE_SQL="delete from Patent where patentId=?";
    private final String Patent_UPDATE_SQL="update Patent set patentId=?,patentName=?,patentType=?,patentIndex=?,patentTime=?,patentStatus=?,contributionRank=?,supportMaterial=? where patentId=?";
    private final String Patent_SELECT_SQL="select * from Patent where patentId=?";
    private static final String searchSQL = "select patentId,patentName,patentType,patentIndex,patentTime,patentStatus,contributionRank,supportMaterial from Patent where ";

    @Override
    public void addPatent(Patent patent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Patent_INSERT_SQL);
            p.setString(1,patent.getPatentId());
            p.setString(2,patent.getPatentName());
            p.setString(3,patent.getPatentType());
            p.setString(4,patent.getPatentIndex());
            p.setString(5,patent.getPatentTime());
            p.setString(6,patent.getPatentStatus());
            p.setString(5,patent.getContributionRank());
            p.setString(6,patent.getSupportMaterial());

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
    public void updatePatent(Patent patent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Patent_UPDATE_SQL);
            p.setString(1,patent.getPatentId());
            p.setString(2,patent.getPatentName());
            p.setString(3,patent.getPatentType());
            p.setString(4,patent.getPatentIndex());
            p.setString(5,patent.getPatentTime());
            p.setString(6,patent.getPatentStatus());
            p.setString(7,patent.getContributionRank());
            p.setString(8,patent.getSupportMaterial());
            p.setString(9,patent.getPatentId());

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
    public void deletePatent(String patentId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Patent_DELETE_SQL);
            p.setString(1,patentId);

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
    public Patent getPatent(String patentId) {
        Patent patent=new Patent();
        try{
            PreparedStatement p=getConnection().prepareStatement(Patent_SELECT_SQL);
            p.setString(1,patentId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                patent.setPatentId(resultSet.getString("patentId"));
                patent.setPatentName(resultSet.getString("patentName"));
                patent.setPatentType(resultSet.getString("patentType"));
                patent.setPatentIndex(resultSet.getString("patentIndex"));
                patent.setPatentTime(resultSet.getString("patentTime"));
                patent.setPatentStatus(resultSet.getString("patentStatus"));
                patent.setContributionRank(resultSet.getString("contributionRank"));
                patent.setSupportMaterial(resultSet.getString("supportMaterial"));
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
        return patent;
    }

    @Override
    public List<Patent> findPatents(Patent patent) {
        List<Patent> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(patent.getPatentId()!= null){
            sql.append("patentId like '%" + DBUtil.fixSqlFieldValue(patent.getPatentId()) + "%' and ");
        }
        if(patent.getPatentName()!= null){
            sql.append("patentName like '%" + DBUtil.fixSqlFieldValue(patent.getPatentName()) + "%' and ");
        }
        if(patent.getPatentType() != null){
            sql.append("patentType like '%" + DBUtil.fixSqlFieldValue(patent.getPatentType()) + "%' and ");
        }
        if(patent.getPatentIndex()!= null){
            sql.append("patentIndex like '%" + DBUtil.fixSqlFieldValue(patent.getPatentIndex()) + "%' and ");
        }
        if(patent.getPatentTime()!= null){
            sql.append("patentTime like '%" + DBUtil.fixSqlFieldValue(patent.getPatentTime()) + "%' and ");
        }
        if(patent.getPatentStatus()!= null){
            sql.append("patentStatus like '%" + DBUtil.fixSqlFieldValue(patent.getPatentStatus()) + "%' and ");
        }
        if(patent.getContributionRank()!= null){
            sql.append("contributionRank like '%" + DBUtil.fixSqlFieldValue(patent.getContributionRank()) + "%' and ");
        }
        if(patent.getSupportMaterial()!= null){
            sql.append("supportMaterial like '%" + DBUtil.fixSqlFieldValue(patent.getSupportMaterial()) + "%' and ");
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
                Patent pat = new Patent();

                pat.setPatentId(rs.getString("patentId"));
                pat.setPatentName(rs.getString("patentName"));
                pat.setPatentType(rs.getString("patentType"));
                pat.setPatentIndex(rs.getString("patentIndex"));
                pat.setPatentTime(rs.getString("patentTime"));
                pat.setPatentStatus(rs.getString("patentStatus"));
                pat.setContributionRank(rs.getString("contributionRank"));
                pat.setSupportMaterial(rs.getString("supportMaterial"));

                result.add(pat);
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
