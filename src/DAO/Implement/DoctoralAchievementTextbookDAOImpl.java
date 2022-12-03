package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Textbook;
import DAO.Entity.DoctoralAchievementTextbook;
import DAO.Interface.DoctoralAchievementTextbookDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctoralAchievementTextbookDAOImpl extends DAObase implements DoctoralAchievementTextbookDAO {
    private final String DoctoralAchievementTextbook_INSERT_SQL="insert into DoctoralAchievementTextbook(studentNo,textbookId) values(?,?)";
    private final String DoctoralAchievementTextbook_DELETE_SQL="delete from DoctoralAchievementTextbook where studentNo=? and textbookId=? ";
    private final String DoctoralAchievementTextbook_UPDATE_SQL="update DoctoralAchievementTextbook set studentNo=?,textbookId=? where studentNo=? and textbookId=?";
    private final String DoctoralAchievementTextbook_SELECT_SQL="select * from DoctoralAchievementTextbook where studentNo=? and textbookId=?";
    private static final String searchSQL = "select studentNo,textbookId from DoctoralAchievementTextbook where ";

    @Override
    public void addDoctoralAchievementTextbook(DoctoralAchievementTextbook doctoralAchievementTextbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementTextbook_INSERT_SQL);
            p.setString(1,doctoralAchievementTextbook.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementTextbook.getTextbook().getTextbookId());

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
    public void updateDoctoralAchievementTextbook(DoctoralAchievementTextbook doctoralAchievementTextbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementTextbook_UPDATE_SQL);
            p.setString(1,doctoralAchievementTextbook.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementTextbook.getTextbook().getTextbookId());
            p.setString(3,doctoralAchievementTextbook.getGraduate().getStudentNo());
            p.setString(4,doctoralAchievementTextbook.getTextbook().getTextbookId());

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
    public void deleteDoctoralAchievementTextbook(Graduate graduate, Textbook textbook) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementTextbook_DELETE_SQL);
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
    public DoctoralAchievementTextbook getDoctoralAchievementTextbook(Graduate graduate, Textbook textbook) {
        DoctoralAchievementTextbook doctoralAchievementTextbook=new DoctoralAchievementTextbook();
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementTextbook_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,textbook.getTextbookId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Textbook textbook1=new Textbook();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                doctoralAchievementTextbook.setGraduate(graduate1);
                textbook1.setTextbookId(resultSet.getString("textbookId"));
                doctoralAchievementTextbook.setTextbook(textbook1);
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
        return doctoralAchievementTextbook;
    }

    @Override
    public List<DoctoralAchievementTextbook> findDoctoralAchievementTextbooks(DoctoralAchievementTextbook doctoralAchievementTextbook) {
        List<DoctoralAchievementTextbook> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(doctoralAchievementTextbook.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementTextbook.getGraduate().getStudentNo()) + "%' and ");
        }
        if(doctoralAchievementTextbook.getTextbook().getTextbookId()!= null){
            sql.append("textbookId like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementTextbook.getTextbook().getTextbookId()) + "%' and ");
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
                DoctoralAchievementTextbook doc = new DoctoralAchievementTextbook();
                Graduate gra1=new Graduate();
                Textbook tex=new Textbook();

                gra1.setStudentNo(rs.getString("studentNo"));
                doc.setGraduate(gra1);
                tex.setTextbookId(rs.getString("textbookId"));
                doc.setTextbook(tex);

                result.add(doc);
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
