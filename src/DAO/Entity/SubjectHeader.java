package DAO.Entity;

public class SubjectHeader {
    private String headerNo;
    private String headerName;
    private Subject subject;

    public SubjectHeader() {
    }

    public SubjectHeader(String headerNo, String headerName, Subject subject) {
        this.headerNo = headerNo;
        this.headerName = headerName;
        this.subject = subject;
    }

    public String getHeaderNo() {
        return headerNo;
    }

    public void setHeaderNo(String headerNo) {
        this.headerNo = headerNo;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SubjectHeader{" +
                "headerNo='" + headerNo + '\'' +
                ", headerName='" + headerName + '\'' +
                ", subjectNo='" + subject.getSubjectNo() + '\'' +
                '}';
    }
}
