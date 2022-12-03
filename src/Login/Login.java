package Login;

import DAO.Implement.DAObase;
import TeachingAssistantSystem.Controller.AdministratorController;
import TeachingAssistantSystem.Controller.GraduateController;
import TeachingAssistantSystem.Controller.SubjectController;
import TeachingAssistantSystem.Controller.TeacherController;
import TeachingAssistantSystem.View.GraduateView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//登录模块
public class Login {

    //数据库查询语句
    private static final String findInGraduateTrainingAdministratorLogin =
            "select password from GraduateTrainingAdministratorLogin where administratorNo = ?";
    private static final String findInSubjectHeaderLogin =
            "select password from SubjectHeaderLogin where headerNo = ?";
    private static final String findInTeacherLogin =
            "select password from TeacherLogin where teacherNo = ?";
    private static final String findInMentorLogin =
            "select password from MentorLogin where mentorNo = ?";
    private static final String findInGraduateLogin =
            "select password from GraduateLogin where studentNo = ?";

    //下面的数表示当前登录者身份。0表示未登录,1表示研究生培养管理员，2为学科负责人，3为授课教师，4为导师，5为研究生
    public static int currentLogonIdentity = 0;
    //下面的字段表示当前登录者的账号，分别是各自的学号(工号)
    public static String currentLogonUsername = null;


    public static void login() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true){
            int selectIdentify = 0;
            System.out.print("[1]研究生培养管理员\n[2]学科负责人\n[3]授课教师\n[4]导师\n[5]研究生\n[6]退出系统\n请选择身份:");
            selectIdentify = sc.nextInt();
            if(selectIdentify == 6)
                System.exit(0);
            System.out.print("请输入账户名:");
            String username = sc.next();
            System.out.print("请输入密码: ");
            String password = sc.next();
            //检查用户名是否存在以及密码是否匹配
            findInDatabase(username , password,selectIdentify);
            if(currentLogonIdentity > 0)
                currentLogonUsername = username;
            System.out.println(currentLogonUsername);
            int select;
            if(currentLogonIdentity == 5){ //研究生
                System.out.print("[1]助教子系统\n[2]学术交流子系统\n[3]成果认定子系统\n请选择操作:");
                select = sc.nextInt();
                switch(select){
                    case 1: //进入助教子系统
                        GraduateController graduateController=new GraduateController(currentLogonUsername);
                        graduateController.SelectView();
                        break;
                    case 2: //进入学术交流子系统
                        break;
                    case 3: //进入成果认定子系统
                        break;
                    default:
                        System.out.println("输入错误!");
                }
            }else if(currentLogonIdentity == 4){//导师
                System.out.print("[1]学术交流子系统\n[2]项目贡献子系统\n[3]成果认定子系统\n请选择操作:");
                select = sc.nextInt();
                switch(select){
                    case 1: //进入学术交流子系统
                        break;
                    case 2: //进入项目贡献子系统
                        ProjectContributionSystem.Controller.start();
                        break;
                    case 3: //进入成果认定子系统
                        break;
                    default:
                        System.out.println("输入错误!");
                }
            }else if(currentLogonIdentity == 3){//授课教师
                System.out.print("[1]助教子系统\n请选择操作:");
                select = sc.nextInt();
                switch(select){
                    case 1: //进入助教子系统
                        TeacherController teacherController=new TeacherController(currentLogonUsername);
                        teacherController.SelectView();
                        break;
                    default:
                        System.out.println("输入错误!");
                }
            }else if(currentLogonIdentity == 2){//学科负责人
                System.out.print("[1]助教子系统\n[2]项目贡献子系统\n[3]学术交流子系统\n请选择操作:");
                select = sc.nextInt();
                switch(select){
                    case 1: //进入助教子系统
                        SubjectController subjectController=new SubjectController(currentLogonUsername);
                        subjectController.SelectView();
                        break;
                    case 2: //进入项目贡献子系统
                        ProjectContributionSystem.Controller.start();
                        break;
                    case 3: //进入学术交流子系统
                        break;
                    default:
                        System.out.println("输入错误!");
                }
            }else if(currentLogonIdentity == 1){ //研究生培养管理员
                System.out.print("[1]助教子系统\n[2]学术交流子系统\n[3]成果认定子系统\n[4]项目贡献子系统\n请选择操作:");
                select = sc.nextInt();
                switch(select){
                    case 1: //进入助教子系统
                        AdministratorController administratorController=new AdministratorController();
                        administratorController.SelectView();
                        break;
                    case 2: //进入学术交流子系统
                        break;
                    case 3: //进入成果认定子系统
                        break;
                    case 4: //进入项目贡献子系统
                        ProjectContributionSystem.Controller.start();
                        break;
                    default:
                        System.out.println("输入错误!");
                }
            }
        }
    }


    public static void findInDatabase(String username , String password,int selectIdentify) throws SQLException {

        PreparedStatement p = null;
        ResultSet resultSet = null;
        String password2 = null;//password2表示从数据库中查找到的密码
        Connection conn = new DAObase().getConnection();
        //下面的集合存放查找结果
        List<String> resultOfGraduateTrainingAdministratorLogin = new ArrayList<>();
        List<String> resultOfSubjectHeaderLogin = new ArrayList<>();
        List<String> resultOfTeacherLogin = new ArrayList<>();
        List<String> resultOfMentorLogin = new ArrayList<>();
        List<String> resultOfGraduateLogin = new ArrayList<>();


        //在GraduateTrainingAdministratorLogin中查找
        p=conn.prepareStatement(findInGraduateTrainingAdministratorLogin);
        p.setString(1,username);
        resultSet=p.executeQuery();
        while(resultSet.next()){
            resultOfGraduateTrainingAdministratorLogin.add(resultSet.getString(1));
        }

        p=conn.prepareStatement(findInSubjectHeaderLogin);
        p.setString(1,username);
        resultSet=p.executeQuery();
        while(resultSet.next()){
            resultOfSubjectHeaderLogin.add(resultSet.getString(1));
        }

        p=conn.prepareStatement(findInTeacherLogin);
        p.setString(1,username);
        resultSet=p.executeQuery();
        while(resultSet.next()){
            resultOfTeacherLogin.add(resultSet.getString(1));
        }

        p=conn.prepareStatement(findInMentorLogin);
        p.setString(1,username);
        resultSet=p.executeQuery();
        while(resultSet.next()){
            resultOfMentorLogin.add(resultSet.getString(1));
        }

        p=conn.prepareStatement(findInGraduateLogin);
        p.setString(1,username);
        resultSet=p.executeQuery();
        while(resultSet.next()){
            resultOfGraduateLogin.add(resultSet.getString(1));
        }
        conn.close();

        if(selectIdentify == 1){ //研究生培养管理员
            if(resultOfGraduateTrainingAdministratorLogin.size() > 0){
                password2 = resultOfGraduateTrainingAdministratorLogin.get(0);
                if(password.equals(password2)){
                    System.out.println("登录成功!你的身份是研究生培养管理员.");
                    currentLogonIdentity = 1;
                }else{
                    System.out.println("用户名或密码错误!");
                }
            }else{
                System.out.println("登录失败!");
                currentLogonIdentity = 0;
                currentLogonUsername = null;
            }
        }else if(selectIdentify == 2){
            if(resultOfSubjectHeaderLogin.size() > 0){
                password2 = resultOfSubjectHeaderLogin.get(0);
                if(password.equals(password2)){
                    System.out.println("登录成功!你的身份是学科负责人.");
                    currentLogonIdentity = 2;
                }else{
                    System.out.println("用户名或密码错误!");
                }
            }else{
                System.out.println("登录失败!");
                currentLogonIdentity = 0;
                currentLogonUsername = null;
            }
        }else if(selectIdentify == 3){
            if(resultOfTeacherLogin.size() > 0){
                password2 = resultOfTeacherLogin.get(0);
                if(password.equals(password2)){
                    System.out.println("登录成功!你的身份是授课教师.");
                    currentLogonIdentity = 3;
                }else{
                    System.out.println("用户名或密码错误!");
                }
            }else{
                System.out.println("登录失败!");
                currentLogonIdentity = 0;
                currentLogonUsername = null;
            }
        }else if(selectIdentify == 4){
            if(resultOfMentorLogin.size() >0){
                password2 = resultOfMentorLogin.get(0);
                if(password.equals(password2)){
                    System.out.println("登录成功!你的身份是导师.");
                    currentLogonIdentity = 4;
                }else{
                    System.out.println("用户名或密码错误!");
                }
            }else{
                System.out.println("登录失败!");
                currentLogonIdentity = 0;
                currentLogonUsername = null;
            }
        }else if(selectIdentify == 5){
            if(resultOfGraduateLogin.size() >0){
                password2 = resultOfGraduateLogin.get(0);
                if(password.equals(password2)){
                    System.out.println("登录成功!你的身份是研究生.");
                    currentLogonIdentity = 5;
                }else{
                    System.out.println("用户名或密码错误!");
                }
            }else{
                System.out.println("登录失败!");
                currentLogonIdentity = 0;
                currentLogonUsername = null;
            }
        }else{
            currentLogonIdentity = 0;
            currentLogonUsername = null;
            System.out.println("登录失败!");
        }
    }
}