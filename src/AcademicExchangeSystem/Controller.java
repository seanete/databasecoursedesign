package AcademicExchangeSystem;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.Entity.Graduate;
import DAO.Entity.GraduateParticipateActivitiy;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Controller {

    // 获取活动ID
    static String getActivityID(Scanner sc){
        System.out.println("请输入活动ID：");
        return sc.next();
    }

    // 获取一整项活动数据
    static AcademicExchangeActivitiy getActivityFull(Scanner sc){
        String[] insVal = new String[5];
        System.out.println("若为空值，请输入：'NULL'");
        System.out.println("请输入活动ID：");
        insVal[1] = sc.next();
        System.out.println("请输入活动名称：");
        insVal[2] = sc.next();
        System.out.println("请输入活动日期：");
        insVal[3] = sc.next();
        System.out.println("请输入活动地点：");
        insVal[4] = sc.next();
        for (int i=1;i<5;i++)
            if (Objects.equals(insVal[i], "NULL"))
                insVal[i] = null;
        return new AcademicExchangeActivitiy(insVal[1], insVal[2], insVal[3], insVal[4]);

    }

    // 获取完整参与信息
    static GraduateParticipateActivitiy getParticipateFull(Scanner sc, String username){
        String[] insVal = new String[6];
        System.out.println("若为空值，请输入：'NULL'");
        System.out.println("请输入学生ID：");
        insVal[0] = sc.next();
        if (username != null && !username.equals(insVal[0]))
            return null;
        System.out.println("请输入活动ID：");
        insVal[1] = sc.next();
        System.out.println("请输入活动名称：");
        insVal[2] = sc.next();
        System.out.println("请输入证明照片：");
        insVal[3] = sc.next();
        System.out.println("请输入参与备注：");
        insVal[4] = sc.next();
        System.out.println("请输入导师签字：");
        insVal[5] = sc.next();
        for (int i=0;i<6;i++)
            if (Objects.equals(insVal[i], "NULL"))
                insVal[i] = null;
        Graduate gra = new Graduate(insVal[0], null, null, null, null);
        AcademicExchangeActivitiy aea = new AcademicExchangeActivitiy(insVal[1], null, null, null);
        return new GraduateParticipateActivitiy(gra, aea, insVal[2], insVal[3], insVal[4], insVal[5]);
    }

    // 获取完整参与信息（学生输入）
    static GraduateParticipateActivitiy getParticipateFullForGraduateIns(Scanner sc, String username){
        String[] insVal = new String[6];
        System.out.println("若为空值，请输入：'NULL'");
        System.out.println("请输入学生ID：");
        insVal[0] = sc.next();
        if (username != null && !username.equals(insVal[0]))
            return null;
        System.out.println("请输入活动ID：");
        insVal[1] = sc.next();
        System.out.println("请输入活动名称：");
        insVal[2] = sc.next();
        System.out.println("请输入证明照片：");
        insVal[3] = sc.next();
        System.out.println("请输入参与备注：");
        insVal[4] = sc.next();
        for (int i=0;i<6;i++)
            if (Objects.equals(insVal[i], "NULL"))
                insVal[i] = null;
        Graduate gra = new Graduate(insVal[0], null, null, null, null);
        AcademicExchangeActivitiy aea = new AcademicExchangeActivitiy(insVal[1], null, null, null);
        // 使学生无法插入和更新签字
        return new GraduateParticipateActivitiy(gra, aea, insVal[2], insVal[3], insVal[4], null);
    }

    // 获取完整参与信息以查询
    static GraduateParticipateActivitiy getParticipateFullToSearch(Scanner sc, String username){
        String[] insVal = new String[6];
        System.out.println("若为空值，请输入：'NULL'");
        System.out.println("请输入学生ID：");
        insVal[0] = sc.next();
        if (username != null && !username.equals(insVal[0]))
            return null;
        System.out.println("请输入活动ID：");
        insVal[1] = sc.next();
        System.out.println("请输入活动名称：");
        insVal[2] = sc.next();
        System.out.println("请输入参与备注：");
        insVal[4] = sc.next();
        System.out.println("请输入导师签字：");
        insVal[5] = sc.next();
        for (int i=0;i<6;i++)
            if (Objects.equals(insVal[i], "NULL"))
                insVal[i] = null;
        Graduate gra = new Graduate(insVal[0], null, null, null, null);
        AcademicExchangeActivitiy aea = new AcademicExchangeActivitiy(insVal[1], null, null, null);
        // 不支持图片查询
        return new GraduateParticipateActivitiy(gra, aea, insVal[2], null, insVal[4], insVal[5]);
    }

    // 获取参与ID
    private static GraduateParticipateActivitiy getParticipateID(Scanner sc, String username) {
        String[] insVal = new String[6];
        System.out.println("若为空值，请输入：'NULL'");
        System.out.println("请输入学生ID：");
        insVal[0] = sc.next();
        if (username != null && !username.equals(insVal[0]))
            return null;
        System.out.println("请输入活动ID：");
        insVal[1] = sc.next();
        for (int i=0;i<6;i++)
            if (Objects.equals(insVal[i], "NULL"))
                insVal[i] = null;
        Graduate gra = new Graduate(insVal[0], null, null, null, null);
        AcademicExchangeActivitiy aea = new AcademicExchangeActivitiy(insVal[1], null, null, null);
        return new GraduateParticipateActivitiy(gra, aea, insVal[2], insVal[3], insVal[4], insVal[5]);
    }

    // 获取教师签名
    private static GraduateParticipateActivitiy getMentorSign(Scanner sc) {
        String[] insVal = new String[6];
        System.out.println("若为空值，请输入：'NULL'");
        System.out.println("请输入学生ID：");
        insVal[0] = sc.next();
        System.out.println("请输入活动ID：");
        insVal[1] = sc.next();
        System.out.println("请输入教师签字：");
        insVal[5] = sc.next();
        for (int i=0;i<6;i++)
            if (Objects.equals(insVal[i], "NULL"))
                insVal[i] = null;
        Graduate gra = new Graduate(insVal[0], null, null, null, null);
        AcademicExchangeActivitiy aea = new AcademicExchangeActivitiy(insVal[1], null, null, null);
        return new GraduateParticipateActivitiy(gra, aea, insVal[2], insVal[3], insVal[4], insVal[5]);
    }

    public static void graduate(Scanner sc, String username) {
        System.out.println("欢迎进入学术交流子系统！\n[1]查找学术活动表项\n[2]ID查询学术活动表项\n学生参与表项\n[3]插入学生参与表项\n" +
                "[4]更新学生参与表项\n[5]删除学生参与表项\n[6]ID查询学生参与表项\n[7]查找学生参与表项\n[8]导出信息\n[9]退出\n请选择：");
        int select = sc.nextInt();
        GraduateParticipateActivitiy insVal;
        String res;
        List listRes;
        AcademicExchangeActivitiy entityRes;
        GraduateParticipateActivitiy gpaRes;
        boolean shouldExit = false;
        while (true) {
            switch (select) {
                case 1://查找
                    listRes = Model.findActivity(getActivityFull(sc));
                    View.showAcademicExchangeActivitiyList(listRes);
                    break;
                case 2://ID查询
                    entityRes = Model.getActivity(getActivityID(sc));
                    View.show(entityRes);
                    break;
                // 学生参与表
                case 3:// 插入
                    insVal = getParticipateFullForGraduateIns(sc, username);
                    if (insVal == null)
                        res = "权限错误，请勿插入他人信息！";
                    else
                        res = Model.addParticipate(insVal);
                    View.show(res);
                    break;
                case 4:// 更新
                    insVal = getParticipateFull(sc, username);
                    if (insVal == null)
                        res = "权限错误，请勿更新他人信息！";
                    else
                        res = Model.updateParticipate(insVal);
                    View.show(res);
                    break;
                case 5:// 删除
                    insVal = getParticipateID(sc, username);
                    if (insVal == null)
                        res = "权限错误，请勿删除他人信息！";
                    else
                        res = Model.deleteParticipate(insVal);
                    View.show(res);
                    break;
                case 6:// ID查询
                    insVal = getParticipateID(sc, username);
                    if (insVal == null){
                        res = "权限错误，请勿查询他人信息！";
                        View.show(res);
                        break;
                    }
                    gpaRes = Model.getParticipate(insVal);
                    View.show(gpaRes);
                    break;
                case 7:// 查找
                    insVal = getParticipateFullToSearch(sc, username);
                    if (insVal == null){
                        res = "权限错误，请勿查找他人信息！";
                        View.show(res);
                        break;
                    }
                    listRes = Model.findParticipate(insVal);
                    View.showGraduateParticipateActivitiyList(listRes);
                    break;
                case 8://导出
                    listRes = Model.getParticipateToExport(username);
                    View.export(listRes);
                    break;
                case 9:
                    shouldExit = true;
                    res = "成功退出学术交流子系统";
                    View.show(res);
                    break;
                default://输入错误
                    System.out.println("不好意思，输入错误!");
            }
            if (shouldExit)
                break;
            System.out.println("[1]查找学术活动表项\n[2]ID查询学术活动表项\n学生参与表项\n[3]插入学生参与表项\n[4]更新学生参与表项\n" +
                    "[5]删除学生参与表项\n[6]ID查询学生参与表项\n[7]查找学生参与表项\n[8]导出信息\n[9]退出\n请继续选择：");
            select = sc.nextInt();
        }

    }

    public static void mentor(Scanner sc, String username) {
        System.out.println("欢迎进入学术交流子系统！\n[1]查找学术活动表项\n[2]ID查询学术活动表项\n学生参与表项\n" +
                "[3]更新学生参与表项（签字）\n[4]ID查询学生参与表项\n[5]查找学生参与表项\n[6]退出\n请选择：");
        int select = sc.nextInt();
        String res;
        List listRes;
        AcademicExchangeActivitiy entityRes;
        GraduateParticipateActivitiy gpaRes;
        boolean shouldExit = false;
        while (true) {
            switch (select) {
                case 1:// 查找
                    listRes = Model.findActivity(getActivityFull(sc));
                    View.showAcademicExchangeActivitiyList(listRes);
                    break;
                case 2:// ID查询
                    entityRes = Model.getActivity(getActivityID(sc));
                    View.show(entityRes);
                    break;
                // 参与表
                case 3:// 更新
                    res = Model.updateParticipateSign(getMentorSign(sc), username);
                    View.show(res);
                    break;
                case 4:// ID查询
                    gpaRes = Model.getParticipate(getParticipateID(sc, null));
                    View.show(gpaRes);
                    break;
                case 5:// 查找
                    listRes = Model.findParticipate(getParticipateFullToSearch(sc, null));
                    View.showGraduateParticipateActivitiyList(listRes);
                    break;
                case 6://退出
                    shouldExit = true;
                    res = "成功退出学术交流子系统";
                    View.show(res);
                    break;
                default://输入错误
                    System.out.println("不好意思，输入错误!");
            }
            // 判断是否退出
            if (shouldExit)
                break;
            System.out.println("\n[1]查找学术活动表项\n[2]ID查询学术活动表项\n学生参与表项\n" +
                    "[3]更新学生参与表项（签字）\n[4]ID查询学生参与表项\n[5]查找学生参与表项\n[6]退出\n请继续选择：");
            select = sc.nextInt();
        }

    }

    public static void subjectHeader(Scanner sc) {
        System.out.println("欢迎进入学术交流子系统！\n[1]查找学术活动表项\n[2]ID查询学术活动表项\n[3]ID查询学生参与表项\n" +
                "[4]查找学生参与表项\n[5]退出\n请选择：");
        int select = sc.nextInt();
        String res;
        List listRes;
        AcademicExchangeActivitiy entityRes;
        GraduateParticipateActivitiy gpaRes;
        boolean shouldExit = false;
        while (true) {
            switch (select) {
                case 1://查找
                    listRes = Model.findActivity(getActivityFull(sc));
                    View.showAcademicExchangeActivitiyList(listRes);
                    break;
                case 2://ID查询
                    entityRes = Model.getActivity(getActivityID(sc));
                    View.show(entityRes);
                    break;
                case 3://ID查询
                    gpaRes = Model.getParticipate(getParticipateID(sc, null));
                    View.show(gpaRes);
                    break;
                case 4://查找
                    listRes = Model.findParticipate(getParticipateFullToSearch(sc, null));
                    View.showGraduateParticipateActivitiyList(listRes);
                    break;
                case 5:
                    shouldExit = true;
                    res = "成功退出学术交流子系统";
                    View.show(res);
                    break;
                default://输入错误
                    System.out.println("不好意思，输入错误!");
            }
            if (shouldExit)
                break;
            System.out.println("[1]查找学术活动表项\n[2]ID查询学术活动表项\n[3]ID查询学生参与表项\n" +
                    "[4]查找学生参与表项\n[5]退出\n请继续选择：");
            select = sc.nextInt();
        }

    }

    public static void admin(Scanner sc) {
        System.out.println("欢迎进入学术交流子系统！\n学术活动表操作：\n[1]插入学术活动表项\n[2]更新学术活动表项\n[3]查找学术活动表项\n" +
                "[4]ID查询查找学术活动表项\n[5]删除学术活动表项\n学生参与表操作：\n[6]插入学生参与表项\n[7]更新学生参与表项\n" +
                "[8]删除学生参与表项\n[9]ID查询学生参与表项\n[10]查找学生参与表项\n[11]退出\n请选择：");
        int select = sc.nextInt();
        String res = null;
        List listRes = null;
        AcademicExchangeActivitiy academicRes;
        GraduateParticipateActivitiy gpaRes;
        boolean shouldExit = false;
        while (true) {
            switch (select) {
                case 1:// 插入
                    res = Model.insertActivity(getActivityFull(sc));
                    View.show(res);
                    break;
                case 2:// 更新
                    res = Model.updateActivity(getActivityFull(sc));
                    View.show(res);
                    break;
                case 3:// 查找
                    listRes = Model.findActivity(getActivityFull(sc));
                    View.showGraduateParticipateActivitiyList(listRes);
                    break;
                case 4:// ID查询
                    academicRes = Model.getActivity(getActivityID(sc));
                    View.show(academicRes);
                    break;
                case 5:// 删除
                    res = Model.deleteActivity(getActivityID(sc));
                    View.show(res);
                    break;
                // 学生参与表
                case 6:// 插入
                    res = Model.addParticipate(getParticipateFull(sc, null));
                    View.show(res);
                    break;
                case 7:// 更新
                    res = Model.updateParticipate(getParticipateFull(sc, null));
                    View.show(res);
                    break;
                case 8:// 删除
                    res = Model.deleteParticipate(getParticipateID(sc, null));
                    View.show(res);
                    break;
                case 9:// ID查询
                    gpaRes = Model.getParticipate(getParticipateID(sc, null));
                    View.show(gpaRes);
                    break;
                case 10:// 查找
                    listRes = Model.findParticipate(getParticipateFullToSearch(sc, null));
                    View.showGraduateParticipateActivitiyList(listRes);
                    break;
                case 11:
                    shouldExit = true;
                    res = "成功退出学术交流子系统";
                    View.show(res);
                    break;
                default://输入错误
                    System.out.println("不好意思，输入错误!");
            }
            if (shouldExit)
                break;
            System.out.println("\n学术活动表操作：\n[1]插入学术活动表项\n[2]更新学术活动表项\n[3]查找学术活动表项\n" +
                    "[4]ID查询查找学术活动表项\n[5]删除学术活动表项\n学生参与表操作：\n[6]插入学生参与表项\n[7]更新学生参与表项\n" +
                    "[8]删除学生参与表项\n[9]ID查询学生参与表项\n[10]查找学生参与表项\n[11]退出\n请继续选择：");
            select = sc.nextInt();
        }

    }
}
