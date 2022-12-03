package TeachingAssistantSystem.View;
import DAO.Entity.*;
import DAO.Factory.DAOFactory;

import java.util.List;
import java.util.Scanner;

public class TeacherView {

    private List<teacherAssitantView> teacherAssitantViews;

    public TeacherView() {
    }

    public TeacherView(String courseNo) {
        this.teacherAssitantViews= DAOFactory.getInstance().getInstance().getTeacherAssitantViewDAO().getTeacherAssitantView(courseNo);
    }

    public void ShowGraduateAssistant(){
        for(int i=0;i<teacherAssitantViews.size();i++){
            teacherAssitantView tea=teacherAssitantViews.get(i);
            System.out.println(tea.getGraduate().getStudentNo());
        }
    }

    public String ChooseGraduate(){
        Scanner sc = new Scanner(System.in);
        String studentNo="";
        int flag=0;
        while(flag==0){
            System.out.print("输入您选择作为助教的学生学号:");
            studentNo="";
            studentNo = sc.next();
            for(int i=0;i<teacherAssitantViews.size();i++){
                teacherAssitantView tea=teacherAssitantViews.get(i);
                if(tea.getGraduate().getStudentNo().equals(studentNo)){
                    flag=1;
                    break;
                }
            }
        }
        return studentNo;
    }

    public GraduateTutor teacherEvaluation(List<GraduateTutor> graduateTutors) throws Exception{
        for(int i=0;i<graduateTutors.size();i++){
            GraduateTutor graduateTutor=graduateTutors.get(i);
            System.out.println("学号:"+graduateTutor.getGraduate().getStudentNo()+"\t课程号:"+graduateTutor.getCourse().getCourseNo());
        }
        Scanner sc = new Scanner(System.in);
        while(true){
            String s="";
            System.out.print("输入您选择作为助教的学生学号:");
            s=sc.next();
            for(int i=0;i<graduateTutors.size();i++){
                GraduateTutor graduateTutor=graduateTutors.get(i);
                if(graduateTutor.getGraduate().getStudentNo().equals(s)){
                    String str="";
                    System.out.print("输入您对该生的评价:");
                    str=sc.next();
                    graduateTutor.setTeacherEvaluation(str);
                    return graduateTutor;
                }
            }
        }
    }
}
