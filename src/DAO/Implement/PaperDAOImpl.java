package DAO.Implement;

import DAO.Entity.*;
import DAO.Interface.PaperDAO;
import DAO.SearchCriteria.SearchCriteria;
import Utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperDAOImpl extends DAObase implements PaperDAO {
    private final String Paper_INSERT_SQL="insert into Paper(paperId,paperName,publicationName,paperStatus,publicationTime,indexType,attributionLibrary,pdf) values(?,?,?,?,?,?,?,?)";
    private final String Paper_DELETE_SQL="delete from Paper where paperId=?";
    private final String Paper_UPDATE_SQL="update Paper set PaperId=?,paperName=?,publicationName=?,paperStatus=?,publicationTime=?,indexType=?,attributionLibrary=?,pdf=? where paperId=?";
    private final String Paper_SELECT_SQL="select * from Paper where paperId=?";
    private static final String searchSQL = "select paperId,paperName,publicationName,paperStatus,publicationTime,indexType,attributionLibrary,pdf from Paper where ";

    @Override
    public void addPaper(Paper paper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Paper_INSERT_SQL);
            p.setString(1,paper.getPaperId());
            p.setString(2,paper.getPaperName());
            p.setString(3,paper.getPublicationName());
            p.setString(4,paper.getPaperStatus());
            p.setString(5,paper.getPublicationTime());
            p.setString(6,paper.getIndexType());
            p.setString(7,paper.getAttributionLibrary());
            p.setString(8,paper.getPdf());

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
    public void updatePaper(Paper paper) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Paper_UPDATE_SQL);
            p.setString(1,paper.getPaperId());
            p.setString(2,paper.getPaperName());
            p.setString(3,paper.getPublicationName());
            p.setString(4,paper.getPaperStatus());
            p.setString(5,paper.getPublicationTime());
            p.setString(6,paper.getIndexType());
            p.setString(7,paper.getAttributionLibrary());
            p.setString(8,paper.getPdf());
            p.setString(9,paper.getPaperId());

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
    public void deletePaper(String paperId) {
        try{
            PreparedStatement p=getConnection().prepareStatement(Paper_DELETE_SQL);
            p.setString(1,paperId);

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
    public Paper getPaper(String paperId) {
        Paper paper=new Paper();
        try{
            PreparedStatement p=getConnection().prepareStatement(Paper_SELECT_SQL);
            p.setString(1,paperId);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                paper.setPaperId(resultSet.getString("paperId"));
                paper.setPaperName(resultSet.getString("paperName"));
                paper.setPublicationName(resultSet.getString("publicationName"));
                paper.setPaperStatus(resultSet.getString("paperStatus"));
                paper.setPublicationTime(resultSet.getString("publicationTime"));
                paper.setIndexType(resultSet.getString("indexType"));
                paper.setAttributionLibrary(resultSet.getString("attributionLibrary"));
                paper.setPdf(resultSet.getString("pdf"));
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
        return paper;
    }

    @Override
    public List<Paper> findPapers(Paper paper) {
        List<Paper> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(searchSQL);
        if(paper.getPaperId()!= null){
            sql.append("paperId like '%" + DBUtil.fixSqlFieldValue(paper.getPaperId()) + "%' and ");
        }
        if(paper.getPaperName()!= null){
            sql.append("paperName like '%" + DBUtil.fixSqlFieldValue(paper.getPaperName()) + "%' and ");
        }
        if(paper.getPublicationName() != null){
            sql.append("publicationName like '%" + DBUtil.fixSqlFieldValue(paper.getPublicationName()) + "%' and ");
        }
        if(paper.getPaperStatus()!= null){
            sql.append("paperStatus like '%" + DBUtil.fixSqlFieldValue(paper.getPaperStatus()) + "%' and ");
        }
        if(paper.getPublicationTime()!= null){
            sql.append("publicationTime like '%" + DBUtil.fixSqlFieldValue(paper.getPublicationTime()) + "%' and ");
        }
        if(paper.getIndexType()!= null){
            sql.append("indexType like '%" + DBUtil.fixSqlFieldValue(paper.getIndexType()) + "%' and ");
        }
        if(paper.getAttributionLibrary()!= null){
            sql.append("attributionLibrary like '%" + DBUtil.fixSqlFieldValue(paper.getAttributionLibrary()) + "%' and ");
        }
        if(paper.getPdf()!= null){
            sql.append("pdf like '%" + DBUtil.fixSqlFieldValue(paper.getPdf()) + "%' and ");
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
                Paper pap = new Paper();

                pap.setPaperId(rs.getString("paperId"));
                pap.setPaperName(rs.getString("paperName"));
                pap.setPublicationName(rs.getString("publicationName"));
                pap.setPaperStatus(rs.getString("paperStatus"));
                pap.setPublicationTime(rs.getString("publicationTime"));
                pap.setIndexType(rs.getString("indexType"));
                pap.setAttributionLibrary(rs.getString("attributionLibrary"));
                pap.setPdf(rs.getString("pdf"));

                result.add(pap);
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
