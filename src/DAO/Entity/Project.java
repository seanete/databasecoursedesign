package DAO.Entity;

public class Project {
    private String projectIndex;
    private String projectType;
    private String projectName;

    private Subject subject;
    private Mentor mentor;

    public Project() {
    }

    public Project(String projectIndex, String projectType, String projectName, Subject subject, Mentor mentor) {
        this.projectIndex = projectIndex;
        this.projectType = projectType;
        this.projectName = projectName;
        this.subject = subject;
        this.mentor = mentor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public String getProjectIndex() {
        return projectIndex;
    }

    public void setProjectIndex(String projectIndex) {
        this.projectIndex = projectIndex;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectIndex='" + projectIndex + '\'' +
                ", projectType='" + projectType + '\'' +
                ", projectName='" + projectName + '\'' +
                ", subject=" + subject +
                ", mentor=" + mentor +
                '}';
    }
}
