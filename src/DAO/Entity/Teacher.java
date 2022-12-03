package DAO.Entity;

public class Teacher {
    private String teacherNo;
    private String teacherName;

    private Subject subject;

    public Teacher() {
    }

    public Teacher(String teacherNo, String teacherName, Subject subject) {
        this.teacherNo = teacherNo;
        this.teacherName = teacherName;
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherNo='" + teacherNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", subject=" + subject +
                '}';
    }
}
