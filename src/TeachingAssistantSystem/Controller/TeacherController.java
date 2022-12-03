package TeachingAssistantSystem.Controller;

import DAO.Entity.*;
import DAO.Factory.DAOFactory;
import TeachingAssistantSystem.Model.SignUpdate;
import TeachingAssistantSystem.Model.TeacherUpdate;
import TeachingAssistantSystem.View.EvaluateView;
import TeachingAssistantSystem.View.TeacherView;
import java.util.List;
import java.util.Scanner;

public class TeacherController {
    private TeacherView teacherView;
    private List<TeacherTeaching> teacherTeaching;
    private TeacherUpdate teacherUpdate;

    private String studentNo;

    private String teacherNo;

    private String courseNo;

    public TeacherController(String currentLogonUsername) {
        TeacherTeaching teacherTeaching1=new TeacherTeaching();
        Teacher teacher=new Teacher();
        Course course=new Course();
        GraduateTutorVolunteer graduateTutorVolunteer=new GraduateTutorVolunteer();
        teacher.setTeacherNo(currentLogonUsername);

        teacherTeaching1.setTeacher(teacher);
        teacherTeaching1.setCourse(course);
        teacherTeaching1.setGraduateTutorVolunteer(graduateTutorVolunteer);

        teacherTeaching= DAOFactory.getInstance().getTeacherTeachingDAO().findTeacherTeachings(teacherTeaching1);
        teacherUpdate=new TeacherUpdate();
        teacherNo=currentLogonUsername;
    }

    public void SelectView(){
        Scanner sc = new Scanner(System.in);
        int flag=0;
        while(flag==0){
            int s=0;
            System.out.print("[1]为课程选择助教\n[2]为助教填写评价\n[3]签字确认助教工作\n");
            s = sc.nextInt();
            switch (s){
                case 1:
                    courseNo=this.InputCourseNo();
                    if(courseNo.equals("0"))            /*相当于false*/
                        break;
                    teacherView=new TeacherView(courseNo);
                    this.ShowGraduateAssistant();
                    this.ChooseGraduate();
                    if(studentNo.equals("0"))
                        break;
                    this.UpdateTeacherTeaching();
                    this.DeleteGraduateTutorVolunteer();
                    this.AddGraduateTutor();
                    break;
                case 2:
                    try {
                        if(!isChoose()){
                            System.out.print("您还未选择助教\n");
                            break;
                        }
                        this.teacherView=new TeacherView();
                        this.teacherEvaluation();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        if(!isChoose()){
                            System.out.print("您还未选择助教\n");
                            break;
                        }
                        this.ShowEvaluateForm();
                        this.TeacherSign();
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

    public String InputCourseNo(){
        Scanner sc = new Scanner(System.in);
        String No="";
        System.out.print("您所教的课程如下:\n");
        for(int i=0;i<teacherTeaching.size();i++){
            TeacherTeaching tea=teacherTeaching.get(i);
            System.out.println(tea.getCourse().getCourseNo());
        }
        int flag=0;
        while(flag==0){
            System.out.print("输入您要操作的课程号:");
            No="";
            No = sc.next();
            if(No.equals("0"))
                return "0";
            for(int i=0;i<teacherTeaching.size();i++){
                TeacherTeaching tea=teacherTeaching.get(i);
                if(tea.getCourse().getCourseNo().equals(No)){
                    if(!tea.getGraduateTutorVolunteer().getStudentNo().equals("0000")){
                        System.out.print("该课程已经选择了"+tea.getGraduateTutorVolunteer().getStudentNo()+"作为助教!\n");
                        break;
                    }
                    flag=1;
                    break;
                }
            }
        }
        return No;
    }

    public boolean isChoose(){      /*是否选择助教了*/
        boolean is=false;
        for(int i=0;i<teacherTeaching.size();i++){
            TeacherTeaching tea=teacherTeaching.get(i);
            if(tea.getTeacher().getTeacherNo().equals(teacherNo)){
                if(!tea.getGraduateTutorVolunteer().getStudentNo().equals("0000")){
                    studentNo=tea.getGraduateTutorVolunteer().getStudentNo();
                    courseNo=tea.getCourse().getCourseNo();
                    is=true;
                    break;
                }
            }
        }
        return is;
    }

    public void ShowGraduateAssistant(){
        teacherView.ShowGraduateAssistant();
    }

    public void ChooseGraduate(){
        studentNo=teacherView.ChooseGraduate();
    }

    public void DeleteGraduateTutorVolunteer(){
        teacherUpdate.DeleteGraduateTutorVolunteer(studentNo);
    }

    public void UpdateTeacherTeaching(){
        Teacher teacher=new Teacher();
        Course course=new Course();
        teacher.setTeacherNo(teacherNo);
        course.setCourseNo(courseNo);

        TeacherTeaching teacherTeaching=DAOFactory.getInstance().getTeacherTeachingDAO().getTeacherTeaching(teacher,course);
        GraduateTutorVolunteer graduateTutorVolunteer=new GraduateTutorVolunteer();
        graduateTutorVolunteer.setStudentNo(studentNo);
        teacherTeaching.setGraduateTutorVolunteer(graduateTutorVolunteer);

        teacherUpdate.UpdateTeacherTeaching(teacherTeaching);
    }

    public void AddGraduateTutor(){
        GraduateTutor graduateTutor=new GraduateTutor();
        Graduate graduate=new Graduate();
        graduate.setStudentNo(studentNo);
        Course course=new Course();
        course.setCourseNo(courseNo);
        Teacher teacher=new Teacher();
        teacher.setTeacherNo(teacherNo);

        graduateTutor.setGraduate(graduate);
        graduateTutor.setCourse(course);
        graduateTutor.setTeacher(teacher);
        graduateTutor.setJobReadme("");
        graduateTutor.setTeacherEvaluation("");

        teacherUpdate.AddGraduateTutor(graduateTutor);
    }

    public void teacherEvaluation() throws Exception{
        GraduateTutor graduateTutor=new GraduateTutor();

        Graduate graduate=new Graduate();
        Teacher teacher=new Teacher();
        Course course=new Course();

        teacher.setTeacherNo(teacherNo);

        graduateTutor.setGraduate(graduate);
        graduateTutor.setCourse(course);
        graduateTutor.setTeacher(teacher);

        List<GraduateTutor> graduateTutors=DAOFactory.getInstance().getGraduateTutorDAO().findGraduateTutors(graduateTutor);
        GraduateTutor graduateTutor1=teacherView.teacherEvaluation(graduateTutors);
        teacherUpdate.updateGraduateTutor(graduateTutor1);
    }

    public void ShowEvaluateForm(){
        List<evaluateView> evaluateViews=DAOFactory.getInstance().getEvaluateViewDAO().getEvaluateView(studentNo);
        EvaluateView evaluateView=new EvaluateView();
        evaluateView.ShowEvaluateForm(evaluateViews);
    }
    public void TeacherSign(){
        EvaluateView evaluateView=new EvaluateView();
        List<String> sign = evaluateView.TeacherSign();
        SignUpdate signUpdate=new SignUpdate();

        Graduate graduate=new Graduate();
        Course course=new Course();
        graduate.setStudentNo(studentNo);
        course.setCourseNo(courseNo);

        GraduateTutor graduateTutor=DAOFactory.getInstance().getGraduateTutorDAO().getGraduateTutor(graduate,course);
        graduateTutor.setTeacherSign(sign.get(0));
        graduateTutor.setTeacherSignTime(sign.get(1));

        signUpdate.UpdateSign(graduateTutor);
    }

}
