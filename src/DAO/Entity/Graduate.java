package DAO.Entity;

/*研究生*/
public class Graduate {
    private String studentNo;
    private String studentName;
    private Subject subject;
    private String type;
    Mentor mentor;

    public Graduate() {
    }

    public Graduate(String studentNo, String studentName, Subject subject, String type, Mentor mentor) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.subject = subject;
        this.type = type;
        this.mentor = mentor;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Graduate{" +
                "studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", subject='" + subject.getSubjectNo() + '\'' +
                ", type='" + type + '\'' +
                ", mentorNo='" + mentor.getMentorNo() + '\'' +
                '}';
    }
}
