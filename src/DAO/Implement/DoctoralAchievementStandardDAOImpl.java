package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Standard;
import DAO.Entity.DoctoralAchievementStandard;
import DAO.Interface.DoctoralAchievementStandardDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctoralAchievementStandardDAOImpl extends DAObase implements DoctoralAchievementStandardDAO {
    private final String DoctoralAchievementStandard_INSERT_SQL="insert into DoctoralAchievementStandard(studentNo,standardId) values(?,?)";
    private final String DoctoralAchievementStandard_DELETE_SQL="delete from DoctoralAchievementStandard where studentNo=? and standardId=? ";
    private final String DoctoralAchievementStandard_UPDATE_SQL="update DoctoralAchievementStandard set studentNo=?,standardId=? where studentNo=? and standardId=?";
    private final String DoctoralAchievementStandard_SELECT_SQL="select * from DoctoralAchievementStandard where studentNo=? and standardId=?";
    private static final String searchSQL = "select studentNo,standardId from DoctoralAchievementStandard where ";

    @Override
    public void addDoctoralAchievementStandard(DoctoralAchievementStandard doctoralAchievementStandard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementStandard_INSERT_SQL);
            p.setString(1,doctoralAchievementStandard.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementStandard.getStandard().getStandardId());

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
    public void updateDoctoralAchievementStandard(DoctoralAchievementStandard doctoralAchievementStandard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementStandard_UPDATE_SQL);
            p.setString(1,doctoralAchievementStandard.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementStandard.getStandard().getStandardId());
            p.setString(3,doctoralAchievementStandard.getGraduate().getStudentNo());
            p.setString(4,doctoralAchievementStandard.getStandard().getStandardId());

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
    public void deleteDoctoralAchievementStandard(Graduate graduate, Standard standard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementStandard_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,standard.getStandardId());

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
    public DoctoralAchievementStandard getDoctoralAchievementStandard(Graduate graduate, Standard standard) {
        DoctoralAchievementStandard doctoralAchievementStandard=new DoctoralAchievementStandard();
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementStandard_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,standard.getStandardId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Standard standard1=new Standard();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                doctoralAchievementStandard.setGraduate(graduate1);
                standard1.setStandardId(resultSet.getString("standardId"));
                doctoralAchievementStandard.setStandard(standard1);
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
        return doctoralAchievementStandard;
    }

    @Override
    public List<DoctoralAchievementStandard> findDoctoralAchievementStandards(DoctoralAchievementStandard doctoralAchievementStandard) {
        List<DoctoralAchievementStandard> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(doctoralAchievementStandard.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementStandard.getGraduate().getStudentNo()) + "%' and ");
        }
        if(doctoralAchievementStandard.getStandard().getStandardId()!= null){
            sql.append("standardId like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementStandard.getStandard().getStandardId()) + "%' and ");
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
                DoctoralAchievementStandard doc = new DoctoralAchievementStandard();
                Graduate gra1=new Graduate();
                Standard sta=new Standard();

                gra1.setStudentNo(rs.getString("studentNo"));
                doc.setGraduate(gra1);
                sta.setStandardId(rs.getString("standardId"));
                doc.setStandard(sta);

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
