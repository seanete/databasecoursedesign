package DAO.Implement;
import DAO.Entity.*;
import DAO.Interface.teacherAssitantViewDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class teacherAssitantViewDAOImpl extends DAObase implements teacherAssitantViewDAO{
    private final String SELECT_SQL="select studentNo from teacherAssitantView where courseNo1=? or courseNo2=?";
    @Override
    public List<teacherAssitantView> getTeacherAssitantView(String courseNo) {
        List<teacherAssitantView> list = new ArrayList<>();
        try{
            PreparedStatement p=getConnection().prepareStatement(SELECT_SQL);
            p.setString(1,courseNo);
            p.setString(2,courseNo);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                teacherAssitantView teacherAssitantView=new teacherAssitantView();
                Graduate graduate=new Graduate();
                graduate.setStudentNo(resultSet.getString("studentNo"));
                teacherAssitantView.setGraduate(graduate);
                list.add(teacherAssitantView);
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
