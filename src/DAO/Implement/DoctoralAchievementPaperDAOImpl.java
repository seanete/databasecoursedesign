package DAO.Implement;

import DAO.Entity.Graduate;
import DAO.Entity.Paper;
import DAO.Entity.DoctoralAchievementPaper;
import DAO.Interface.DoctoralAchievementPaperDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.lang.management.MemoryNotificationInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctoralAchievementPaperDAOImpl extends DAObase implements DoctoralAchievementPaperDAO {
    private final String DoctoralAchievementPaper_INSERT_SQL="insert into DoctoralAchievementPaper(studentNo,paperId) values(?,?)";
    private final String DoctoralAchievementPaper_DELETE_SQL="delete from DoctoralAchievementPaper where studentNo=? and paperId=? ";
    private final String DoctoralAchievementPaper_UPDATE_SQL="update DoctoralAchievementPaper set studentNo=?,paperId=? where studentNo=? and paperId=?";
    private final String DoctoralAchievementPaper_SELECT_SQL="select * from DoctoralAchievementPaper where studentNo=? and paperId=?";
    private static final String searchSQL = "select studentNo,paperId from DoctoralAchievementPaper where ";

    @Override
    public void addDoctoralAchievementPaper(DoctoralAchievementPaper doctoralAchievementPaper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPaper_INSERT_SQL);
            p.setString(1,doctoralAchievementPaper.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementPaper.getPaper().getPaperId());

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
    public void updateDoctoralAchievementPaper(DoctoralAchievementPaper doctoralAchievementPaper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPaper_UPDATE_SQL);
            p.setString(1,doctoralAchievementPaper.getGraduate().getStudentNo());
            p.setString(2,doctoralAchievementPaper.getPaper().getPaperId());
            p.setString(3,doctoralAchievementPaper.getGraduate().getStudentNo());
            p.setString(4,doctoralAchievementPaper.getPaper().getPaperId());

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
    public void deleteDoctoralAchievementPaper(Graduate graduate, Paper paper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPaper_DELETE_SQL);
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
    public DoctoralAchievementPaper getDoctoralAchievementPaper(Graduate graduate, Paper paper) {
        DoctoralAchievementPaper doctoralAchievementPaper=new DoctoralAchievementPaper();
        try{
            PreparedStatement p=getConnection().prepareStatement(DoctoralAchievementPaper_SELECT_SQL);
            p.setString(1,graduate.getStudentNo());
            p.setString(2,paper.getPaperId());

            ResultSet resultSet=p.executeQuery();
            Graduate graduate1=new Graduate();
            Paper paper1=new Paper();
            while(resultSet.next()){
                graduate1.setStudentNo(resultSet.getString("studentNo"));
                doctoralAchievementPaper.setGraduate(graduate1);
                paper1.setPaperId(resultSet.getString("paperId"));
                doctoralAchievementPaper.setPaper(paper1);
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
        return doctoralAchievementPaper;
    }

    @Override
    public List<DoctoralAchievementPaper> findDoctoralAchievementPapers(DoctoralAchievementPaper doctoralAchievementPaper) {
        List<DoctoralAchievementPaper> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(doctoralAchievementPaper.getGraduate().getStudentNo() != null){
            sql.append("studentNo like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementPaper.getGraduate().getStudentNo()) + "%' and ");
        }
        if(doctoralAchievementPaper.getPaper().getPaperId()!= null){
            sql.append("paperId like '%" + DBUtil.fixSqlFieldValue(doctoralAchievementPaper.getPaper().getPaperId()) + "%' and ");
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
                DoctoralAchievementPaper doc = new DoctoralAchievementPaper();
                Graduate gra1=new Graduate();
                Paper pap=new Paper();

                gra1.setStudentNo(rs.getString("studentNo"));
                doc.setGraduate(gra1);
                pap.setPaperId(rs.getString("paperId"));
                doc.setPaper(pap);

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
