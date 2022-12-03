package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Platform;
import DAO.Entity.DoctoralAchievementPlatform;
import DAO.Interface.DoctoralAchievementPlatformDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctoralAchievementPlatformDAOImpl extends DAObase implements DoctoralAchievementPlatformDAO {
    private final String DoctoralAchievementPlatform_INSERT_SQL="insert into DoctoralAchievementPlatform(studentNo,platformId) values(?,?)";
    private final String DoctoralAchievementPlatform_DELETE_SQL="delete from DoctoralAchievementPlatform where studentNo=? and platformId=? ";
    private final String DoctoralAchievementPlatform_UPDATE_SQL="update DoctoralAchievementPlatform set studentNo=?,platformId=? where studentNo=? and platformId=?";
    private final String DoctoralAchievementPlatform_SELECT_SQL="select * from DoctoralAchievementPlatform where studentNo=? and platformId=?";
    private static final String searchSQL = "select studentNo,platformId from DoctoralAchievementPlatform where ";

    @Override
    public void addDoctoralAchievementPlatform(DoctoralAchievementPlatform doctoralAchievementPlatform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPlatform_INSERT_SQL);
            p.setString(1,doctoralAchievementPlatform.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementPlatform.getPlatform().getPlatformId());

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
    public void updateDoctoralAchievementPlatform(DoctoralAchievementPlatform doctoralAchievementPlatform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPlatform_UPDATE_SQL);
            p.setString(1,doctoralAchievementPlatform.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementPlatform.getPlatform().getPlatformId());
            p.setString(3,doctoralAchievementPlatform.getGraduate().getStudentNo());
            p.setString(4,doctoralAchievementPlatform.getPlatform().getPlatformId());

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
    public void deleteDoctoralAchievementPlatform(Graduate graduate, Platform platform) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPlatform_DELETE_SQL);
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
    public DoctoralAchievementPlatform getDoctoralAchievementPlatform(Graduate graduate, Platform platform) {
        DoctoralAchievementPlatform doctoralAchievementPlatform=new DoctoralAchievementPlatform();
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPlatform_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,platform.getPlatformId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Platform platform1=new Platform();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                doctoralAchievementPlatform.setGraduate(graduate1);
                platform1.setPlatformId(resultSet.getString("platformId"));
                doctoralAchievementPlatform.setPlatform(platform1);
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
        return doctoralAchievementPlatform;
    }

    @Override
    public List<DoctoralAchievementPlatform> findDoctoralAchievementPlatforms(DoctoralAchievementPlatform doctoralAchievementPlatform) {
        List<DoctoralAchievementPlatform> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(doctoralAchievementPlatform.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementPlatform.getGraduate().getStudentNo()) + "%' and ");
        }
        if(doctoralAchievementPlatform.getPlatform().getPlatformId()!= null){
            sql.append("platformId like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementPlatform.getPlatform().getPlatformId()) + "%' and ");
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
                DoctoralAchievementPlatform doc = new DoctoralAchievementPlatform();
                Graduate gra1=new Graduate();
                Platform pla=new Platform();

                gra1.setStudentNo(rs.getString("studentNo"));
                doc.setGraduate(gra1);
                pla.setPlatformId(rs.getString("platformId"));
                doc.setPlatform(pla);

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
