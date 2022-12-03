package TeachingAssistantSystem.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.Entity.evaluateView;

public class EvaluateView {
    public EvaluateView() {
    }

    public void ShowEvaluateForm(List<evaluateView> evaluateViews){
        for(int i=0;i<evaluateViews.size();i++){
            evaluateView evaluateView=evaluateViews.get(i);
            String put="研究生姓名:"+evaluateView.getStudentName()+"\t\t\t\t"+"研究生学号:"+evaluateView.getStudentNo()+"\n"
                    +"课程名称:"+evaluateView.getCourseName()+"\t\t\t\t"+"授课人数:"+evaluateView.getTeachingNumber()+"\n"
                    +"研究生所在学科:"+"\t\t\t\t"+evaluateView.getSubjectName()+"\n"
                    +"课程性质:"+evaluateView.getProperties()+"\t\t\t\t"+"授课对象:"+evaluateView.getType()+"\n"
                    +"授课教师:"+evaluateView.getTeacherName()+"\t\t\t\t"+"授课时间:"+evaluateView.getTeachingTime()+"\n"
                    +"助教工作自述:"+evaluateView.getJobReadme()+"\t\t\t\t"+"研究生签字:"+evaluateView.getStudentSign()+"\t\t"+evaluateView.getStudentSignTime()+"\n"
                    +"授课教师评价:"+"\t\t\t\t"+"评价结果:"+evaluateView.getTeacherEvaluation()+"\n"
                    +"\t\t\t\t教师签字:"+evaluateView.getTeacherSign()+"\t\t"+evaluateView.getTeacherSignTime();
            System.out.println(put);
        }
    }

    public List<String> StudentSign(){
        List<String> sign=new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("输入签名信息(学号):");
        String s = sc.next();
        sign.add(s);
        System.out.print("输入签名时间(X-X-X):");
        s = sc.next();
        sign.add(s);
        return sign;
    }

    public List<String> TeacherSign(){
        List<String> sign=new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("输入签名信息(工号号):");
        String s = sc.next();
        sign.add(s);
        System.out.print("输入签名时间(X-X-X):");
        s = sc.next();
        sign.add(s);
        return sign;
    }
}
