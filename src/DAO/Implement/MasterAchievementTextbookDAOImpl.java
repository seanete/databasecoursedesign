package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Textbook;
import DAO.Entity.MasterAchievementTextbook;
import DAO.Interface.MasterAchievementTextbookDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterAchievementTextbookDAOImpl extends DAObase implements MasterAchievementTextbookDAO {
    private final String MasterAchievementTextbook_INSERT_SQL="insert into MasterAchievementTextbook(studentNo,textbookId) values(?,?)";
    private final String MasterAchievementTextbook_DELETE_SQL="delete from MasterAchievementTextbook where studentNo=? and textbookId=? ";
    private final String MasterAchievementTextbook_UPDATE_SQL="update MasterAchievementTextbook set studentNo=?,textbookId=? where studentNo=? and textbookId=?";
    private final String MasterAchievementTextbook_SELECT_SQL="select * from MasterAchievementTextbook where studentNo=? and textbookId=?";
    private static final String searchSQL = "select studentNo,textbookId from MasterAchievementTextbook where ";

    @Override
    public void addMasterAchievementTextbook(MasterAchievementTextbook masterAchievementTextbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementTextbook_INSERT_SQL);
            p.setString(1,masterAchievementTextbook.getGraduate().getStudentNo());
            p.setString(2,masterAchievementTextbook.getTextbook().getTextbookId());

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
    public void updateMasterAchievementTextbook(MasterAchievementTextbook masterAchievementTextbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementTextbook_UPDATE_SQL);
            p.setString(1,masterAchievementTextbook.getGraduate().getStudentNo());
            p.setString(2,masterAchievementTextbook.getTextbook().getTextbookId());
            p.setString(3,masterAchievementTextbook.getGraduate().getStudentNo());
            p.setString(4,masterAchievementTextbook.getTextbook().getTextbookId());

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
    public void deleteMasterAchievementTextbook(Graduate graduate, Textbook textbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementTextbook_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,textbook.getTextbookId());

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
    public MasterAchievementTextbook getMasterAchievementTextbook(Graduate graduate, Textbook textbook) {
        MasterAchievementTextbook masterAchievementTextbook=new MasterAchievementTextbook();
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementTextbook_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,textbook.getTextbookId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Textbook textbook1=new Textbook();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                masterAchievementTextbook.setGraduate(graduate1);
                textbook1.setTextbookId(resultSet.getString("textbookId"));
                masterAchievementTextbook.setTextbook(textbook1);
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
        return masterAchievementTextbook;
    }

    @Override
    public List<MasterAchievementTextbook> findMasterAchievementTextbooks(MasterAchievementTextbook masterAchievementTextbook) {
        List<MasterAchievementTextbook> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(masterAchievementTextbook.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(masterAchievementTextbook.getGraduate().getStudentNo()) + "%' and ");
        }
        if(masterAchievementTextbook.getTextbook().getTextbookId()!= null){
            sql.append("textbookId like '%" + DBUtil.fixSqlFieldValue(masterAchievementTextbook.getTextbook().getTextbookId()) + "%' and ");
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
                MasterAchievementTextbook mas = new MasterAchievementTextbook();
                Graduate gra1=new Graduate();
                Textbook tex=new Textbook();

                gra1.setStudentNo(rs.getString("studentNo"));
                mas.setGraduate(gra1);
                tex.setTextbookId(rs.getString("textbookId"));
                mas.setTextbook(tex);

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
