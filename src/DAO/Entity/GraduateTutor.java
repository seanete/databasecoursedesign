package DAO.Entity;

/*研究生助教表*/
public class GraduateTutor {
    private Graduate graduate;
    private Course course;
    private Teacher teacher;
    private String jobReadme;
    private String TeacherEvaluation;

    private String studentSign;
    private String studentSignTime;
    private String teacherSign;
    private String teacherSignTime;

    public GraduateTutor() {
    }

    public GraduateTutor(Graduate graduate, Course course, Teacher teacher, String jobReadme, String teacherEvaluation, String studentSign, String studentSignTime, String teacherSign, String teacherSignTime) {
        this.graduate = graduate;
        this.course = course;
        this.teacher = teacher;
        this.jobReadme = jobReadme;
        TeacherEvaluation = teacherEvaluation;
        this.studentSign = studentSign;
        this.studentSignTime = studentSignTime;
        this.teacherSign = teacherSign;
        this.teacherSignTime = teacherSignTime;
    }

    public String getStudentSign() {
        return studentSign;
    }

    public void setStudentSign(String studentSign) {
        this.studentSign = studentSign;
    }

    public String getStudentSignTime() {
        return studentSignTime;
    }

    public void setStudentSignTime(String studentSignTime) {
        this.studentSignTime = studentSignTime;
    }

    public String getTeacherSign() {
        return teacherSign;
    }

    public void setTeacherSign(String teacherSign) {
        this.teacherSign = teacherSign;
    }

    public String getTeacherSignTime() {
        return teacherSignTime;
    }

    public void setTeacherSignTime(String teacherSignTime) {
        this.teacherSignTime = teacherSignTime;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getJobReadme() {
        return jobReadme;
    }

    public void setJobReadme(String jobReadme) {
        this.jobReadme = jobReadme;
    }

    public String getTeacherEvaluation() {
        return TeacherEvaluation;
    }

    public void setTeacherEvaluation(String teacherEvaluation) {
        TeacherEvaluation = teacherEvaluation;
    }

    @Override
    public String toString() {
        return "GraduateTutor{" +
                "graduate=" + graduate +
                ", course=" + course +
                ", teacher=" + teacher +
                ", jobReadme='" + jobReadme + '\'' +
                ", TeacherEvaluation='" + TeacherEvaluation + '\'' +
                ", studentSign='" + studentSign + '\'' +
                ", studentSignTime='" + studentSignTime + '\'' +
                ", teacherSign='" + teacherSign + '\'' +
                ", teacherSignTime='" + teacherSignTime + '\'' +
                '}';
    }
}
