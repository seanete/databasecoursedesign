package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.TextbookDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TextbookDAOImpl extends DAObase implements TextbookDAO {
    private final String Textbook_INSERT_SQL="insert into Textbook(textbookId,textbookName,publicHouse,publicationTime,contributionRank,supportMaterial) values(?,?,?,?,?,?)";
    private final String Textbook_DELETE_SQL="delete from Textbook where textbookId=?";
    private final String Textbook_UPDATE_SQL="update Textbook set textbookId=?,textbookName=?,publicHouse=?,publicationTime=?,contributionRank=?,supportMaterial=? where textbookId=?";
    private final String Textbook_SELECT_SQL="select * from Textbook where textbookId=?";
    private static final String searchSQL = "select textbookId,textbookName,publicHouse,publicationTime,contributionRank,supportMaterial from Textbook where ";

    @Override
    public void addTextbook(Textbook textbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Textbook_INSERT_SQL);
            p.setString(1,textbook.getTextbookId());
            p.setString(2,textbook.getTextbookName());
            p.setString(3,textbook.getPublicHouse());
            p.setString(4,textbook.getPublicationTime());
            p.setString(5,textbook.getContributionRank());
            p.setString(6,textbook.getSupportMaterial());

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
    public void updateTextbook(Textbook Textbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Textbook_UPDATE_SQL);
            p.setString(1,Textbook.getTextbookId());
            p.setString(2,Textbook.getTextbookName());
            p.setString(3,Textbook.getPublicHouse());
            p.setString(4,Textbook.getPublicationTime());
            p.setString(5,Textbook.getContributionRank());
            p.setString(6,Textbook.getSupportMaterial());
            p.setString(7,Textbook.getTextbookId());

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
    public void deleteTextbook(String textbookId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Textbook_DELETE_SQL);
            p.setString(1,textbookId);

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
    public Textbook getTextbook(String textbookId) {
        Textbook textbook=new Textbook();
        try{
            PreparedStatement p=getConnection().prepareStatement(Textbook_SELECT_SQL);
            p.setString(1,textbookId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                textbook.setTextbookId(resultSet.getString("textbookId"));
                textbook.setTextbookName(resultSet.getString("textbookName"));
                textbook.setPublicHouse(resultSet.getString("publicHouse"));
                textbook.setPublicationTime(resultSet.getString("publicationTime"));
                textbook.setContributionRank(resultSet.getString("contributionRank"));
                textbook.setSupportMaterial(resultSet.getString("supportMaterial"));
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
        return textbook;
    }

    @Override
    public List<Textbook> findTextbooks(Textbook textbook) {
        List<Textbook> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(textbook.getTextbookId()!= null){
            sql.append("textbookId like '%" + DBUtil.fixSqlFieldValue(textbook.getTextbookId()) + "%' and ");
        }
        if(textbook.getTextbookName()!= null){
            sql.append("textbookName like '%" + DBUtil.fixSqlFieldValue(textbook.getTextbookName()) + "%' and ");
        }
        if(textbook.getPublicHouse() != null){
            sql.append("publicHouse like '%" + DBUtil.fixSqlFieldValue(textbook.getPublicHouse()) + "%' and ");
        }
        if(textbook.getPublicationTime()!= null){
            sql.append("publicationTime like '%" + DBUtil.fixSqlFieldValue(textbook.getPublicationTime()) + "%' and ");
        }
        if(textbook.getContributionRank()!= null){
            sql.append("contributionRank like '%" + DBUtil.fixSqlFieldValue(textbook.getContributionRank()) + "%' and ");
        }
        if(textbook.getSupportMaterial()!= null){
            sql.append("supportMaterial like '%" + DBUtil.fixSqlFieldValue(textbook.getSupportMaterial()) + "%' and ");
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
                Textbook tex = new Textbook();

                tex.setTextbookId(rs.getString("textbookId"));
                tex.setTextbookName(rs.getString("textbookName"));
                tex.setPublicHouse(rs.getString("publicHouse"));
                tex.setPublicationTime(rs.getString("publicationTime"));
                tex.setContributionRank(rs.getString("contributionRank"));
                tex.setSupportMaterial(rs.getString("supportMaterial"));

                result.add(tex);
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
