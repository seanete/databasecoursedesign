package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Standard;
import DAO.Entity.MasterAchievementStandard;
import DAO.Interface.MasterAchievementStandardDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterAchievementStandardDAOImpl extends DAObase implements MasterAchievementStandardDAO {
    private final String MasterAchievementStandard_INSERT_SQL="insert into MasterAchievementStandard(studentNo,standardId) values(?,?)";
    private final String MasterAchievementStandard_DELETE_SQL="delete from MasterAchievementStandard where studentNo=? and standardId=? ";
    private final String MasterAchievementStandard_UPDATE_SQL="update MasterAchievementStandard set studentNo=?,standardId=? where studentNo=? and standardId=?";
    private final String MasterAchievementStandard_SELECT_SQL="select * from MasterAchievementStandard where studentNo=? and standardId=?";
    private static final String searchSQL = "select studentNo,standardId from MasterAchievementStandard where ";

    @Override
    public void addMasterAchievementStandard(MasterAchievementStandard masterAchievementStandard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementStandard_INSERT_SQL);
            p.setString(1,masterAchievementStandard.getGraduate().getStudentNo());
            p.setString(2,masterAchievementStandard.getStandard().getStandardId());

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
    public void updateMasterAchievementStandard(MasterAchievementStandard masterAchievementStandard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementStandard_UPDATE_SQL);
            p.setString(1,masterAchievementStandard.getGraduate().getStudentNo());
            p.setString(2,masterAchievementStandard.getStandard().getStandardId());
            p.setString(3,masterAchievementStandard.getGraduate().getStudentNo());
            p.setString(4,masterAchievementStandard.getStandard().getStandardId());

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
    public void deleteMasterAchievementStandard(Graduate graduate, Standard standard) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementStandard_DELETE_SQL);
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
    public MasterAchievementStandard getMasterAchievementStandard(Graduate graduate, Standard standard) {
        MasterAchievementStandard masterAchievementStandard=new MasterAchievementStandard();
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementStandard_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,standard.getStandardId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Standard standard1=new Standard();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                masterAchievementStandard.setGraduate(graduate1);
                standard1.setStandardId(resultSet.getString("standardId"));
                masterAchievementStandard.setStandard(standard1);
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
        return masterAchievementStandard;
    }

    @Override
    public List<MasterAchievementStandard> findMasterAchievementStandards(MasterAchievementStandard masterAchievementStandard) {
        List<MasterAchievementStandard> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(masterAchievementStandard.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(masterAchievementStandard.getGraduate().getStudentNo()) + "%' and ");
        }
        if(masterAchievementStandard.getStandard().getStandardId()!= null){
            sql.append("standardId like '%" + DBUtil.fixSqlFieldValue(masterAchievementStandard.getStandard().getStandardId()) + "%' and ");
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
                MasterAchievementStandard mas = new MasterAchievementStandard();
                Graduate gra1=new Graduate();
                Standard sta=new Standard();

                gra1.setStudentNo(rs.getString("studentNo"));
                mas.setGraduate(gra1);
                sta.setStandardId(rs.getString("standardId"));
                mas.setStandard(sta);

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
