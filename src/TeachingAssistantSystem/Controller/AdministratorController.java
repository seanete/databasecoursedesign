package TeachingAssistantSystem.Controller;

import DAO.Entity.*;
import DAO.Factory.DAOFactory;
import TeachingAssistantSystem.Model.SignUpdate;
import TeachingAssistantSystem.View.AdministratorView;
import TeachingAssistantSystem.View.EvaluateView;

import java.util.List;
import java.util.Scanner;

public class AdministratorController {

    private List<evaluateView> evaluateViews;

    private String studentNo;

    public AdministratorController(){
       this.evaluateViews= DAOFactory.getInstance().getEvaluateViewDAO().getAll();
    }


    public void SelectView(){
        Scanner sc = new Scanner(System.in);
        int flag=0;
        while(flag==0){
            int s=0;
            System.out.print("[1]确认助教表单\n");
            s = sc.nextInt();
            switch (s){
                case 1:
                    if(!this.ShowForm()){
                        System.out.print("还没有助教表提交\n");
                        break;
                    }
                    this.ShowEvaluateForm();
                    this.Check();
                    break;
                case 0:
                    flag=1;
                    break;
                default:
                    break;
            }
        }
    }


    public boolean ShowForm(){
        if(evaluateViews.size()==0)
            return false;
        for(int i=0;i<evaluateViews.size();i++){
            System.out.print("学号:"+evaluateViews.get(i).getStudentNo()+"\t课程名:"+evaluateViews.get(i).getCourseName()+"\t教师名:"+evaluateViews.get(i).getTeacherName());
        }
        return true;
    }

    public void ShowEvaluateForm(){
        AdministratorView administratorView=new AdministratorView();
        studentNo=administratorView.InputStudentNo(evaluateViews);
        List<evaluateView> eva= DAOFactory.getInstance().getEvaluateViewDAO().getEvaluateView(studentNo);
        EvaluateView evaluateView=new EvaluateView();
        evaluateView.ShowEvaluateForm(eva);
    }

    public void Check(){
        AdministratorView administratorView=new AdministratorView();
        String str=administratorView.Check();
        if(str.equals("合格")){

        }else{
            GraduateTutor graduateTutor=new GraduateTutor();
            Graduate graduate=new Graduate();
            graduate.setStudentNo(studentNo);
            graduateTutor.setGraduate(graduate);
            graduateTutor.setTeacher(new Teacher());
            graduateTutor.setCourse(new Course());
            List<GraduateTutor> graduateTutors=DAOFactory.getInstance().getGraduateTutorDAO().findGraduateTutors(graduateTutor);
            graduateTutors.get(0).setStudentSign(null);
            graduateTutors.get(0).setStudentSignTime(null);
            graduateTutors.get(0).setTeacherSign(null);
            graduateTutors.get(0).setTeacherSignTime(null);
            SignUpdate signUpdate=new SignUpdate();
            signUpdate.UpdateSign(graduateTutors.get(0));
        }
    }
}
