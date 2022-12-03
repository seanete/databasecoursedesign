package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Patent;
import DAO.Entity.MasterAchievementPatent;
import DAO.Interface.MasterAchievementPatentDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterAchievementPatentDAOImpl extends DAObase implements MasterAchievementPatentDAO {
    private final String MasterAchievementPatent_INSERT_SQL="insert into MasterAchievementPatent(studentNo,patentId) values(?,?)";
    private final String MasterAchievementPatent_DELETE_SQL="delete from MasterAchievementPatent where studentNo=? and patentId=? ";
    private final String MasterAchievementPatent_UPDATE_SQL="update MasterAchievementPatent set studentNo=?,patentId=? where studentNo=? and patentId=?";
    private final String MasterAchievementPatent_SELECT_SQL="select * from MasterAchievementPatent where studentNo=? and patentId=?";
    private static final String searchSQL = "select studentNo,patentId from MasterAchievementPatent where ";

    @Override
    public void addMasterAchievementPatent(MasterAchievementPatent masterAchievementPatent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPatent_INSERT_SQL);
            p.setString(1,masterAchievementPatent.getGraduate().getStudentNo());
            p.setString(2,masterAchievementPatent.getPatent().getPatentId());

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
    public void updateMasterAchievementPatent(MasterAchievementPatent masterAchievementPatent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPatent_UPDATE_SQL);
            p.setString(1,masterAchievementPatent.getGraduate().getStudentNo());
            p.setString(2,masterAchievementPatent.getPatent().getPatentId());
            p.setString(3,masterAchievementPatent.getGraduate().getStudentNo());
            p.setString(4,masterAchievementPatent.getPatent().getPatentId());

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
    public void deleteMasterAchievementPatent(Graduate graduate, Patent patent) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPatent_DELETE_SQL);
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
    public MasterAchievementPatent getMasterAchievementPatent(Graduate graduate, Patent patent) {
        MasterAchievementPatent masterAchievementPatent=new MasterAchievementPatent();
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPatent_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,patent.getPatentId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Patent patent1=new Patent();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                masterAchievementPatent.setGraduate(graduate1);
                patent1.setPatentId(resultSet.getString("patentId"));
                masterAchievementPatent.setPatent(patent1);
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
        return masterAchievementPatent;
    }

    @Override
    public List<MasterAchievementPatent> findMasterAchievementPatents(MasterAchievementPatent masterAchievementPatent) {
        List<MasterAchievementPatent> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(masterAchievementPatent.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(masterAchievementPatent.getGraduate().getStudentNo()) + "%' and ");
        }
        if(masterAchievementPatent.getPatent().getPatentId()!= null){
            sql.append("patentId like '%" + DBUtil.fixSqlFieldValue(masterAchievementPatent.getPatent().getPatentId()) + "%' and ");
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
                MasterAchievementPatent mas = new MasterAchievementPatent();
                Graduate gra1=new Graduate();
                Patent pat=new Patent();

                gra1.setStudentNo(rs.getString("studentNo"));
                mas.setGraduate(gra1);
                pat.setPatentId(rs.getString("patentId"));
                mas.setPatent(pat);

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
