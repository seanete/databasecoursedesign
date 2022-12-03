package AcademicExchangeSystem;

import DAO.Entity.AcademicExchangeActivitiy;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.jws.WebParam;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    // 获取ID
    static String getID(Scanner sc){
        System.out.println("请输入插入信息：");
        return sc.next();
    }
    // 获取一整项数据
    static AcademicExchangeActivitiy getFull(Scanner sc){
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
    public static void graduate(Scanner sc) {
        System.out.println("欢迎进入学术交流子系统！\n[1]查找学术活动表项\n[2]ID查询学术活动表项\n[3]\n[4]删除\n[5]\n[6]退出\n请选择：");
        int select = sc.nextInt();
        String res = null;
        List listRes = null;
        AcademicExchangeActivitiy entityRes = null;
        boolean shouldExit = false;
        while (true) {
            switch (select) {
                case 1://插入
                    listRes = Model.find(getFull(sc));
                    View.printActivity(listRes);
                    break;
                case 2://查找
                    entityRes = Model.get(getID(sc));
                    View.printActivity(entityRes);
                    break;
                case 3://导出两条
                    break;
                case 4://查看全部
                    break;
                case 5:
                    shouldExit = true;
                    res = "成功退出学术交流子系统";
                    break;
                default://输入错误
                    System.out.println("不好意思，输入错误!");
            }
            if (shouldExit)
                break;
            System.out.println("[1]查找学术活动表项\n[2]ID查询学术活动表项\n[3]搜索\n[4]删除\n[5]ID查询\n[6]退出\n请继续选择：");
            select = sc.nextInt();
        }

    }

    public static void mentor(Scanner sc) {
        System.out.println("欢迎进入学术交流子系统！\n[1]插入学术活动表项\n[2]查找学术活动表项\n[3]ID查询学术活动表项\n[4]退出\n请选择：");
        int select = sc.nextInt();
        String res = null;
        List listRes = null;
        AcademicExchangeActivitiy entityRes = null;
        boolean shouldExit = false;
        while (true) {
            switch (select) {
                case 1:// 插入
                    res = Model.insert(getFull(sc));
                    View.printActivity(res);
                    break;
                case 2:// 查找
                    listRes = Model.find(getFull(sc));
                    View.printActivity(listRes);
                    break;
                case 3:// ID查询
                    entityRes = Model.get(getID(sc));
                    View.printActivity(entityRes);
                    break;
                case 4://退出
                    shouldExit = true;
                    res = "成功退出学术交流子系统";
                    break;
                default://输入错误
                    System.out.println("不好意思，输入错误!");
            }
            // 判断是否退出
            if (shouldExit)
                break;
            System.out.println("[1]插入学术活动表项\n[2]查找学术活动表项\n[3]ID查询学术活动表项\n[4]退出\n请继续选择：");
            select = sc.nextInt();
        }

    }

    public static void subjectHeader(Scanner sc) {
        System.out.println("欢迎进入学术交流子系统！\n[1]插入学术活动表项\n[2]更新学术活动表项\n[3]查找学术活动表项\n[4]ID查询查找学术活动表项\n[5]删除学术活动表项\n[6]退出\n请选择：");
        int select = sc.nextInt();
        String res = null;
        List listRes = null;
        AcademicExchangeActivitiy entityRes = null;
        boolean shouldExit = false;
        while (true) {
            switch (select) {
                case 1:// 插入
                    res = Model.insert(getFull(sc));
                    View.printActivity(res);
                    break;
                case 2:// 更新
                    res = Model.update(getFull(sc));
                    View.printActivity(res);
                    break;
                case 3:// 查找
                    listRes = Model.find(getFull(sc));
                    View.printActivity(listRes);
                    break;
                case 4:// ID查询
                    entityRes = Model.get(getID(sc));
                    View.printActivity(entityRes);
                    break;
                case 5:// 删除
                    res = Model.delete(getID(sc));
                    View.printActivity(res);
                    break;
                case 6:
                    shouldExit = true;
                    res = "成功退出学术交流子系统";
                    break;
                default://输入错误
                    System.out.println("不好意思，输入错误!");
            }
            if (shouldExit)
                break;
            System.out.println("[1]插入学术活动表项\n[2]更新学术活动表项\n[3]查找学术活动表项\n[4]ID查询查找学术活动表项\n[5]删除学术活动表项\n[6]退出\n请继续选择：");
            select = sc.nextInt();
        }

    }
}
