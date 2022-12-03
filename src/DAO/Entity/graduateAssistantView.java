package DAO.Entity;

public class graduateAssistantView {
    private Course course;      /*获取no，name，creidt*/
    private TeacherTeaching teacherTeaching; /*number*/
    private Teacher teacher; /*no name*/

    public graduateAssistantView() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TeacherTeaching getTeacherTeaching() {
        return teacherTeaching;
    }

    public void setTeacherTeaching(TeacherTeaching teacherTeaching) {
        this.teacherTeaching = teacherTeaching;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "graduateAssistantView{" +
                "courseNo=" + course.getCourseNo() +
                ", courseName=" + course.getCourseName() +
                ", credit=" + course.getCredit() +
                ", teacherNo=" + teacher.getTeacherNo() +
                ", teacherName=" + teacher.getTeacherName() +
                ", teachingNumber=" + teacherTeaching.getTeachingNumber() +
                ", priority=" + course.getPriority() +
                '}';
    }
}
