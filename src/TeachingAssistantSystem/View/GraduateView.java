package TeachingAssistantSystem.View;
import DAO.Entity.*;
import DAO.Factory.DAOFactory;
import TeachingAssistantSystem.Model.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class GraduateView {

    private List<graduateAssistantView> graduateView;
    public GraduateView() {
        this.graduateView = DAOFactory.getInstance().getGraduateAssistantViewDAO().getGraduateAssistantView();
    }

    public void getGraduateView() throws SQLException{
        for(int i=0;i<graduateView.size();i++){
            graduateAssistantView gra=graduateView.get(i);
            System.out.println(gra.toString());
        }
    }

    public GraduateTutorVolunteer chooseCourse(String studentNo) throws Exception{
        Scanner sc = new Scanner(System.in);

        String courseNo1="";
        String teacherNo1="";
        int num=1;
        GraduateTutorVolunteer graduateTutorVolunteer=new GraduateTutorVolunteer();
        while(num!=3){
            int flag=0;
            int i=0;
            String s=String.valueOf(num);
            System.out.print("请输入志愿"+s+"的课程号:");
            String choice = sc.next();
            for(i=0;i<graduateView.size();i++){
                graduateAssistantView gra=graduateView.get(i);
                if(gra.getCourse().getCourseNo().equals(choice)){
                    flag++;
                    num++;
                    break;
                }
            }
            if(flag==1){
                System.out.print("您已成功报选"+choice+"课程的助教!");
                Course course=new Course();
                Teacher teacher=new Teacher();
                if(num==2) {
                    graduateTutorVolunteer.setStudentNo(studentNo);
                    course.setCourseNo(choice);
                    teacher.setTeacherNo(graduateView.get(i).getTeacher().getTeacherNo());
                    graduateTutorVolunteer.setVolunteer1Course(course);
                    graduateTutorVolunteer.setVolunteer1Teacher(teacher);
                }else{
                    graduateTutorVolunteer.setStudentNo(studentNo);
                    course.setCourseNo(choice);
                    teacher.setTeacherNo(graduateView.get(i).getTeacher().getTeacherNo());
                    graduateTutorVolunteer.setVolunteer2Course(course);
                    graduateTutorVolunteer.setVolunteer2Teacher(teacher);

                    return graduateTutorVolunteer;
                    //update.InsertGraduateTutorVolunteer(graduateTutorVolunteer);
                }
            }else{
                System.out.print("输入的课程号"+choice+"有误!");
            }
        }
        return null;
    }

    public GraduateTutor JobReadme(List<GraduateTutor> graduateTutors) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你对"+ graduateTutors.get(0).getCourse().getCourseNo() +"自述:\n");
        String s=sc.next();
        GraduateTutor graduateTutor=DAOFactory.getInstance().getGraduateTutorDAO().getGraduateTutor(graduateTutors.get(0).getGraduate(),graduateTutors.get(0).getCourse());
        graduateTutor.setJobReadme(s);
        return graduateTutor;
    }
}
