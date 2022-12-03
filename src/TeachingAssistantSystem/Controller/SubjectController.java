package TeachingAssistantSystem.Controller;

import DAO.Entity.*;
import DAO.Factory.DAOFactory;
import TeachingAssistantSystem.Model.SubjectUpdate;
import TeachingAssistantSystem.View.SubjectView;
import TeachingAssistantSystem.View.TeacherView;

import java.util.List;
import java.util.Scanner;

public class SubjectController {
    private String hostNo;
    private List<subjectHeaderView> subjectHeaderViews;

    public SubjectController(String currentLogonUsername) {
        this.hostNo=currentLogonUsername;
        this.subjectHeaderViews=DAOFactory.getInstance().getSubjectHeaderViewDAO().getSubjectHeaderView(hostNo);
    }

    public void SelectView(){
        Scanner sc = new Scanner(System.in);
        int flag=0;
        while(flag==0){
            int s=0;
            System.out.print("[1]修改课程优先级\n");
            s = sc.nextInt();
            switch (s){
                case 1:
                    this.ShowCoursePri();
                    this.UpdatePri();
                    break;
                case 0:
                    flag=1;
                    break;
                default:
                    break;
            }
        }
    }

    public void ShowCoursePri(){
        SubjectView subjectView=new SubjectView();
        subjectView.ShowCoursePri(subjectHeaderViews);
    }

    public void UpdatePri(){
        SubjectView subjectView=new SubjectView();
        List<String> list = subjectView.UpdatePri(subjectHeaderViews);
        Course course=DAOFactory.getInstance().getCourseDAO().getCourse(list.get(0));
        course.setPriority(list.get(1));

        SubjectUpdate subjectUpdate=new SubjectUpdate();
        subjectUpdate.UpdatePri(course);
    }
}
