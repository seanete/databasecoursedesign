package Test;

import DAO.Entity.Course;
import DAO.Entity.Graduate;
import DAO.Factory.DAOFactory;
import Login.Login;
import Utils.DruidUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//测试模块
public class Test {
    public static void main(String[] args) {
     /*   Course course=new Course();
        course.setProperties("必修");
        List course1 =DAOFactory.getInstance().getCourseDAO().findCourses(course);
        System.out.println(course1.toString());*/

        Login l=new Login();
        try{
            l.login();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
