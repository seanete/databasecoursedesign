package DAO.Entity;

public class Course {
    private String courseNo;
    private String courseName;
    private String properties;
    private Subject subject;
    private String priority;

    private String credit;

    public Course() {
    }

    public Course(String courseNo, String courseName, String properties, Subject subject, String priority, String credit) {
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.properties = properties;
        this.subject = subject;
        this.priority = priority;
        this.credit = credit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", properties='" + properties + '\'' +
                ", subject=" + subject +
                ", priority='" + priority + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
