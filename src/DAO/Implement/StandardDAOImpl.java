package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.StandardDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StandardDAOImpl extends DAObase implements StandardDAO {
    private final String Standard_INSERT_SQL="insert into Standard(standardId,standardName,standardLevel,standardTime,supportMaterial) values(?,?,?,?,?)";
    private final String Standard_DELETE_SQL="delete from Standard where standardId=?";
    private final String Standard_UPDATE_SQL="update Standard set standardId=?,standardName=?,standardLevel=?,standardTime=?,supportMaterial=? where standardId=?";
    private final String Standard_SELECT_SQL="select * from Standard where standardId=?";
    private static final String searchSQL = "select standardId,standardName,standardLevel,standardTime,supportMaterial from Standard where ";

    @Override
    public void addStandard(Standard standard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Standard_INSERT_SQL);
            p.setString(1,standard.getStandardId());
            p.setString(2,standard.getStandardName());
            p.setString(3,standard.getStandardLevel());
            p.setString(4,standard.getStandardTime());
            p.setString(5,standard.getSupportMaterial());

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
    public void updateStandard(Standard standard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Standard_UPDATE_SQL);
            p.setString(1,standard.getStandardId());
            p.setString(2,standard.getStandardName());
            p.setString(3,standard.getStandardLevel());
            p.setString(4,standard.getStandardTime());
            p.setString(5,standard.getSupportMaterial());
            p.setString(6,standard.getStandardId());


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
    public void deleteStandard(String standardId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Standard_DELETE_SQL);
            p.setString(1,standardId);

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
    public Standard getStandard(String standardId) {
        Standard standard=new Standard();
        try{
            PreparedStatement p=getConnection().prepareStatement(Standard_SELECT_SQL);
            p.setString(1,standardId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                standard.setStandardId(resultSet.getString("standardId"));
                standard.setStandardName(resultSet.getString("standardName"));
                standard.setStandardLevel(resultSet.getString("standardLevel"));
                standard.setStandardTime(resultSet.getString("standardTime"));
                standard.setSupportMaterial(resultSet.getString("supportMaterial"));

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
        return standard;
    }

    @Override
    public List<Standard> findStandards(Standard standard) {
        List<Standard> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(standard.getStandardId()!= null){
            sql.append("standardId like '%" + DBUtil.fixSqlFieldValue(standard.getStandardId()) + "%' and ");
        }
        if(standard.getStandardName()!= null){
            sql.append("standardName like '%" + DBUtil.fixSqlFieldValue(standard.getStandardName()) + "%' and ");
        }
        if(standard.getStandardLevel() != null){
            sql.append("standardLevel like '%" + DBUtil.fixSqlFieldValue(standard.getStandardLevel()) + "%' and ");
        }
        if(standard.getStandardTime()!= null){
            sql.append("standardTime like '%" + DBUtil.fixSqlFieldValue(standard.getStandardTime()) + "%' and ");
        }
        if(standard.getSupportMaterial()!= null){
            sql.append("supportMaterial like '%" + DBUtil.fixSqlFieldValue(standard.getSupportMaterial()) + "%' and ");
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
                Standard sta = new Standard();

                sta.setStandardId(rs.getString("standardId"));
                sta.setStandardName(rs.getString("standardName"));
                sta.setStandardLevel(rs.getString("standardLevel"));
                sta.setStandardTime(rs.getString("standardTime"));
                sta.setSupportMaterial(rs.getString("supportMaterial"));

                result.add(sta);
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
