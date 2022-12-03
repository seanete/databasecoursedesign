package DAO.Implement;
import DAO.Entity.*;
import DAO.Interface.subjectHeaderViewDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class subjectHeaderViewDAOImpl extends DAObase implements subjectHeaderViewDAO{
    private final String SELECT_SQL="select courseNo,subjectNo,priority from subjectHeaderView where hostNo=?";
    @Override
    public List<subjectHeaderView> getSubjectHeaderView(String hostNo) {
        List<subjectHeaderView> list = new ArrayList<>();
        try{
            PreparedStatement p=getConnection().prepareStatement(SELECT_SQL);
            p.setString(1,hostNo);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                subjectHeaderView subjectHeaderView=new subjectHeaderView();
                Course course=new Course();
                Subject subject=new Subject();
                course.setCourseNo(resultSet.getString("courseNo"));
                subject.setSubjectNo(resultSet.getString("subjectNo"));
                subjectHeaderView.setCourse(course);
                subjectHeaderView.setPriority(resultSet.getString("priority"));
                subjectHeaderView.setSubject(subject);
                list.add(subjectHeaderView);
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
        //      Arrays.sort(gra.getTeacherTeaching().getTeachingNumber().toCharArray());
        return list;
    }
}
