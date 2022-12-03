package DAO.Entity;

public class Mentor {
    private String mentorNo;
    private String mentorName;

    private Subject subject;

    public Mentor() {
    }

    public Mentor(String mentorNo, String mentorName, Subject subject) {
        this.mentorNo = mentorNo;
        this.mentorName = mentorName;
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getMentorNo() {
        return mentorNo;
    }

    public void setMentorNo(String mentorNo) {
        this.mentorNo = mentorNo;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorNo='" + mentorNo + '\'' +
                ", mentorName='" + mentorName + '\'' +
                ", subjectNo=" + subject.getSubjectNo() +
                '}';
    }
}
