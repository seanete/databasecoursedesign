package DAO.Entity;


public class TeacherTeaching {
    private Teacher teacher;
    private Course course;
    private String teachingTarget;
    private String teachingNumber;
    private String teachingTime;
    private GraduateTutorVolunteer graduateTutorVolunteer;

    public TeacherTeaching() {
    }

    public TeacherTeaching(Teacher teacher, Course course, String teachingTarget, String teachingNumber, String teachingTime, GraduateTutorVolunteer graduateTutorVolunteer) {
        this.teacher = teacher;
        this.course = course;
        this.teachingTarget = teachingTarget;
        this.teachingNumber = teachingNumber;
        this.teachingTime = teachingTime;
        this.graduateTutorVolunteer = graduateTutorVolunteer;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTeachingTarget() {
        return teachingTarget;
    }

    public void setTeachingTarget(String teachingTarget) {
        this.teachingTarget = teachingTarget;
    }

    public String getTeachingNumber() {
        return teachingNumber;
    }

    public void setTeachingNumber(String teachingNumber) {
        this.teachingNumber = teachingNumber;
    }

    public String getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(String teachingTime) {
        this.teachingTime = teachingTime;
    }

    public GraduateTutorVolunteer getGraduateTutorVolunteer() {
        return graduateTutorVolunteer;
    }

    public void setGraduateTutorVolunteer(GraduateTutorVolunteer graduateTutorVolunteer) {
        this.graduateTutorVolunteer = graduateTutorVolunteer;
    }

    @Override
    public String toString() {
        return "TeacherTeaching{" +
                "teacher=" + teacher +
                ", course=" + course +
                ", teachingTarget='" + teachingTarget + '\'' +
                ", teachingNumber=" + teachingNumber +
                ", teachingTime='" + teachingTime + '\'' +
                ", graduateTutorVolunteer=" + graduateTutorVolunteer +
                '}';
    }
}
