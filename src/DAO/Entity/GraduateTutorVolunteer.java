package DAO.Entity;

public class GraduateTutorVolunteer {
    private String studentNo;
    private Course volunteer1Course;
    private Teacher volunteer1Teacher;
    private Course volunteer2Course;
    private Teacher volunteer2Teacher;

    public GraduateTutorVolunteer() {
    }

    public GraduateTutorVolunteer(String studentNo, Course volunteer1Course, Teacher volunteer1Teacher, Course volunteer2Course, Teacher volunteer2Teacher) {
        this.studentNo = studentNo;
        this.volunteer1Course = volunteer1Course;
        this.volunteer1Teacher = volunteer1Teacher;
        this.volunteer2Course = volunteer2Course;
        this.volunteer2Teacher = volunteer2Teacher;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Course getVolunteer1Course() {
        return volunteer1Course;
    }

    public void setVolunteer1Course(Course volunteer1Course) {
        this.volunteer1Course = volunteer1Course;
    }

    public Teacher getVolunteer1Teacher() {
        return volunteer1Teacher;
    }

    public void setVolunteer1Teacher(Teacher volunteer1Teacher) {
        this.volunteer1Teacher = volunteer1Teacher;
    }

    public Course getVolunteer2Course() {
        return volunteer2Course;
    }

    public void setVolunteer2Course(Course volunteer2Course) {
        this.volunteer2Course = volunteer2Course;
    }

    public Teacher getVolunteer2Teacher() {
        return volunteer2Teacher;
    }

    public void setVolunteer2Teacher(Teacher volunteer2Teacher) {
        this.volunteer2Teacher = volunteer2Teacher;
    }

    @Override
    public String toString() {
        return "graduateTutorVolunteer{" +
                "studentNo='" + studentNo + '\'' +
                ", volunteer1Course=" + volunteer1Course +
                ", volunteer1Teacher=" + volunteer1Teacher +
                ", volunteer2Course=" + volunteer2Course +
                ", volunteer2Teacher=" + volunteer2Teacher +
                '}';
    }
}
