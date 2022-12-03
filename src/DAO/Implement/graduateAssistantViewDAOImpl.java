package DAO.Implement;
import DAO.Entity.*;
import DAO.Interface.graduateAssistantViewDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class graduateAssistantViewDAOImpl extends DAObase implements graduateAssistantViewDAO{

    private final String SELECT_SQL="select * from graduateAssistantView";
    public List<graduateAssistantView> getGraduateAssistantView() {
        List<graduateAssistantView> list = new ArrayList<>();
        try{
            int num=0;
            PreparedStatement p=getConnection().prepareStatement(SELECT_SQL);
            ResultSet resultSet=p.executeQuery();
            while(resultSet.next()){
                graduateAssistantView gra=new graduateAssistantView();
                Course course=new Course();
                TeacherTeaching teacherTeaching=new TeacherTeaching();
                Teacher teacher=new Teacher();
                course.setCourseNo(resultSet.getString("courseNo"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setCredit(resultSet.getString("credit"));
                course.setPriority(resultSet.getString("priority"));
                teacher.setTeacherName(resultSet.getString("teacherName"));
                teacher.setTeacherNo(resultSet.getString("teacherNo"));
                teacherTeaching.setTeachingNumber(resultSet.getString("teachingNumber"));
                gra.setCourse(course);
                gra.setTeacher(teacher);
                gra.setTeacherTeaching(teacherTeaching);
                int flag=0;
                for(int i=1;i<list.size();i++){
                    if(Integer.parseInt(gra.getCourse().getPriority())>Integer.parseInt(list.get(i).getCourse().getPriority())){
                        list.add(i,gra);
                        flag=1;
                        break;
                    }
                    else if(Integer.parseInt(gra.getTeacherTeaching().getTeachingNumber())>Integer.parseInt(list.get(i).getTeacherTeaching().getTeachingNumber())){
                        list.add(i,gra);
                        flag=1;
                        break;
                    }
                    else if(Integer.parseInt(gra.getTeacherTeaching().getTeachingNumber())==Integer.parseInt(list.get(i).getTeacherTeaching().getTeachingNumber())){
                        if(Integer.parseInt(gra.getCourse().getCredit())>Integer.parseInt(list.get(i).getCourse().getCredit())){
                            list.add(i,gra);
                            flag=1;
                            break;
                        }
                    }
                }
                if(flag==0&&num!=0){
                    list.add(gra);
                }
                if(num==0){
                    list.add(gra);
                    num++;
                }
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
