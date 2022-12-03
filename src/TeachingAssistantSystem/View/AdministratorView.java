package TeachingAssistantSystem.View;

import DAO.Entity.evaluateView;

import java.util.List;
import java.util.Scanner;

public class AdministratorView {
    public String InputStudentNo(List<evaluateView> evaluateViews){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("\n输入审核的学生学号:");
            String studentNo = sc.next();
            for(int i=0;i<evaluateViews.size();i++){
                if(evaluateViews.get(i).getStudentNo().equals(studentNo))
                    return studentNo;
            }
        }
    }

    public String Check(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("输入  合格|不合格:");
            String str = sc.next();
            if (str.equals("合格") || str.equals("不合格")) {
                return str;
            }
        }
    }
}
