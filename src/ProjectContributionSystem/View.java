package ProjectContributionSystem;

import DAO.Entity.*;
import DAO.Factory.DAOFactory;
import DAO.Implement.DAObase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    //导师查看自己主持的所有项目
    protected static void mentorProjectView1(){
        String sql = "select 项目编号,项目类型,项目名称 from mentorProjectView1 where 主持导师 = "
                + Controller.currentLogonUsername;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        List<Project> searchProjectByMentor = new ArrayList<>();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                Project project = new Project();
                project.setProjectIndex(resultSet.getString(1));
                project.setProjectType(resultSet.getString(2));
                project.setProjectName(resultSet.getString(3));
                searchProjectByMentor.add(project);
            }
            //输出
            if(searchProjectByMentor.size()==0){
                System.out.println("您没有主持的项目!");
            }else{
                System.out.println("您主持的所有项目如下：");
                System.out.println("项目编号\t项目类型\t项目名称");
                for(Project pro : searchProjectByMentor){
                    System.out.println(pro.getProjectIndex() + "\t" + pro.getProjectType() +
                            "\t" + pro.getProjectName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    //导师查看自己带的研究生
    protected static void mentorProjectView2(){
        String sql = "select 学号,姓名,研究生类型 from mentorProjectView2 where 导师号 = "
                + Controller.currentLogonUsername;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        List<Graduate> searchGraduateByMentor = new ArrayList<>();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                Graduate graduate = new Graduate();
                graduate.setStudentNo(resultSet.getString(1));
                graduate.setStudentName(resultSet.getString(2));
                graduate.setType(resultSet.getString(3));
                searchGraduateByMentor.add(graduate);
            }
            //输出
            if(searchGraduateByMentor.size()==0){
                System.out.println("您没有带研究生!");
            }else{
                System.out.println("您带的研究生如下：");
                System.out.println("学号\t姓名\t研究生类型");
                for(Graduate gra : searchGraduateByMentor){
                    System.out.println(gra.getStudentNo() + "\t" + gra.getStudentName() +
                            "\t" + gra.getType());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    //导师为研究生填写项目参与表
    protected static void mentorProjectView3(){
        Scanner sc = new Scanner(System.in);
        System.out.print("输入学号:");
        String studentNo = sc.next();
        //判断学号是否合法
        if(judgeStudentNo(studentNo)){
            System.out.print("输入项目编号:");
            String projectIndex = sc.next();
            //判断项目编号是否合法
            if(judgeProjectIndex(projectIndex)){
                System.out.print("输入参与时间(xxxx-xx-xx):");
                String projectTime = sc.next();
                System.out.print("输入承担的工作:");
                String takeOnWork = sc.next();
                System.out.print("输入折合经费:");
                double budget = sc.nextDouble();
                System.out.print("输入导师签字(导师号):");
                String mentorNo = sc.next();
                //检查导师号输入是否正确
                if(!Controller.currentLogonUsername.equals(mentorNo)){
                    System.out.println("导师签字(导师号)输入有误!");
                }else{ //所有的输入都正确，可以插入数据库
                    Graduate graduate = DAOFactory.getInstance().getGraduateDAO().getGraduate(studentNo);
                    Project project = DAOFactory.getInstance().getProjectDAO().getProject(projectIndex);
                    GraduateParticipateProject graduateParticipateProject = new GraduateParticipateProject();
                    graduateParticipateProject.setGraduate(graduate);
                    graduateParticipateProject.setProject(project);
                    graduateParticipateProject.setParticipateTime(projectTime);
                    graduateParticipateProject.setTakeOnWork(takeOnWork);
                    graduateParticipateProject.setBudget(String.valueOf(budget));
                    graduateParticipateProject.setMentorSign(mentorNo);
                    DAOFactory.getInstance().getGraduateParticipateProjectDAO().
                            addGraduateParticipateProject(graduateParticipateProject);
                    System.out.println("新纪录增加成功!");
                }

            }else{
                System.out.println("项目编号不存在!");
            }
        }else{
            System.out.println("学号不存在!");
        }
        //返回上一级菜单
        Controller.start();
    }

    //导师查看自己填过的研究生项目参与表
    protected static void mentorProjectView4(){
        String sql = "select 学号,项目编号,参与时间,承担工作,折合经费 from mentorProjectView4 where 导师签字 = "
                + Controller.currentLogonUsername;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();

        List<String> studentNo = new ArrayList<>();
        List<String> projectIndex = new ArrayList<>();
        List<String> projectTime = new ArrayList<>();
        List<String> takeOnWork = new ArrayList<>();
        List<String> budget = new ArrayList<>();

        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                String studentno = resultSet.getString(1);
                String projectindex = resultSet.getString(2);
                String projecttime = resultSet.getString(3);
                String takeonwork = resultSet.getString(4);
                String bud = resultSet.getString(5);
                studentNo.add(studentno);
                projectIndex.add(projectindex);
                projectTime.add(projecttime);
                takeOnWork.add(takeonwork);
                budget.add(bud);
            }
            //输出
            if(studentNo.size()==0){
                System.out.println("您还没有录入研究生参与项目的记录!");
            }else{
                System.out.println("您录入研究生参与项目的记录如下:");
                System.out.println("学号\t项目编号\t参与时间\t承担工作\t折合经费");
                for(int i = 0;i < studentNo.size();i++){
                    System.out.println(studentNo.get(i) + "\t" + projectIndex.get(i) + "\t" +
                            projectTime.get(i) + "\t" + takeOnWork.get(i) + "\t" + budget.get(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    //学科负责人查看自己学科下的导师
    protected static void headerProjectView1(){
        SubjectHeader subjectHeader = DAOFactory.getInstance().getSubjectHeaderDAO().
                getSubjectHeader(Controller.currentLogonUsername);

        String sql = "select 导师号,导师姓名 from headerProjectView1 where 学科号 = " +
                subjectHeader.getSubject().getSubjectNo();
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        List<Mentor> mentors = new ArrayList<>();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                Mentor mentor = new Mentor();
                mentor.setMentorNo(resultSet.getString(1));
                mentor.setMentorName(resultSet.getString(2));
                mentors.add(mentor);
            }
            //输出
            if(mentors.size()==0){
                System.out.println("您学科下没有导师!");
            }else{
                System.out.println("您学科下的导师如下：");
                System.out.println("导师号\t导师姓名");
                for(Mentor me : mentors){
                    System.out.println(me.getMentorNo() + "\t" + me.getMentorName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    //学科负责人查看自己学科下的研究生
    protected static void headerProjectView2(){
        SubjectHeader subjectHeader = DAOFactory.getInstance().getSubjectHeaderDAO().
                getSubjectHeader(Controller.currentLogonUsername);
        String sql = "select 学号,姓名,导师号,研究生类型 from headerProjectView2 where 学科号 = "
                + subjectHeader.getSubject().getSubjectNo();
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        List<Graduate> graduates = new ArrayList<>();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                Graduate graduate = new Graduate();
                graduate.setStudentNo(resultSet.getString(1));
                graduate.setStudentName(resultSet.getString(2));
                Mentor mentor = new Mentor();
                mentor.setMentorNo(resultSet.getString(3));
                graduate.setMentor(mentor);
                graduate.setType(resultSet.getString(4));
                graduates.add(graduate);
            }
            //输出
            if(graduates.size()==0){
                System.out.println("您学科下没有研究生!");
            }else{
                System.out.println("您学科下的研究生如下：");
                System.out.println("学号\t姓名\t导师号\t类型");
                for(Graduate gra : graduates){
                    System.out.println(gra.getStudentNo() + "\t" + gra.getStudentName() + "\t" +
                            gra.getMentor().getMentorNo() + "\t" + gra.getType());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    //学科负责人查看自己学科下的项目
    protected static void headerProjectView3(){
        SubjectHeader subjectHeader = DAOFactory.getInstance().getSubjectHeaderDAO().
                getSubjectHeader(Controller.currentLogonUsername);
        String sql = "select 项目编号,项目类型,项目名称,主持导师 from headerProjectView3 where 学科号 = " +
                subjectHeader.getSubject().getSubjectNo();
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        List<Project> projects = new ArrayList<>();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                Project project = new Project();
                project.setProjectIndex(resultSet.getString(1));
                project.setProjectType(resultSet.getString(2));
                project.setProjectName(resultSet.getString(3));
                Mentor mentor = new Mentor();
                mentor.setMentorNo(resultSet.getString(4));
                project.setMentor(mentor);
                projects.add(project);
            }
            //输出
            if(projects.size()==0){
                System.out.println("您学科下没有项目!");
            }else{
                System.out.println("您学科下的项目如下：");
                System.out.println("项目编号\t项目类型\t项目名称\t主持导师");
                for(Project pro : projects){
                    System.out.println(pro.getProjectIndex() + "\t" + pro.getProjectType() + "\t" +
                            pro.getProjectName() + "\t" + pro.getMentor().getMentorNo());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    //学科负责人为研究生项目参与表签字
    //只有导师签过字之后学科负责人才可以签字
    protected static void headerProjectView4(){
        Scanner sc = new Scanner(System.in);
        System.out.print("输入学科负责人工号:");
        String headerNo = sc.next();
        if(!headerNo.equals(Controller.currentLogonUsername)){
            System.out.println("学科负责人签字输入有误!");
        }else{
            System.out.print("输入要签字的记录的学号:");
            String studentNo = sc.next();
            System.out.print("输入要签字的记录的项目编号:");
            String projectNo = sc.next();
            String sql = "select * from headerProjectView4 where 学号 = " + studentNo +
                    " and 项目编号 = " + projectNo + " and 导师签字 is not null";
            PreparedStatement p = null;
            ResultSet resultSet = null;
            Connection conn = new DAObase().getConnection();
            try {
                p=conn.prepareStatement(sql);
                resultSet=p.executeQuery();
                if(resultSet.next()){ //结果集不为空
                    sql = "update GraduateParticipateProject set hostSign=? where studentNo=? and projectIndex=?";
                    p=conn.prepareStatement(sql);
                    p.setString(1,headerNo);
                    p.setString(2,studentNo);
                    p.setString(3,projectNo);
                    p.executeUpdate();
                    System.out.println("签字成功!");
                }else{
                    System.out.println("没有找到学号为" + studentNo + ",项目编号为" + projectNo + "且导师已签字的记录!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    //学科负责人查看自己填过的研究生项目参与表
    //只能查看导师和学科负责人都签过字的记录
    protected static void headerProjectView5(){
        String sql = "select 学号,项目编号,参与时间,承担工作,折合经费,导师签字,学科负责人签字 from headerProjectView5 where 学科负责人签字 = "
                + Controller.currentLogonUsername;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();

        List<String> studentNo = new ArrayList<>();
        List<String> projectIndex = new ArrayList<>();
        List<String> projectTime = new ArrayList<>();
        List<String> takeOnWork = new ArrayList<>();
        List<String> budget = new ArrayList<>();
        List<String> mentorSign = new ArrayList<>();
        List<String> hostSign = new ArrayList<>();

        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                String studentno = resultSet.getString(1);
                String projectindex = resultSet.getString(2);
                String projecttime = resultSet.getString(3);
                String takeonwork = resultSet.getString(4);
                String bud = resultSet.getString(5);
                String mentorsign = resultSet.getString(6);
                String hostsign = resultSet.getString(7);
                studentNo.add(studentno);
                projectIndex.add(projectindex);
                projectTime.add(projecttime);
                takeOnWork.add(takeonwork);
                budget.add(bud);
                mentorSign.add(mentorsign);
                hostSign.add(hostsign);
            }
            //输出
            if(studentNo.size()==0){
                System.out.println("暂无已签字的记录!");
            }else{
                System.out.println("您录入研究生参与项目的记录如下:");
                System.out.println("学号\t项目编号\t参与时间\t承担工作\t折合经费\t导师签字\t学科负责人签字");
                for(int i = 0;i < studentNo.size();i++){
                    System.out.println(studentNo.get(i) + "\t" + projectIndex.get(i) + "\t" +
                            projectTime.get(i) + "\t" + takeOnWork.get(i) + "\t" + budget.get(i) +
                            "\t" + mentorSign.get(i) + "\t" + hostSign.get(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }


    //管理员查看系统中的所有项目
    protected static void administratorProjectView1(){
        String sql = "select 项目编号,项目类型,项目名称,所属学科号,主持导师号 from administratorProject1";
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        List<Project> projects = new ArrayList<>();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            while(resultSet.next()){
                Project project = new Project();
                project.setProjectIndex(resultSet.getString(1));
                project.setProjectType(resultSet.getString(2));
                project.setProjectName(resultSet.getString(3));
                Subject subject = new Subject();
                subject.setSubjectNo(resultSet.getString(4));
                project.setSubject(subject);
                Mentor mentor = new Mentor();
                mentor.setMentorNo(resultSet.getString(5));
                project.setMentor(mentor);
                projects.add(project);
            }
            //输出
            if(projects.size()==0){
                System.out.println("系统中还没有项目!");
            }else{
                System.out.println("系统中的项目如下：");
                System.out.println("项目编号\t项目类型\t项目名称\t所属学科号\t主持导师号");
                for(Project pro : projects){
                    System.out.println(pro.getProjectIndex() + "\t" + pro.getProjectType() +
                            "\t" + pro.getProjectName() + "\t" + pro.getSubject().getSubjectNo()
                     + "\t" + pro.getMentor().getMentorNo());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回上一级菜单
        Controller.start();
    }

    protected static void administratorProjectView2(){
        Scanner sc = new Scanner(System.in);
        System.out.print("输入项目编号:");
        String projectIndex = sc.next();
        System.out.print("输入项目类型:");
        String projectType = sc.next();
        System.out.print("输入项目名称:");
        String projectName = sc.next();
        System.out.print("输入所属学科号:");
        String subjectNo = sc.next();
        //判断学科号是否存在
        if(!judgeSubjectNo(subjectNo)){
            System.out.println("学科号不存在!");
        }else{
            System.out.print("输入主持导师号:");
            String mentorNo = sc.next();
            if(!judgeMentorNo(mentorNo)){
                System.out.println("导师号不存在!");
            }else{
                //开始插入
                Subject subject = new Subject();
                subject.setSubjectNo(subjectNo);
                Mentor mentor = new Mentor();
                mentor.setMentorNo(mentorNo);
                Project project = new Project(projectIndex , projectType , projectName , subject,mentor);
                DAOFactory.getInstance().getProjectDAO().addProject(project);
                System.out.println("新项目插入成功!");
            }
        }
        //返回上一级菜单
        Controller.start();
    }


    //判断输入的学号是否合法
    private static boolean judgeStudentNo(String studentNo){
        String sql = "select * from mentorProjectView2 where 导师号 = "
                + Controller.currentLogonUsername + " and 学号 = " + studentNo;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            boolean flag = resultSet.next();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //判断导师号是否合法
    private static boolean judgeMentorNo(String mentorNo){
        String sql = "select * from Mentor where mentorNo = " + mentorNo;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            boolean flag = resultSet.next();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    //判断项目编号是否合法
    private static boolean judgeProjectIndex(String projectIndex){
        String sql = "select * from mentorProjectView1 where 主持导师 = "
                + Controller.currentLogonUsername + " and 项目编号 = " + projectIndex;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            boolean flag = resultSet.next();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //判断学科号是否合法
    private static boolean judgeSubjectNo(String subjectNo){
        String sql = "select * from Subject where subjectNo = " + subjectNo;
        PreparedStatement p = null;
        ResultSet resultSet = null;
        Connection conn = new DAObase().getConnection();
        try {
            p=conn.prepareStatement(sql);
            resultSet=p.executeQuery();
            boolean flag = resultSet.next();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
