package AcademicExchangeSystem;

import DAO.Entity.AcademicExchangeActivitiy;
import DAO.Entity.GraduateParticipateActivitiy;

import java.util.List;
import java.util.Scanner;

public class View {
    static void show(String str){
        System.out.println(str);
    }
    static void showAcademicExchangeActivitiyList(List<AcademicExchangeActivitiy> lst){
        if (lst == null){
            System.out.println("结果为空");
            return;
        }
        for (int i = 0; i < lst.size(); i++)
            show(lst.get(i));
    }
    static void showGraduateParticipateActivitiyList(List<GraduateParticipateActivitiy> lst){
        if (lst == null){
            System.out.println("结果为空");
            return;
        }
        for (int i = 0; i < lst.size(); i++)
            show(lst.get(i));
    }
    static void show(AcademicExchangeActivitiy aea){
        if (aea == null || aea.getActivityId() == null){
            System.out.println("结果为空");
            return;
        }
        System.out.print("活动ID：" + aea.getActivityId());
        System.out.print("\t名称：" + aea.getActivityName());
        System.out.print("\t时间：" + aea.getActivityTime());
        System.out.println("\t地点：" + aea.getActivityLocation());
    }
    static void show(GraduateParticipateActivitiy gpa){
        if (gpa == null || gpa.getGraduate() == null || gpa.getAcademicExchangeActivitiy().getActivityId() == null){
            System.out.println("结果为空");
            return;
        }
        System.out.print("学生ID：" + gpa.getGraduate().getStudentNo());
        System.out.print("\t活动ID：" + gpa.getAcademicExchangeActivitiy().getActivityId());
        System.out.print("\t中英名称：" + gpa.getReportNameChineseEnglish());
        System.out.print("\t备注：" + gpa.getRemark());
        System.out.println("\t导师签字：" + gpa.getMentorSign());
    }
    static void fileNotFound(){
        System.out.println("不好意思，图片未找到，将退出插入模块，返回学术子系统");
    }
    static void ioExcept(){
        System.out.println("不好意思，出现输入错误，将退出插入模块，返回学术子系统");
    }

    public static void export(List<GraduateParticipateActivitiy> listRes) {
        Scanner sc = new Scanner(System.in);
        int len = listRes.size();
        System.out.println("搜索结果如下：");
        for (int i = 0; i < len; i++)
            show(listRes.get(i));
        if (len < 2){
            System.out.println("您的参与活动数少于2，请录入后再尝试导出！");
            return;
        }
        System.out.println("请选择两项导出");
        int num1, num2;
        System.out.print("请输入第一项序号：");
        num1 = sc.nextInt();
        while (num1 < 1 || num1 > len){
            System.out.println("错误，请输入长度范围内序号！");
            System.out.print("请重新输入第一项序号：");
            num1 = sc.nextInt();
        }
        System.out.print("请输入第二项序号：");
        num2 = sc.nextInt();
        while (num1 == num2 || num2 < 1 || num2 > len){
            System.out.println("错误，请勿输入相同或长度范围外序号！");
            System.out.print("请重新输入第二项序号：");
            num2 = sc.nextInt();
        }
        System.out.println("系统对 " + listRes.get(num1 - 1).getGraduate().getStudentNo() + " 号学生参与学术交流活动认证");
        show(listRes.get(num1 - 1));
        show(listRes.get(num2 - 1));
    }
}
