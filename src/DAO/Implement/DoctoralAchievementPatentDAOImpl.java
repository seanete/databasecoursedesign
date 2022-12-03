package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Patent;
import DAO.Entity.DoctoralAchievementPatent;
import DAO.Interface.DoctoralAchievementPatentDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctoralAchievementPatentDAOImpl extends DAObase implements DoctoralAchievementPatentDAO {
    private final String DoctoralAchievementPatent_INSERT_SQL="insert into DoctoralAchievementPatent(studentNo,patentId) values(?,?)";
    private final String DoctoralAchievementPatent_DELETE_SQL="delete from DoctoralAchievementPatent where studentNo=? and patentId=? ";
    private final String DoctoralAchievementPatent_UPDATE_SQL="update DoctoralAchievementPatent set studentNo=?,patentId=? where studentNo=? and patentId=?";
    private final String DoctoralAchievementPatent_SELECT_SQL="select * from DoctoralAchievementPatent where studentNo=? and patentId=?";
    private static final String searchSQL = "select studentNo,patentId from DoctoralAchievementPatent where ";

    @Override
    public void addDoctoralAchievementPatent(DoctoralAchievementPatent doctoralAchievementPatent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPatent_INSERT_SQL);
            p.setString(1,doctoralAchievementPatent.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementPatent.getPatent().getPatentId());

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
    public void updateDoctoralAchievementPatent(DoctoralAchievementPatent doctoralAchievementPatent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPatent_UPDATE_SQL);
            p.setString(1,doctoralAchievementPatent.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementPatent.getPatent().getPatentId());
            p.setString(3,doctoralAchievementPatent.getGraduate().getStudentNo());
            p.setString(4,doctoralAchievementPatent.getPatent().getPatentId());

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
    public void deleteDoctoralAchievementPatent(Graduate graduate, Patent patent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPatent_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,patent.getPatentId());

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
    public DoctoralAchievementPatent getDoctoralAchievementPatent(Graduate graduate, Patent patent) {
        DoctoralAchievementPatent doctoralAchievementPatent=new DoctoralAchievementPatent();
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPatent_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,patent.getPatentId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Patent patent1=new Patent();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                doctoralAchievementPatent.setGraduate(graduate1);
                patent1.setPatentId(resultSet.getString("patentId"));
                doctoralAchievementPatent.setPatent(patent1);
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
        return doctoralAchievementPatent;
    }

    @Override
    public List<DoctoralAchievementPatent> findDoctoralAchievementPatents(DoctoralAchievementPatent doctoralAchievementPatent) {
        List<DoctoralAchievementPatent> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(doctoralAchievementPatent.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementPatent.getGraduate().getStudentNo()) + "%' and ");
        }
        if(doctoralAchievementPatent.getPatent().getPatentId()!= null){
            sql.append("patentId like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementPatent.getPatent().getPatentId()) + "%' and ");
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
                DoctoralAchievementPatent doc = new DoctoralAchievementPatent();
                Graduate gra1=new Graduate();
                Patent pat=new Patent();

                gra1.setStudentNo(rs.getString("studentNo"));
                doc.setGraduate(gra1);
                pat.setPatentId(rs.getString("patentId"));
                doc.setPatent(pat);

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
