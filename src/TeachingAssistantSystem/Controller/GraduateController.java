package TeachingAssistantSystem.Controller;

import DAO.Entity.*;
import DAO.Factory.DAOFactory;
import TeachingAssistantSystem.Model.SignUpdate;
import TeachingAssistantSystem.Model.Update;
import TeachingAssistantSystem.View.EvaluateView;
import TeachingAssistantSystem.View.GraduateView;
import java.util.List;
import java.util.Scanner;

public class GraduateController {
    private GraduateView graduateView;

    private Update update;

    private  List<GraduateTutor> graduateTutors;
    private String studentNo;

    public GraduateController(String studenNo) {
            this.graduateView=new GraduateView();
            this.studentNo=studenNo;
            this.update=new Update();
    }

    public void SelectView(){
        Scanner sc = new Scanner(System.in);
        int flag=0;
        while(flag==0){
            int s=0;
            System.out.print("[1]选择课程助教\n[2]填写工作自述\n[3]确认助教工作\n");
            s = sc.nextInt();
            switch (s){
                case 1:
                    try {
                        if(this.isChoose()!=0){
                            System.out.print("您已填报志愿了!\n");
                            break;
                        }
                        this.ShowCourseList();
                        this.Choose();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        if(this.isChoose()!=2){
                            System.out.print("您还不是助教!\n");
                            break;
                        }
                        this.JobReadme();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        if(this.isChoose()!=2){
                            System.out.print("您还不是助教!\n");
                            break;
                        }
                        if(this.isSign()){
                            System.out.print("您已签字确认了!\n");
                            break;
                        }
                        this.ShowEvaluateForm();
                        this.StudentSign();
                        break;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    flag=1;
                    break;
                default:
                    break;
            }
        }
    }

    public int isChoose(){
        GraduateTutor graduateTutor=new GraduateTutor();

        Graduate graduate=new Graduate();
        Teacher teacher=new Teacher();
        Course course=new Course();

        graduate.setStudentNo(studentNo);

        graduateTutor.setGraduate(graduate);
        graduateTutor.setCourse(course);
        graduateTutor.setTeacher(teacher);

        this.graduateTutors=DAOFactory.getInstance().getGraduateTutorDAO().findGraduateTutors(graduateTutor);

        GraduateTutorVolunteer graduateTutorVolunteer=DAOFactory.getInstance().getGraduateTutorVolunteerDAO().getGraduateTutorVolunteer(studentNo);

        int num=0;

        if(graduateTutorVolunteer.getStudentNo()==null){
            num=0;
        }else if(graduateTutorVolunteer.getStudentNo().equals(studentNo)){
            num=1;
        }

        for(int i=0;i<this.graduateTutors.size();i++){
            GraduateTutor gra=this.graduateTutors.get(i);
            if(gra.getGraduate().getStudentNo().equals(studentNo)){
                num=2;
            }
        }
        return num;
    }

    public boolean isSign(){
        List<evaluateView> evaluateViews=DAOFactory.getInstance().getEvaluateViewDAO().getEvaluateView(studentNo);
        for(int i=0;i<evaluateViews.size();i++){
            if(evaluateViews.get(i).getStudentSign()==null){
                return false;
            }
            if(evaluateViews.get(i).getStudentSign().equals(studentNo)){
                return true;
            }
        }
        return false;
    }


    public void ShowCourseList() throws Exception {
        graduateView.getGraduateView();
    }

    public void Choose() throws Exception{
       GraduateTutorVolunteer graduateTutorVolunteer=graduateView.chooseCourse(studentNo);
       update.InsertGraduateTutorVolunteer(graduateTutorVolunteer);
    }

    public void JobReadme() throws Exception{
       GraduateTutor graduateTutor=graduateView.JobReadme(this.graduateTutors);
       update.updateGraduateTutor(graduateTutor);
    }

    public void ShowEvaluateForm(){
        List<evaluateView> evaluateViews=DAOFactory.getInstance().getEvaluateViewDAO().getEvaluateView(studentNo);
        EvaluateView evaluateView=new EvaluateView();
        evaluateView.ShowEvaluateForm(evaluateViews);
    }

    public void StudentSign(){
        EvaluateView evaluateView=new EvaluateView();
        List<String> sign = evaluateView.StudentSign();
        SignUpdate signUpdate=new SignUpdate();
        GraduateTutor graduateTutor=this.graduateTutors.get(0);
        graduateTutor.setStudentSign(sign.get(0));
        graduateTutor.setStudentSignTime(sign.get(1));
        signUpdate.UpdateSign(graduateTutor);
    }
}
