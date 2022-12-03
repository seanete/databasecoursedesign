package DAO.Entity;

public class MasterAchievementReport {
    private Graduate graduate;
    private Report report;

    public MasterAchievementReport(){
    }

    public MasterAchievementReport(Graduate graduate, Report report) {
        this.graduate = graduate;
        this.report = report;
    }

    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "MasterAchievementReport{" +
                "graduate=" + graduate +
                ", report=" + report +
                '}';
    }
}
