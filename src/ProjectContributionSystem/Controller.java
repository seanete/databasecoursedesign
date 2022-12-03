package ProjectContributionSystem;

import DAO.Implement.DAObase;
import Login.Login;

import java.sql.*;
import java.util.Scanner;

//项目贡献子系统的Controller部分  用于接收用户输入的指令和数据
public class Controller {
    //下面的数表示当前登录者身份。0表示未登录,1表示研究生培养管理员，2为学科负责人，3为授课教师，4为导师，5为研究生
    protected static int currentLogonIdentity = 0;
    //获取当前登录者账号
    protected static String currentLogonUsername = null;


    public static void start(){
        //获取当前登录者身份
        Controller.currentLogonIdentity = Login.currentLogonIdentity;
        //获取当前登录者账号
        Controller.currentLogonUsername = Login.currentLogonUsername;
        if(currentLogonIdentity == 4){ //导师
            mentor();
        }else if(currentLogonIdentity == 2){ //学科负责人
            subjectLeader();
        }else{ //研究生培养管理员
            graduateTrainingAdministrator();
        }
    }

    //导师
    protected static void mentor(){
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.print("[1]查看自己主持的所有项目\n[2]查看自己带的研究生\n[3]为研究生填写项目参与表\n" +
                "[4]查看自己填过的研究生项目参与表\n[其他]返回上一级菜单\n请选择操作:");
        select = sc.nextInt();
        switch(select){
            case 1: //查看自己主持的所有项目
                View.mentorProjectView1();
                break;
            case 2: //查看自己带的研究生
                View.mentorProjectView2();
                break;
            case 3: //为研究生填写项目参与表
                View.mentorProjectView3();
                break;
            case 4: //导师查看自己填过的研究生项目参与表
                View.mentorProjectView4();
                break;
            default: //返回上一级菜单
                break;
        }
    }

    //学科负责人
    protected static void subjectLeader(){
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.print("[1]查看自己学科下的导师\n[2]查看自己学科下的研究生\n[3]查看自己学科下的项目\n" +
                "[4]为研究生项目参与表签字\n[5]查看自己填过的研究生项目参与表\n[其他]返回上一级菜单\n请选择操作:");
        select = sc.nextInt();
        switch(select){
            case 1: //查看自己学科下的导师
                View.headerProjectView1();
                break;
            case 2: //查看自己学科下的研究生
                View.headerProjectView2();
                break;
            case 3: //查看自己学科下的项目
                View.headerProjectView3();
                break;
            case 4: //为研究生项目参与表签字
                View.headerProjectView4();
                break;
            case 5: //查看自己填过的研究生项目参与表
                View.headerProjectView5();
                break;
            default: //返回上一级菜单
                break;
        }
    }

    //研究生培养管理员
    protected static void graduateTrainingAdministrator(){
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.print("[1]查看所有项目\n[2]增加新项目\n请选择操作:");
        select = sc.nextInt();
        switch(select){
            case 1: //查看所有项目
                View.administratorProjectView1();
                break;
            case 2: //增加新项目
                View.administratorProjectView2();
                break;
            default: //返回上一级菜单
                break;
        }

    }
}
