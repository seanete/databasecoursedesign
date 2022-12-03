package DAO.Entity;


public class GraduateParticipateProject {
    private Graduate graduate;
    private Project project;
    private String participateTime;
    private String TakeOnWork;
    private String budget;

    private String mentorSign;
    private String hostSign;

    public GraduateParticipateProject() {
    }

    public GraduateParticipateProject(Graduate graduate, Project project, String participateTime, String takeOnWork, String budget, String mentorSign, String hostSign) {
        this.graduate = graduate;
        this.project = project;
        this.participateTime = participateTime;
        TakeOnWork = takeOnWork;
        this.budget = budget;
        this.mentorSign = mentorSign;
        this.hostSign = hostSign;
    }

    public String getMentorSign() {
        return mentorSign;
    }

    public void setMentorSign(String mentorSign) {
        this.mentorSign = mentorSign;
    }

    public String getHostSign() {
        return hostSign;
    }

    public void setHostSign(String hostSign) {
        this.hostSign = hostSign;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getParticipateTime() {
        return participateTime;
    }

    public void setParticipateTime(String participateTime) {
        this.participateTime = participateTime;
    }

    public String getTakeOnWork() {
        return TakeOnWork;
    }

    public void setTakeOnWork(String takeOnWork) {
        TakeOnWork = takeOnWork;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "GraduateParticipateProject{" +
                "graduate=" + graduate +
                ", project=" + project +
                ", participateTime='" + participateTime + '\'' +
                ", TakeOnWork='" + TakeOnWork + '\'' +
                ", budget='" + budget + '\'' +
                ", mentorSign='" + mentorSign + '\'' +
                ", hostSign='" + hostSign + '\'' +
                '}';
    }
}
