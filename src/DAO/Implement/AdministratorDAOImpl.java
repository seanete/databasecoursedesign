package DAO.Implement;

import DAO.Entity.Administrator;
import DAO.Entity.Mentor;
import DAO.Entity.Subject;
import DAO.Interface.AdministratorDAO;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAOImpl extends DAObase implements AdministratorDAO {
    private final String Administrator_INSERT_SQL = "insert into Administrator(administratorNo,administratorName) values(?,?)";
    private final String Administrator_DELETE_SQL = "delete from Administrator where administratorNo=? ";
    private final String Administrator_UPDATE_SQL = "update Administrator set administratorNo=?,administratorName=? where administratorNo=?";
    private final String Administrator_SELECT_SQL = "select * from Administrator where administratorNo=?";

    private static final String searchSQL = "select * from Administrator where ";

    @Override
    public void addAdministrator(Administrator administrator) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Administrator_INSERT_SQL);
            p.setString(1,administrator.getAdministratorNo());
            p.setString(2,administrator.getAdministratorName());
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
    public void updateAdministrator(Administrator administrator) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Administrator_UPDATE_SQL);
            p.setString(1,administrator.getAdministratorNo());
            p.setString(2,administrator.getAdministratorName());
            p.setString(3,administrator.getAdministratorNo());
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
    public void deleteAdministrator(String administratorNo) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Administrator_DELETE_SQL);
            p.setString(1,administratorNo);
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
    public Administrator getAdministrator(String administratorNo) {
        Administrator administrator = new Administrator();
        try{
            PreparedStatement p=getConnection().prepareStatement(Administrator_SELECT_SQL);
            p.setString(1,administratorNo);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                administrator.setAdministratorNo(resultSet.getString(1));
                administrator.setAdministratorName(resultSet.getString(2));
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
        return administrator;
    }

    @Override
    public List<Administrator> findAdministrators(Administrator administrator) {
        List<Administrator> administrators = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(administrator.getAdministratorNo() != null){
            sql.append("administratorNo like '%" + DBUtil.fixSqlFieldValue(administrator.getAdministratorNo()) + "%' and ");
        }
        if(administrator.getAdministratorName() != null){
            sql.append("administratorName like '%" + DBUtil.fixSqlFieldValue(administrator.getAdministratorName()) + "%' and ");
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
                Administrator administrator1 = new Administrator();
                administrator1.setAdministratorNo(rs.getString(1));
                administrator1.setAdministratorName(rs.getString(2));

                administrators.add(administrator1);
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
        return administrators;

    }
}
