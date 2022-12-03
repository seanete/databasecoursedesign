package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Paper;
import DAO.Entity.MasterAchievementPaper;
import DAO.Interface.MasterAchievementPaperDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterAchievementPaperDAOImpl extends DAObase implements MasterAchievementPaperDAO {
    private final String MasterAchievementPaper_INSERT_SQL="insert into MasterAchievementPaper(studentNo,mentorNo) values(?,?)";
    private final String MasterAchievementPaper_DELETE_SQL="delete from MasterAchievementPaper where studentNo=? and paperId=? ";
    private final String MasterAchievementPaper_UPDATE_SQL="update MasterAchievementPaper set studentNo=?,paperId=? where studentNo=? and paperId=?";
    private final String MasterAchievementPaper_SELECT_SQL="select * from MasterAchievementPaper where studentNo=? and paperId=?";
    private static final String searchSQL = "select studentNo,paperId from MasterAchievementPaper where ";

    @Override
    public void addMasterAchievementPaper(MasterAchievementPaper masterAchievementPaper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPaper_INSERT_SQL);
            p.setString(1,masterAchievementPaper.getGraduate().getStudentNo());
            p.setString(2,masterAchievementPaper.getPaper().getPaperId());

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
    public void updateMasterAchievementPaper(MasterAchievementPaper masterAchievementPaper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPaper_UPDATE_SQL);
            p.setString(1,masterAchievementPaper.getGraduate().getStudentNo());
            p.setString(2,masterAchievementPaper.getPaper().getPaperId());
            p.setString(3,masterAchievementPaper.getGraduate().getStudentNo());
            p.setString(4,masterAchievementPaper.getPaper().getPaperId());

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
    public void deleteMasterAchievementPaper(Graduate graduate, Paper paper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPaper_DELETE_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,paper.getPaperId());

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
    public MasterAchievementPaper getMasterAchievementPaper(Graduate graduate, Paper paper) {
        MasterAchievementPaper masterAchievementPaper=new MasterAchievementPaper();
        try{
            PreparedStatement p=getConnection().prepareStatement(MasterAchievementPaper_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,paper.getPaperId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Paper paper1=new Paper();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                masterAchievementPaper.setGraduate(graduate1);
                paper1.setPaperId(resultSet.getString("paperId"));
                masterAchievementPaper.setPaper(paper1);
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
        return masterAchievementPaper;
    }

    @Override
    public List<MasterAchievementPaper> findMasterAchievementPapers(MasterAchievementPaper masterAchievementPaper) {
        List<MasterAchievementPaper> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(masterAchievementPaper.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(masterAchievementPaper.getGraduate().getStudentNo()) + "%' and ");
        }
        if(masterAchievementPaper.getPaper().getPaperId()!= null){
            sql.append("paperId like '%" + DBUtil.fixSqlFieldValue(masterAchievementPaper.getPaper().getPaperId()) + "%' and ");
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
                MasterAchievementPaper mas = new MasterAchievementPaper();
                Graduate gra1=new Graduate();
                Paper pap=new Paper();

                gra1.setStudentNo(rs.getString("studentNo"));
                mas.setGraduate(gra1);
                pap.setPaperId(rs.getString("paperId"));
                mas.setPaper(pap);

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
