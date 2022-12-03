package TeachingAssistantSystem.View;

import DAO.Entity.subjectHeaderView;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectView {
    public void ShowCoursePri(List<subjectHeaderView> subjectHeaderViews){
        for(int i=0;i<subjectHeaderViews.size();i++){
            subjectHeaderView sub=subjectHeaderViews.get(i);
            System.out.println(sub.getCourse().getCourseNo()+"\t"+sub.getPriority());
        }
    }

    public List<String> UpdatePri(List<subjectHeaderView> subjectHeaderViews){
        Scanner sc = new Scanner(System.in);
        List<String> list=new ArrayList<>();
        while(true){
            System.out.print("输入您要修改优先级的课程号:");
            String s = sc.next();
            for(int i=0;i<subjectHeaderViews.size();i++){
                subjectHeaderView sub=subjectHeaderViews.get(i);
                if(sub.getCourse().getCourseNo().equals(s)){
                    list.add(s);
                    System.out.print("输入新的优先级:");
                    String str = sc.next();
                    list.add(str);
                    return list;
                }
            }
        }
    }
}
