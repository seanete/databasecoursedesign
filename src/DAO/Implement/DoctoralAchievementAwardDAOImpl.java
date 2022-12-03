package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Award;
import DAO.Entity.DoctoralAchievementAward;
import DAO.Interface.DoctoralAchievementAwardDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctoralAchievementAwardDAOImpl extends DAObase implements DoctoralAchievementAwardDAO {
    private final String DoctoralAchievementAward_INSERT_SQL="insert into DoctoralAchievementAward(studentNo,awardId) values(?,?)";
    private final String DoctoralAchievementAward_DELETE_SQL="delete from DoctoralAchievementAward where studentNo=? and awardId=? ";
    private final String DoctoralAchievementAward_UPDATE_SQL="update DoctoralAchievementAward set studentNo=?,awardId=? where studentNo=? and awardId=?";
    private final String DoctoralAchievementAward_SELECT_SQL="select * from DoctoralAchievementAward where studentNo=? and awardId=?";
    private static final String searchSQL = "select studentNo,awardId from DoctoralAchievementAward where ";

    @Override
    public void addDoctoralAchievementAward(DoctoralAchievementAward doctoralAchievementAward) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementAward_INSERT_SQL);
            p.setString(1,doctoralAchievementAward.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementAward.getAward().getAwardId());
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
    public void updateDoctoralAchievementAward(DoctoralAchievementAward doctoralAchievementAward) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementAward_UPDATE_SQL);
            p.setString(1,doctoralAchievementAward.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementAward.getAward().getAwardId());
            p.setString(3,doctoralAchievementAward.getGraduate().getStudentNo());
            p.setString(4,doctoralAchievementAward.getAward().getAwardId());

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
    public void deleteDoctoralAchievementAward(Graduate graduate, Award award) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementAward_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,award.getAwardId());

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
    public DoctoralAchievementAward getDoctoralAchievementAward(Graduate graduate, Award award) {
        DoctoralAchievementAward doctoralAchievementAward=new DoctoralAchievementAward();
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementAward_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,award.getAwardId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Award award1=new Award();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                doctoralAchievementAward.setGraduate(graduate1);
                award1.setAwardId(resultSet.getString("awardId"));
                doctoralAchievementAward.setAward(award1);
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
        return doctoralAchievementAward;
    }

    @Override
    public List<DoctoralAchievementAward> findDoctoralAchievementAwards(DoctoralAchievementAward doctoralAchievementAward) {
        List<DoctoralAchievementAward> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(doctoralAchievementAward.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementAward.getGraduate().getStudentNo()) + "%' and ");
        }
        if(doctoralAchievementAward.getAward().getAwardId()!= null){
            sql.append("awardId like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementAward.getAward().getAwardId()) + "%' and ");
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
                DoctoralAchievementAward doc = new DoctoralAchievementAward();
                Graduate gra1=new Graduate();
                Award awa=new Award();

                gra1.setStudentNo(rs.getString("studentNo"));
                doc.setGraduate(gra1);
                awa.setAwardId(rs.getString("awardId"));
                doc.setAward(awa);

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
